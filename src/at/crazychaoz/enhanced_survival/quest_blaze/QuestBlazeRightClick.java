package at.crazychaoz.enhanced_survival.quest_blaze;

import at.crazychaoz.enhanced_survival.EnhancedSurvivalPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class QuestBlazeRightClick implements Listener {

    private final QuestBlaze questBlaze;
    private final EnhancedSurvivalPlugin plugin;

    public QuestBlazeRightClick(QuestBlaze blaze, EnhancedSurvivalPlugin plugin) {
        this.questBlaze = blaze;
        this.plugin = plugin;
    }

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

}
