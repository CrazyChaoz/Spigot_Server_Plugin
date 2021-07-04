package at.crazychaoz.enhanced_survival.quest_blaze;

import at.crazychaoz.enhanced_survival.EnhancedSurvivalPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public record QuestBlazeInventoryClicked(EnhancedSurvivalPlugin plugin) implements Listener {

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
            QuestBlazeRecipe recipe= inventory.getRecipes().get(event.getSlot());
            List<ItemStack> itemsToHandIn = recipe.getItems();
            boolean playerHasAllItems = itemsToHandIn.stream().allMatch(itemStack -> player.getInventory().contains(itemStack.getType(), itemStack.getAmount()));
            if (playerHasAllItems) {
                //onHandInSuccess
                player.sendMessage("NAISUUUUU");
                itemsToHandIn.forEach(itemStack -> player.getInventory().removeItem(itemStack));
                recipe.onSuccess(player);
            } else {
                //onHandInFail
                player.sendMessage("OH HELL NAW");
            }
        }
    }
}
