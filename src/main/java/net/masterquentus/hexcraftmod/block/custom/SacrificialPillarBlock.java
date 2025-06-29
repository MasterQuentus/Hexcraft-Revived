package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.block.entity.SacrificialPillarBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Properties;

public class SacrificialPillarBlock extends BaseEntityBlock {

    public SacrificialPillarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof SacrificialPillarBlockEntity pillar) {
            pillar.broadcastMessage("⏳ DEBUG: Tick detected on Sacrificial Pillar!", ChatFormatting.BLUE);
            pillar.attemptRitual();
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;

        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof SacrificialPillarBlockEntity pillar) {
            ItemStack heldItem = player.getItemInHand(hand);

            if (heldItem.isEmpty()) {
                // Retrieve stored item
                ItemStack retrieved = pillar.removeItem();
                if (!retrieved.isEmpty()) {
                    player.addItem(retrieved);
                    player.displayClientMessage(Component.literal("🗿 You retrieved " + retrieved.getHoverName()), true);
                    return InteractionResult.CONSUME;
                }
            } else {
                // Store the item
                if (pillar.setItem(heldItem)) {
                    if (!player.isCreative()) heldItem.shrink(1);
                    player.displayClientMessage(Component.literal("🗿 You placed " + heldItem.getHoverName() + " in the pillar."), true);
                    return InteractionResult.CONSUME;
                }
            }

            // ✅ Try triggering the ritual when interacting
            player.displayClientMessage(Component.literal("🔮 DEBUG: Attempting ritual..."), true);
            pillar.attemptRitual();
        }
        return InteractionResult.PASS;
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SacrificialPillarBlockEntity(pos, state);
    }
}
