package at.crazychaoz.enhanced_survival;

import net.minecraft.server.v1_16_R3.ChatMessage;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EntityVillager;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SpawnMerchantBlazeCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
            Location location = player.getLocation();

            if (command.getName().equalsIgnoreCase("blaze_merchant")) {
                EntityVillager merchy = new MerchantBlaze(EntityTypes.VILLAGER, world);
                merchy.setCustomName(new ChatMessage("Merchy"));
                merchy.setCustomNameVisible(true);
                merchy.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
                world.addEntity(merchy);
            }
        }

        return true;
    }
}
