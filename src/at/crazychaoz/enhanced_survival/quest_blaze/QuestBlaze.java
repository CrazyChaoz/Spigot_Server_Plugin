package at.crazychaoz.enhanced_survival.quest_blaze;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class QuestBlaze extends EntityCreature {

    public Inventory inventory;
    private List<QuestBlazeRecipe> recipes;

    public QuestBlaze(World world) {
        super(EntityTypes.BLAZE, world);
        setSilent(true);
        setInvulnerable(true);
        setCustomName(new ChatMessage("Questy"));
        setCustomNameVisible(true);
        setNoGravity(true);

        setCustomInventory(new ArrayList<>());
    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
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
