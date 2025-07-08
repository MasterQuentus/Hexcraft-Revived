package net.masterquentus.hexcraftmod.spells;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public abstract class HexSpell {
    public abstract String getId(); // e.g., "incendia"
    public abstract void cast(ServerLevel level, Player caster);
}