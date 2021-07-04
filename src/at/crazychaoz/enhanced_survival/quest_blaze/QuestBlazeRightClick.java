package at.crazychaoz.enhanced_survival.quest_blaze;

import at.crazychaoz.enhanced_survival.EnhancedSurvivalPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public record QuestBlazeRightClick(QuestBlaze questBlaze,EnhancedSurvivalPlugin plugin) implements Listener {

    @EventHandler
    public void toggle(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked().getUniqueId().equals(questBlaze.getUniqueID())) {
            if (!plugin.inventories.containsKey(player.getName())) {
                plugin.inventories.put(player.getName(), new QuestBlazeInventory());
            }
            player.openInventory(plugin.inventories.get(player.getName()).inventory);
        }
    }

//    @EventHandler
//    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
//        if (event.getEntity().equals(questBlaze) ) {
//            event.setCancelled(true);
//        }
//    }


}
