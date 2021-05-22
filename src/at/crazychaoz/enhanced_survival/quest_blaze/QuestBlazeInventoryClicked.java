package at.crazychaoz.enhanced_survival.quest_blaze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class QuestBlazeInventoryClicked implements Listener {

    private QuestBlaze questBlaze;

    public QuestBlazeInventoryClicked(QuestBlaze questBlaze) {
        this.questBlaze = questBlaze;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getClickedInventory().equals(questBlaze.inventory)) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        boolean handInStatus;
        switch (event.getSlot()) {
            case 0:
                handInStatus = tryToHandInItems(event, questBlaze.getRecipes().get(0).getItems());
                break;
//            case 1:
//                handInStatus = tryToHandInItems(event, questBlaze.getRecipes().get(1).getItems());
//                break;
            default:
                return;
        }

        if (handInStatus) {
            onHandInSuccess();
        } else {
            onHandInFail();
        }
    }

    public boolean tryToHandInItems(InventoryClickEvent event, List<ItemStack> itemsToHandIn) {


        return false;
    }

    private void onHandInSuccess() {
    }

    private void onHandInFail() {
    }
}
