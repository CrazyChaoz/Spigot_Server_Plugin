package at.crazychaoz.enhanced_survival.quest_blaze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class QuestBlazeInventoryClicked implements Listener {

    private QuestBlaze questBlaze;

    public QuestBlazeInventoryClicked(QuestBlaze questBlaze) {
        this.questBlaze = questBlaze;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!event.getClickedInventory().equals(questBlaze.inventory))return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        event.setCancelled(true);

        Player player=(Player) event.getWhoClicked();

        if(event.getSlot() == 0){

        }
    }
}
