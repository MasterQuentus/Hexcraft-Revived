package net.masterquentus.hexcraftmod.block.custom.plants;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlisterCactusFlowerBlock extends Block {
    private static final int ADD_PARTICLE_ATTEMPTS = 14;
    private static final int PARTICLE_XZ_RADIUS = 10;
    private static final int PARTICLE_Y_MAX = 10;

    public BlisterCactusFlowerBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    // Flower can only survive on top of a Blister Cactus
    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState belowState = world.getBlockState(pos.below());
        return belowState.is(HexcraftBlocks.BLISTER_CACTUS.get());
    }

    // If the cactus below is removed, the flower breaks
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canSurvive(state, world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    // Custom particle effects when the flower is broken
    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, world, pos, newState, isMoving);

        if (!world.isClientSide()) {
            releaseToxicSpores(world, pos);
        }
    }

    private void releaseToxicSpores(Level world, BlockPos pos) {
        // Spawn particles in a burst around the broken flower
        world.addParticle(ParticleTypes.EXPLOSION, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0.0, 0.0, 0.0);

        // Apply effect to nearby players or entities
        for (LivingEntity entity : world.getEntitiesOfClass(LivingEntity.class,
                new net.minecraft.world.phys.AABB(pos).inflate(3))) {
            if (entity instanceof Player) {
                ((Player) entity).addEffect(new MobEffectInstance(HexcraftEffects.BLISTER_EFFECT.get(), 100, 1)); // Poison for 5 seconds
                // You can add other effects here, like BLINDNESS or WEAKNESS
            }
        }

    }

    // Custom particle effects for continuous animation
    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        // Toxic spore cloud animation
        for (int i = 0; i < ADD_PARTICLE_ATTEMPTS; ++i) {
            double xOffset = x + random.nextDouble();
            double yOffset = y + 0.7;
            double zOffset = z + random.nextDouble();
            world.addParticle(ParticleTypes.SPORE_BLOSSOM_AIR, xOffset, yOffset, zOffset, 0.0, 0.0, 0.0);
        }
    }
}
