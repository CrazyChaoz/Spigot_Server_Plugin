package at.crazychaoz.enhanced_survival;

import net.minecraft.server.v1_16_R3.*;

public class MerchantBlaze extends EntityVillager {


    public MerchantBlaze(EntityTypes<? extends EntityVillager> entitytypes, World world) {
        super(entitytypes, world);
    }

    @Override
    public void openTrade(EntityHuman entityhuman, IChatBaseComponent ichatbasecomponent, int i) {
        super.openTrade(entityhuman, ichatbasecomponent, i);
    }
}
