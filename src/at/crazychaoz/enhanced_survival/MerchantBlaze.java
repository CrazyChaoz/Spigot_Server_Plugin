package at.crazychaoz.enhanced_survival;

import net.minecraft.server.v1_16_R3.*;

public class MerchantBlaze extends EntityCreature{


    public MerchantBlaze(World world) {
        super(EntityTypes.BLAZE, world);
        setSilent(true);
        setInvulnerable(true);
        setCustomName(new ChatMessage("Merchy"));
        setCustomNameVisible(true);
        setNoGravity(true);
    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(0,new PathfinderGoalLookAtPlayer(this,EntityHuman.class,8.0F));
    }
}
