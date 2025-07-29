package net.masterquentus.hexcraftmod.item.custom;

import net.masterquentus.hexcraftmod.block.custom.chalk.AbstractChalkBlock;
import net.masterquentus.hexcraftmod.block.entity.custom.RedChalkMarkBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class RedChalkItem extends Item {

    private final Block redChalkMarkBlock;

    public RedChalkItem(Block markBlock, Properties properties) {
        super(properties);
        this.redChalkMarkBlock = markBlock;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getClickedFace() != Direction.UP) return InteractionResult.FAIL;

        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        BlockPos targetPos = clickedPos.above();
        BlockState below = level.getBlockState(clickedPos);
        BlockState above = level.getBlockState(targetPos);

        if (!above.isAir() || !below.isSolidRender(level, clickedPos)) return InteractionResult.FAIL;

        if (below.getBlock() instanceof BushBlock ||
                below.getBlock() instanceof CarpetBlock ||
                below.getBlock() instanceof SlabBlock ||
                below.getBlock() instanceof LiquidBlock ||
                below.getBlock() instanceof AbstractChalkBlock) {
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide && context.getPlayer() != null) {
            int glyph = level.random.nextInt(8) + 1; // Red chalk has 8 symbols

            BlockState state = redChalkMarkBlock.defaultBlockState()
                    .setValue(RedChalkMarkBlock.GLYPH, glyph);

            level.setBlock(targetPos, state, Block.UPDATE_ALL);
            context.getPlayer().getItemInHand(context.getHand())
                    .hurtAndBreak(1, context.getPlayer(), p -> {});
        }

        return InteractionResult.SUCCESS;
    }
}