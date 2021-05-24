package at.crazychaoz.enhanced_survival;

import at.crazychaoz.enhanced_survival.quest_blaze.QuestBlaze;
import at.crazychaoz.enhanced_survival.quest_blaze.QuestBlazeInventory;
import at.crazychaoz.enhanced_survival.quest_blaze.SpawnQuestBlazeCommand;
import net.minecraft.server.v1_16_R3.Entity;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EnhancedSurvivalPlugin extends JavaPlugin {
    private static final String VERSION = "0.0.12";
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
        readFromFile();
    }


    private void readFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("enhanced_survival_plugin_blazes.data"))) {
            livingBlazes.forEach(questBlaze -> {
                bufferedReader.lines().forEach(line->{
                    String[] splitLine=line.split(";");
                });
            });
        } catch (FileNotFoundException e) {
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Enhanced Survival Plugin v" + VERSION + " didn't find a previous enhanced_survival_plugin_blazes.data");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("enhanced_survival_plugin_inventories.data"))) {
            livingBlazes.forEach(questBlaze -> {
                bufferedReader.lines().forEach(line->{
                    String[] splitLine=line.split(";");
                });
            });
        } catch (FileNotFoundException e) {
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Enhanced Survival Plugin v" + VERSION + " didn't find a previous enhanced_survival_plugin_inventories.data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile() {
        try (BufferedWriter fileOutputStream = new BufferedWriter(new FileWriter("enhanced_survival_plugin_blazes.data"))) {
            livingBlazes.forEach(questBlaze -> {
                try {
                    fileOutputStream.write(questBlaze.locX() + ";");
                    fileOutputStream.write(questBlaze.locY() + ";");
                    fileOutputStream.write(questBlaze.locZ() + ";");
                    fileOutputStream.write(questBlaze.yaw + ";");
                    fileOutputStream.write(questBlaze.pitch + ";\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter fileOutputStream = new BufferedWriter(new FileWriter("enhanced_survival_plugin_inventories.data"))) {
            inventories.forEach((s, questBlazeInventory) -> {
                try {
                    fileOutputStream.write(s);
                    fileOutputStream.write(";#");
                    questBlazeInventory.getRecipes().forEach(questBlazeRecipe -> {
                        try {
                            fileOutputStream.write(questBlazeRecipe.getTriggerItem().getType().getKey().getNamespace());
                            fileOutputStream.write(";");
                            fileOutputStream.write(questBlazeRecipe.getTriggerItem().getType().getKey().getKey());
                            fileOutputStream.write(";");
                            fileOutputStream.write(questBlazeRecipe.getTriggerItem().getAmount() + "");
                            fileOutputStream.write(";");
                            fileOutputStream.write(questBlazeRecipe.getTriggerItem().getItemMeta().getDisplayName());
                            fileOutputStream.write(";");
                            fileOutputStream.write(questBlazeRecipe.getTriggerItem().getItemMeta().getLore().stream().reduce("", (a, b) -> a + b + ";"));
                            fileOutputStream.write("\n");

                            questBlazeRecipe.getItems().forEach(itemStack -> {
                                try {
                                    fileOutputStream.write(questBlazeRecipe.getTriggerItem().getType().getKey().getNamespace());
                                    fileOutputStream.write(";");
                                    fileOutputStream.write(questBlazeRecipe.getTriggerItem().getType().getKey().getKey());
                                    fileOutputStream.write(";");
                                    fileOutputStream.write(questBlazeRecipe.getTriggerItem().getAmount() + "");
                                    fileOutputStream.write(";\n");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


