package net.masterquentus.hexcraftmod.block.custom.plants;

import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.BlockGetter;

public class GarlicStrandBlock extends Block {

    public GarlicStrandBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
        return new ItemStack(HexcraftItems.GARLIC_STRAND_ITEM.get()); // Change dynamically per flower
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Get the direction of the face that was clicked
        Direction direction = context.getClickedFace();

        // Only allow placement on side faces (EAST, WEST, NORTH, SOUTH)
        if (direction == Direction.UP || direction == Direction.DOWN) {
            return null;  // Return null to prevent placement on top or bottom
        }

        // Call the super method to get the default block state for placement
        BlockState state = super.getStateForPlacement(context);

        // Optionally log the state for debugging
        if (state != null) {
            System.out.println("Block state for placement: " + state);
        } else {
            System.out.println("Failed to retrieve block state for placement.");
        }

        // Return the valid state (or null if invalid placement)
        return state;
    }
}