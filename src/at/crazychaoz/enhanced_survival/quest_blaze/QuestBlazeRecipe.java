package at.crazychaoz.enhanced_survival.quest_blaze;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class QuestBlazeRecipe {

    public static List<QuestBlazeRecipe> DEFAULT_RECIPES = new ArrayList<>();

    static {
        ItemStack item = new ItemStack(Material.BELL);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "Stuff");
        List<String> lore = new ArrayList<>();
        lore.add("Hand in Items");
        lore.add("70x Dirt");
        lore.add("5x Netherstar");
        meta.setLore(lore);
        item.setItemMeta(meta);

        List<ItemStack> itemsToHandIn = new ArrayList<>();

        ItemStack itemsToHandIn0 = new ItemStack(Material.DIRT);
        itemsToHandIn0.setAmount(70);
        itemsToHandIn.add(itemsToHandIn0);

        ItemStack itemsToHandIn1 = new ItemStack(Material.NETHER_STAR);
        itemsToHandIn1.setAmount(5);
        itemsToHandIn.add(itemsToHandIn1);

        QuestBlazeRecipe default1 = new QuestBlazeRecipe(item, itemsToHandIn);


        DEFAULT_RECIPES.add(default1);

    }

    private ItemStack triggerItem;
    private List<ItemStack> items;
    private Consumer<Player> onSuccessFunction = player -> {
        AttributeInstance ai = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        ai.setBaseValue(ai.getBaseValue() + 2);
    };

    public QuestBlazeRecipe(ItemStack triggerItem, List<ItemStack> items) {
        this.triggerItem = triggerItem;
        this.items = items;
    }

    public ItemStack getTriggerItem() {
        return triggerItem;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void setOnSuccessFunction(Consumer<Player> onSuccessFunction) {
        this.onSuccessFunction = onSuccessFunction;
    }

    public void onSuccess(Player player) {
        onSuccessFunction.accept(player);
    }
}
