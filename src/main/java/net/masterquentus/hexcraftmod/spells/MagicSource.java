package net.masterquentus.hexcraftmod.spells;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public enum MagicSource {
    ANCESTRAL,
    EXPRESSION,
    NATURE,
    CHANNELING,
    DARK_OBJECTS,
    SPIRIT,
    SIPHONER,
    HERETIC,
    WEREWITCH;

    public void applyMagicSourceEffects(ServerLevel level, Player caster, ModularSpell spell) {
        // Customize magic source behavior here
    }
}