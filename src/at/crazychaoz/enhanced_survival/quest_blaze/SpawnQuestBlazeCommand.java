package at.crazychaoz.enhanced_survival.quest_blaze;

import at.crazychaoz.enhanced_survival.EnhancedSurvivalPlugin;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;


public class SpawnQuestBlazeCommand implements CommandExecutor, Listener {

    private final EnhancedSurvivalPlugin enhancedSurvivalPlugin;

    public SpawnQuestBlazeCommand(EnhancedSurvivalPlugin enhancedSurvivalPlugin) {
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
                enhancedSurvivalPlugin.livingBlazes.add(questy);
                world.addEntity(questy, CreatureSpawnEvent.SpawnReason.COMMAND);
                enhancedSurvivalPlugin.getServer().getPluginManager().registerEvents(new QuestBlazeRightClick(questy, enhancedSurvivalPlugin), enhancedSurvivalPlugin);
                enhancedSurvivalPlugin.getServer().getPluginManager().registerEvents(new QuestBlazeInventoryClicked(enhancedSurvivalPlugin), enhancedSurvivalPlugin);
            }
        }

        return true;
    }
}
