package at.crazychaoz.enhanced_survival.quest_blaze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class QuestBlazeInventory {
    public Inventory inventory;
    private List<QuestBlazeRecipe> recipes;

    public QuestBlazeInventory() {
        setCustomInventory(QuestBlazeRecipe.DEFAULT_RECIPES);
    }

    public void setCustomInventory(List<QuestBlazeRecipe> items) {
        inventory = Bukkit.createInventory(null, 9 + (items.size() / 9), ChatColor.RED + "" + ChatColor.BOLD + "Sacrifice your Items");
        recipes = items;

        for (int i = 0; i < items.size(); i++) {
            inventory.setItem(i, items.get(i).getTriggerItem());
        }
    }

    public List<QuestBlazeRecipe> getRecipes() {
        return recipes;
    }
}
