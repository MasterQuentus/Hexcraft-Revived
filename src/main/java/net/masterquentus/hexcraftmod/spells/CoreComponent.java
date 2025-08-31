package net.masterquentus.hexcraftmod.spells;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public interface CoreComponent {
    String getId();
    void applyEffect(ServerLevel level, Player caster);
}