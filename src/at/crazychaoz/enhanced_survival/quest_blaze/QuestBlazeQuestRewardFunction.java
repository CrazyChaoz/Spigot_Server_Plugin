package at.crazychaoz.enhanced_survival.quest_blaze;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class QuestBlazeQuestRewardFunction implements Consumer<Player> {
    @Override
    public void accept(Player player) {
        AttributeInstance ai = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        ai.setBaseValue(ai.getBaseValue() + 2);
    }
}
