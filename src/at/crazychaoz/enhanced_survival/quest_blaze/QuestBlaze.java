package at.crazychaoz.enhanced_survival.quest_blaze;

import at.crazychaoz.enhanced_survival.EnhancedSurvivalPlugin;
import net.minecraft.network.chat.ChatMessage;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.level.World;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

import java.util.UUID;

public class QuestBlaze extends EntityCreature{
    private final UUID worldUuid;
    private final EnhancedSurvivalPlugin plugin;


    public QuestBlaze(World world, UUID worldUuid, EnhancedSurvivalPlugin plugin) {
        super(EntityTypes.h, world);
        setSilent(true);
        setInvulnerable(true);
        setCustomName(new ChatMessage("Questy"));
        setCustomNameVisible(true);
        setNoGravity(true);

        //setNoAI(true);

        getAttributeInstance(GenericAttributes.d).setValue(0);
        getAttributeInstance(GenericAttributes.c).setValue(100);

        cb=new MinecraftKey("");

        this.worldUuid=worldUuid;
        this.plugin=plugin;
    }

    public UUID getWorldUuid() {
        return worldUuid;
    }

    @Override
    protected void initPathfinder() {
        this.bO.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
    }


    @Override
    public void die(DamageSource damagesource) {
        plugin.livingBlazes.remove(this);
        super.die(damagesource);
    }
}
