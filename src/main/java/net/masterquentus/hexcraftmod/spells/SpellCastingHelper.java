package net.masterquentus.hexcraftmod.spells;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public class SpellCastingHelper {

    public static void castSelectedSpell(ServerLevel level, Player player) {
        player.getCapability(SpellDataProvider.SPELL_DATA).ifPresent(data -> {
            String selected = data.getSelectedSpell();
            if (selected != null) {
                HexSpell spell = SpellRegistry.getSpell(selected);
                if (spell != null && spell.canCast(level, player)) {
                    spell.cast(level, player);
                } else {
                    player.displayClientMessage(Component.literal("Cannot cast spell: " + selected), true);
                }
            } else {
                player.displayClientMessage(Component.literal("No spell selected."), true);
            }
        });
    }
}