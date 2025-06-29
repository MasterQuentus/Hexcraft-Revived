package net.masterquentus.hexcraftmod.block.custom.plants;

import net.masterquentus.hexcraftmod.entity.vampires.VampireEvokerEntity;
import net.masterquentus.hexcraftmod.entity.vampires.VampirePiglinEntity;
import net.masterquentus.hexcraftmod.entity.vampires.VampireVindicatorEntity;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class VervainStrandBlock extends Block {

    public VervainStrandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
        return new ItemStack(HexcraftItems.VERVAIN_STRAND_ITEM.get());
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        return (direction == Direction.UP || direction == Direction.DOWN) ? null : super.getStateForPlacement(context);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, world, pos, oldState, isMoving);

        if (!world.isClientSide) {
            scareVampires(world, pos);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        scareVampires(world, pos); // Run effect on every tick
        world.scheduleTick(pos, this, 20); // Schedule next check in 1 second
    }

    private void scareVampires(Level world, BlockPos pos) {
        int radius = 6;
        AABB area = new AABB(pos).inflate(radius);

        List<LivingEntity> vampires = world.getEntitiesOfClass(LivingEntity.class, area, entity ->
                entity instanceof VampireEvokerEntity ||
                        entity instanceof VampireVindicatorEntity ||
                        entity instanceof VampirePiglinEntity
        );

        for (LivingEntity vampire : vampires) {
            if (vampire instanceof PathfinderMob pathfinderMob) {
                // Stop any movement toward other entities
                pathfinderMob.getNavigation().stop();
                pathfinderMob.setTarget(null); // Remove their current target to stop attacking

                // Make the mob flee away from the VervainStrandBlock immediately upon proximity
                Vec3 fleeDirection = vampire.position().subtract(Vec3.atCenterOf(pos)).normalize().scale(3.0);
                pathfinderMob.getNavigation().moveTo(vampire.getX() + fleeDirection.x, vampire.getY() + fleeDirection.y, vampire.getZ() + fleeDirection.z, 1.5);
            }

            // Apply vervain damage (this is the part that hurts them)
            vampire.hurt(vampire.damageSources().magic(), 3.0F);
        }
    }
}
