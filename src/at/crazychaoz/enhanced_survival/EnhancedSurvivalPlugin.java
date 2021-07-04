package at.crazychaoz.enhanced_survival;

import at.crazychaoz.enhanced_survival.quest_blaze.*;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.Entity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EnhancedSurvivalPlugin extends JavaPlugin {
    private static final String VERSION = "1.0.0";
    public final HashMap<String, QuestBlazeInventory> inventories = new HashMap<>();
    public final ArrayList<QuestBlaze> livingBlazes = new ArrayList<>();


    @Override
    public void onDisable() {
        writeToFile();
        //kill all questblazes
        livingBlazes.forEach(Entity::die);
    }

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Enhanced Survival Plugin v" + VERSION + " enabled");
        getCommand("quest_blaze").setExecutor(new SpawnQuestBlazeCommand(this));
        this.getServer().getPluginManager().registerEvents(new QuestBlazeInventoryClicked(this), this);
        readFromFile();
    }

    @SuppressWarnings({"unchecked", "nullable"})
    private void readFromFile() {
        try {
            FileReader reader = new FileReader("enhanced_survival_plugin.data");
            JSONParser parser = new JSONParser();
            JSONObject topLevelObject = (JSONObject) parser.parse(reader);
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Read File from Version " + topLevelObject.get("version") + "");

            ((JSONArray) topLevelObject.get("livingBlazes")).forEach(blazeDataUncast -> {
                JSONObject blazeData = (JSONObject) blazeDataUncast;
                spawn_quest_blaze(
                        (CraftWorld) getServer().getWorld(UUID.fromString((String) blazeData.get("world"))),
                        UUID.fromString((String) blazeData.get("world")),
                        Double.parseDouble((String) blazeData.get("locX")),
                        Double.parseDouble((String) blazeData.get("locY")),
                        Double.parseDouble((String) blazeData.get("locZ")),
                        Float.parseFloat((String) blazeData.get("YRot")),
                        Float.parseFloat((String) blazeData.get("XRot")));
            });

            JSONArray inventoriesJson = (JSONArray) topLevelObject.get("inventories");
            if (!inventoriesJson.isEmpty()) {
                inventoriesJson.forEach(invUncast -> {
                    JSONObject inv = (JSONObject) invUncast;

                    QuestBlazeInventory reconstructedInventory = new QuestBlazeInventory();

                    List<QuestBlazeRecipe> recipeList = new ArrayList<>();

                    ((JSONArray) inv.get("recipe")).forEach(recipeJsonUncast -> {

                        JSONObject recipeJson = (JSONObject) recipeJsonUncast;

                        ItemStack triggeritem = new ItemStack(Material.getMaterial((String) recipeJson.get("triggeritem_key")), Integer.parseInt((String) recipeJson.get("triggeritem_amount")));

                        ItemMeta meta = triggeritem.getItemMeta();

                        meta.setDisplayName((String) recipeJson.get("triggeritem_displayname"));

                        JSONArray loreLines = (JSONArray) recipeJson.get("triggeritem_lore");

                        meta.setLore(loreLines);

                        triggeritem.setItemMeta(meta);

                        List<ItemStack> itemList = new ArrayList<>();

                        ((JSONArray) recipeJson.get("handin_items")).forEach(singleHandInItemUncast -> {
                            JSONObject singleHandInItemJson = (JSONObject) singleHandInItemUncast;
                            ItemStack singleHandInItem = new ItemStack(Material.getMaterial((String) singleHandInItemJson.get("handin_key")), Integer.parseInt((String) singleHandInItemJson.get("handin_amount")));
                            itemList.add(singleHandInItem);
                        });

                        recipeList.add(new QuestBlazeRecipe(triggeritem, itemList));
                    });

                    reconstructedInventory.setCustomInventory(recipeList);
                    inventories.put((String) inv.get("user"), reconstructedInventory);
                });
            }

        } catch (FileNotFoundException | NoSuchFileException fileNotFound) {
            System.out.println("file not found, starting from empty data");
            //fileNotFoundException.printStackTrace();
        } catch
        (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    private void readFromFile_oldVersion() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("enhanced_survival_plugin_blazes.data"))) {
            bufferedReader.lines().forEach(line -> {
                String[] splitLine = line.split(";");
                try {
                    spawn_quest_blaze((CraftWorld) getServer().getWorld(UUID.fromString(splitLine[0])), UUID.fromString(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]), Float.parseFloat(splitLine[4]), Float.parseFloat(splitLine[5]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Enhanced Survival Plugin v" + VERSION + " didn't find a previous enhanced_survival_plugin_blazes.data");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("enhanced_survival_plugin_inventories.data"))) {
            bufferedReader.lines().forEach(line -> {
                try {

                    int i = 0;
                    String name = line.substring(1, line.indexOf(",", i));
                    String data = line.substring(line.indexOf(",", 0) + 1, line.lastIndexOf("]"));
                    while (i < line.length() + 5) {
                        String offer = data.substring(data.indexOf("{", i) + 1, line.indexOf("]", i));
                        i = line.indexOf("]", i) + 2;
                        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Name: " + name + ", Offer: " + offer);
                    }
                } catch (StringIndexOutOfBoundsException indexOutOfBoundsException) {
                    indexOutOfBoundsException.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Enhanced Survival Plugin v" + VERSION + " didn't find a previous enhanced_survival_plugin_inventories.data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile() {
        JSONObject topLevelObject = new JSONObject();
        topLevelObject.put("version", VERSION);
        JSONArray livingBlazeJson = new JSONArray();
        livingBlazes.forEach(questBlaze -> {
            JSONObject blazeData = new JSONObject();
            blazeData.put("world", questBlaze.getWorldUuid() + "");
            blazeData.put("locX", questBlaze.locX() + "");
            blazeData.put("locY", questBlaze.locY() + "");
            blazeData.put("locZ", questBlaze.locZ() + "");
            blazeData.put("YRot", questBlaze.getYRot() + "");
            blazeData.put("XRot", questBlaze.getXRot() + "");
            livingBlazeJson.add(blazeData);
        });
        topLevelObject.put("livingBlazes", livingBlazeJson);

        JSONArray inventoryJson = new JSONArray();
        inventories.forEach((s, questBlazeInventory) -> {
            JSONObject inv = new JSONObject();
            inv.put("user", s);
            JSONArray itemJson = new JSONArray();
            questBlazeInventory.getRecipes().forEach(questBlazeRecipe -> {
                JSONObject recipeJson = new JSONObject();
                recipeJson.put("triggeritem_namespace", questBlazeRecipe.getTriggerItem().getType().getKey().getNamespace());
                recipeJson.put("triggeritem_key", questBlazeRecipe.getTriggerItem().getType().name());
                recipeJson.put("triggeritem_amount", questBlazeRecipe.getTriggerItem().getAmount() + "");
                recipeJson.put("triggeritem_displayname", questBlazeRecipe.getTriggerItem().getItemMeta().getDisplayName());
                JSONArray loreLines = new JSONArray();
                loreLines.addAll(questBlazeRecipe.getTriggerItem().getItemMeta().getLore());
                recipeJson.put("triggeritem_lore", loreLines);
                JSONArray handInItemJson = new JSONArray();
                questBlazeRecipe.getItems().forEach(itemStack -> {
                    JSONObject singleHandInItem = new JSONObject();
                    singleHandInItem.put("handin_namespace", itemStack.getType().getKey().getNamespace());
                    singleHandInItem.put("handin_key", itemStack.getType().name());
                    singleHandInItem.put("handin_amount", itemStack.getAmount() + "");
                    handInItemJson.add(singleHandInItem);
                });
                recipeJson.put("handin_items", handInItemJson);
                itemJson.add(recipeJson);
            });
            inv.put("recipe", itemJson);
            inventoryJson.add(inv);
        });
        topLevelObject.put("inventories", inventoryJson);


        try (BufferedWriter fileOutputStream = new BufferedWriter(new FileWriter("enhanced_survival_plugin.data"))) {
            topLevelObject.writeJSONString(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void spawn_quest_blaze(WorldServer world, double locX, double locY, double locZ, float yaw, float pitch) {
        spawn_quest_blaze(world.getWorld(), world.uuid, locX, locY, locZ, yaw, pitch);
    }

    public void spawn_quest_blaze(CraftWorld world, UUID worldUuid, double locX, double locY, double locZ, float yaw, float pitch) {
        QuestBlaze questy = new QuestBlaze(world.getHandle(), worldUuid,this);
        questy.setLocation(locX, locY, locZ, yaw, pitch);
        this.livingBlazes.add(questy);
        world.addEntity(questy, CreatureSpawnEvent.SpawnReason.COMMAND);

        this.getServer().getPluginManager().registerEvents(new QuestBlazeRightClick(questy, this), this);
    }
}


