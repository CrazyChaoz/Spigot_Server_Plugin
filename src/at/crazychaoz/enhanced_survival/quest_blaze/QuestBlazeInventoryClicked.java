package at.crazychaoz.enhanced_survival.quest_blaze;

import at.crazychaoz.enhanced_survival.EnhancedSurvivalPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class QuestBlazeInventoryClicked implements Listener {

    private final EnhancedSurvivalPlugin plugin;

    public QuestBlazeInventoryClicked(EnhancedSurvivalPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (!plugin.inventories.containsKey(player.getName())) return;
        if (!plugin.inventories.get(player.getName()).inventory.equals(event.getClickedInventory())) return;
        event.setCancelled(true);
        QuestBlazeInventory inventory = plugin.inventories.get(player.getName());
        int invsize = inventory.inventory.getSize();
        if (event.getSlot() < invsize) {
            List<ItemStack> itemsToHandIn = inventory.getRecipes().get(event.getSlot()).getItems();
            boolean playerHasAllItems = itemsToHandIn.stream().allMatch(itemStack -> player.getInventory().contains(itemStack.getType(), itemStack.getAmount()));
            if (playerHasAllItems) {
                //onHandInSuccess
                player.sendMessage("NAISUUUUU");
                itemsToHandIn.forEach(itemStack -> player.getInventory().remove(itemStack.getType()));
            } else {
                //onHandInFail
                player.sendMessage("OH HELL NAW");
            }
        }
    }
}
