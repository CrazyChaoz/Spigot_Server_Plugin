package at.crazychaoz.enhanced_survival.quest_blaze;

import net.minecraft.network.chat.ChatMessage;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.level.World;

import java.util.UUID;

public class QuestBlaze extends EntityCreature {
    private final UUID worldUuid;


    public QuestBlaze(World world, UUID worldUuid) {
        super(EntityTypes.h, world);
        setSilent(true);
        setInvulnerable(true);
        setCustomName(new ChatMessage("Questy"));
        setCustomNameVisible(true);
        setNoGravity(true);


        cb=new MinecraftKey("");

        this.worldUuid=worldUuid;
    }

    @Override
    protected void initPathfinder() {
        this.bO.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
    }

    public UUID getWorldUuid() {
        return worldUuid;
    }
}
