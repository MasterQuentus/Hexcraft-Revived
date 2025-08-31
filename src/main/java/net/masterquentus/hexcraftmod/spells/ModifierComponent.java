package net.masterquentus.hexcraftmod.spells;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public interface ModifierComponent {
    String getId();
    void modify(ServerLevel level, Player caster, CoreComponent core);
}