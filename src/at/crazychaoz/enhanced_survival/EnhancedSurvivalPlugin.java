package at.crazychaoz.enhanced_survival;

import at.crazychaoz.enhanced_survival.quest_blaze.SpawnQuestBlazeCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class EnhancedSurvivalPlugin extends JavaPlugin {
    private static final String VERSION="0.0.6";
    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"Enhanced Survival Plugin v"+VERSION+" enabled");


        getCommand("quest_blaze").setExecutor(new SpawnQuestBlazeCommand(this));

    }
}


