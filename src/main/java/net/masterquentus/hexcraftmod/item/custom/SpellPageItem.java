package net.masterquentus.hexcraftmod.item.custom;

import net.masterquentus.hexcraftmod.spells.SpellDataProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class SpellPageItem extends Item {
    private final String spellId;

    public SpellPageItem(String spellId, Properties props) {
        super(props);
        this.spellId = spellId;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!(context.getPlayer() instanceof ServerPlayer player)) return InteractionResult.PASS;

        player.getCapability(SpellDataProvider.SPELL_DATA).ifPresent(data -> {
            if (!data.knowsSpell(spellId)) {
                data.learnSpell(spellId);
                player.displayClientMessage(Component.literal("You have learned the spell: " + spellId), true);
                context.getItemInHand().shrink(1);
            } else {
                player.displayClientMessage(Component.literal("You already know this spell."), true);
            }
        });

        return InteractionResult.SUCCESS;
    }
}