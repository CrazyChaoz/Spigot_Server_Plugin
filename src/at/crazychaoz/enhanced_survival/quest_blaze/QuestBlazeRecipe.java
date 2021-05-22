package at.crazychaoz.enhanced_survival.quest_blaze;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class QuestBlazeRecipe {

    public static List<QuestBlazeRecipe> DEFAULT_RECIPES = new ArrayList<>();

    static {
        ItemStack item = new ItemStack(Material.BELL);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "Stuff");
        List<String> lore = new ArrayList<>();
        lore.add("Hand in Items");
        lore.add("64x Dirt");
        lore.add("5x Netherstar");
        meta.setLore(lore);
        item.setItemMeta(meta);

        List<ItemStack> itemsToHandIn=new ArrayList<>();

        ItemStack itemsToHandIn0=new ItemStack(Material.DIRT);
        itemsToHandIn0.setAmount(64);
        itemsToHandIn.add(itemsToHandIn0);

        ItemStack itemsToHandIn1=new ItemStack(Material.DIRT);
        itemsToHandIn1.setAmount(64);
        itemsToHandIn.add(itemsToHandIn1);

        DEFAULT_RECIPES.add(new QuestBlazeRecipe(item,itemsToHandIn));




    }

    private ItemStack triggerItem;
    private List<ItemStack> items;

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
}
