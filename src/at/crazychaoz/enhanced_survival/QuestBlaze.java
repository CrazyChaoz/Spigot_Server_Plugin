package at.crazychaoz.enhanced_survival;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class QuestBlaze extends EntityCreature{

    public Inventory inventory;

    public QuestBlaze(World world) {
        super(EntityTypes.BLAZE, world);
        setSilent(true);
        setInvulnerable(true);
        setCustomName(new ChatMessage("Questy"));
        setCustomNameVisible(true);
        setNoGravity(true);

        setCustomInventory();
    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(0,new PathfinderGoalLookAtPlayer(this,EntityHuman.class,8.0F));
    }

    public void setCustomInventory(ItemStack ... items){
        inventory = Bukkit.createInventory(null,9+(items.length/9), ChatColor.RED+""+ChatColor.BOLD+"Sacrifice your Items");

        for (int i = 0; i < items.length; i++) {
            inventory.setItem(i,items[i]);
        }
    }
}
