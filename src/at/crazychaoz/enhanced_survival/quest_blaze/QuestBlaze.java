package at.crazychaoz.enhanced_survival.quest_blaze;

import net.minecraft.server.v1_16_R3.*;

public class QuestBlaze extends EntityCreature {

    public QuestBlaze(World world) {
        super(EntityTypes.BLAZE, world);
        setSilent(true);
        setInvulnerable(true);
        setCustomName(new ChatMessage("Questy"));
        setCustomNameVisible(true);
        setNoGravity(true);
        lootTableKey=new MinecraftKey("");
    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
    }

}
