package net.masterquentus.hexcraftmod.item.custom;

import net.masterquentus.hexcraftmod.spells.SpellDataProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SpellPageItem extends Item {
    private final String spellId;

    public SpellPageItem(String spellId, Properties props) {
        super(props);
        this.spellId = spellId;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (world.isClientSide) return InteractionResultHolder.pass(stack);

        player.getCapability(SpellDataProvider.SPELL_DATA).ifPresent(data -> {
            if (!data.knowsSpell(spellId)) {
                data.learnSpell(spellId);
                player.displayClientMessage(Component.literal("You have learned the spell: " + spellId), true);
                stack.shrink(1);
            } else {
                player.displayClientMessage(Component.literal("You already know this spell."), true);
            }
        });

        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.hexcraftmod.spell_page.tooltip").withStyle(ChatFormatting.AQUA));
    }

}