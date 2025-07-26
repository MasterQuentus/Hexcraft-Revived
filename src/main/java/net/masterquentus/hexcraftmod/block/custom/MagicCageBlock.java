package net.masterquentus.hexcraftmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MagicCageBlock extends Block {

    public MagicCageBlock(Properties properties) {
        super(properties);
    }

    // Make unbreakable by setting destroy time to -1
    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos) {
        return 0.0F; // Cannot be mined effectively
    }

    // Prevent players from harvesting
    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        return false;
    }

    // Allow players to pass through by making collision empty for players
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            if (entity instanceof Player) {
                // No collision for players
                return Shapes.empty();
            }
        }
        // For mobs or other entities, full cube collision
        return Shapes.block();
    }

    // Optional: Make it not occluding to avoid rendering shadows
    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }
}