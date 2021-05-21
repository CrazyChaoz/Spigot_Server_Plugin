package at.crazychaoz.enhanced_survival;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class EnhancedSurvivalPlugin extends JavaPlugin {
    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        getCommand("blaze_merchant").setExecutor(new SpawnMerchantBlazeCommand());
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"Enhanced Survival Plugin enabled");
        //getServer().getPluginManager().registerEvents();
    }
}


