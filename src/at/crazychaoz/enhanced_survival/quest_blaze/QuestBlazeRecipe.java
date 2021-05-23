package at.crazychaoz.enhanced_survival.quest_blaze;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

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
        itemsToHandIn0.setAmount(70);
        itemsToHandIn.add(itemsToHandIn0);

        ItemStack itemsToHandIn1=new ItemStack(Material.NETHER_STAR);
        itemsToHandIn1.setAmount(5);
        itemsToHandIn.add(itemsToHandIn1);

        DEFAULT_RECIPES.add(new QuestBlazeRecipe(item,itemsToHandIn));




    }

    private ItemStack triggerItem;
    private List<ItemStack> items;
    private Predicate<Player> onSuccessFunction;

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
