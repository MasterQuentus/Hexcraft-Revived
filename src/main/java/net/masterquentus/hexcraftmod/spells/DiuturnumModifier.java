package net.masterquentus.hexcraftmod.spells;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public class DiuturnumModifier implements ModifierComponent {

    @Override
    public String getId() {
        return "diuturnum";
    }

    @Override
    public void modify(ServerLevel level, Player caster, CoreComponent core) {
        // This is an example; in practice, you might extend CoreComponent or pass context to increase durations
        // For simplicity, maybe set a flag or increase timer variables in the spell's effect.
        // Implementation depends on how you structure duration in core spells.
    }
}