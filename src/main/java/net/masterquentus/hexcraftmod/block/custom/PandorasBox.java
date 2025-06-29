package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.entity.HexcraftBlockEntities;
import net.masterquentus.hexcraftmod.block.entity.custom.PandorasBoxBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class PandorasBox extends Block implements EntityBlock {
    public PandorasBox(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof PandorasBoxBlockEntity pandorasBox) {
                pandorasBox.startOpeningAnimation(player); // ✅ Starts animation when right-clicked

                // ✅ Spread Cursed Soil when activated
                spreadCursedSoil((ServerLevel) level, pos);

                // ✅ Play ominous sound effect
                level.playSound(null, pos, SoundEvents.WITHER_SPAWN, SoundSource.BLOCKS, 1.0F, 1.0F);

                // ✅ Add particle effects for a spooky effect
                level.addParticle(ParticleTypes.SMOKE, pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0, 0);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PandorasBoxBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == HexcraftBlockEntities.PANDORAS_BOX_ENTITY.get()
                ? (lvl, pos, blockState, blockEntity) -> {
            if (blockEntity instanceof PandorasBoxBlockEntity pandorasBox) {
                PandorasBoxBlockEntity.tick(lvl, pos, blockState, pandorasBox);
            }
        }
                : null;
    }

    // ✅ Spread Cursed Soil when Pandora's Box is activated
    private void spreadCursedSoil(ServerLevel level, BlockPos center) {
        int radius = 3; // Spread radius for the effect

        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-radius, 0, -radius), center.offset(radius, 0, radius))) {
            BlockState blockState = level.getBlockState(pos);

            // ✅ Only replace natural ground blocks (Dirt, Grass, etc.)
            if (blockState.is(BlockTags.DIRT) || blockState.is(Blocks.GRASS_BLOCK)) {
                level.setBlock(pos, HexcraftBlocks.CURSED_SOIL.get().defaultBlockState(), 3);
            }
        }
    }
}