package at.crazychaoz.enhanced_survival.quest_blaze;

import at.crazychaoz.enhanced_survival.EnhancedSurvivalPlugin;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


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
                enhancedSurvivalPlugin.spawn_quest_blaze(world, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
            }
        }
        return true;
    }


}
