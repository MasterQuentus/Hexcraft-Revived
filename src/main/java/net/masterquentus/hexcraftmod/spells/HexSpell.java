package net.masterquentus.hexcraftmod.spells;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class HexSpell {

    public abstract String getId(); // Unique identifier for the spell

    public abstract void cast(ServerLevel level, Player caster); // What happens when the spell is cast

    // Determines if the spell can be cast
    public boolean canCast(ServerLevel level, Player caster) {
        AtomicBoolean allowed = new AtomicBoolean(true);

        caster.getCapability(SiphonerDataProvider.SIPHONER_DATA_CAPABILITY).ifPresent(data -> {
            if (data.isSiphoner() && data.getMagicCharge() <= 0) {
                allowed.set(false);
            }
        });

        return allowed.get();
    }
}