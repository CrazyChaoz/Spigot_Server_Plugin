package at.crazychaoz.enhanced_survival.quest_blaze;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class SpawnQuestBlazeCommand implements CommandExecutor, Listener {

    private final JavaPlugin enhancedSurvivalPlugin;

    public SpawnQuestBlazeCommand(JavaPlugin enhancedSurvivalPlugin) {
        this.enhancedSurvivalPlugin = enhancedSurvivalPlugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
            Location location = player.getLocation();

            if (command.getName().equalsIgnoreCase("quest_blaze")) {
                QuestBlaze questy = new QuestBlaze(world);
                questy.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
                questy.setCustomInventory(QuestBlazeRecipe.DEFAULT_RECIPES);

                world.addEntity(questy, CreatureSpawnEvent.SpawnReason.COMMAND);
                enhancedSurvivalPlugin.getServer().getPluginManager().registerEvents(new QuestBlazeRightClick(questy),enhancedSurvivalPlugin);
                enhancedSurvivalPlugin.getServer().getPluginManager().registerEvents(new QuestBlazeInventoryClicked(questy),enhancedSurvivalPlugin);
            }
        }

        return true;
    }
}
