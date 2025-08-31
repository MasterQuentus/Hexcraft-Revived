package net.masterquentus.hexcraftmod.spells;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public interface AugmentComponent {
    String getId();
    void applyExtra(ServerLevel level, Player caster);
}