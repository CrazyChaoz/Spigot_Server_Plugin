package at.crazychaoz.enhanced_survival.quest_blaze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class QuestBlazeRightClick implements Listener {

    private final QuestBlaze questBlaze;

    public QuestBlazeRightClick(QuestBlaze blaze) {
        questBlaze = blaze;
    }

    @EventHandler
    public void toggle(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (event.getRightClicked().getUniqueId().equals(questBlaze.getUniqueID()))
            player.openInventory(questBlaze.inventory);
    }

}
