package at.crazychaoz.enhanced_survival;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MerchantRightClick extends Event {

    private final MerchantBlaze merchantBlaze;

    public MerchantRightClick(MerchantBlaze blaze) {

        merchantBlaze = blaze;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
