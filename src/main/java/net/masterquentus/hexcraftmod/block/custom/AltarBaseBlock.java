package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.entity.custom.AltarTopBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AltarBaseBlock extends Block {

    public AltarBaseBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity entity = world.getBlockEntity(pos.above());  // Check for AltarTopBlockEntity above the base
        if (entity instanceof AltarTopBlockEntity altarTop) {
            ItemStack heldItem = player.getItemInHand(hand);
            if (!heldItem.isEmpty()) {
                // You might want to check some conditions before removing an item
                altarTop.removeItemFromAltar(heldItem);  // This should remove the item
                world.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);

                if (!player.isCreative()) {
                    heldItem.shrink(1);  // Decrease the player's item count
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

}