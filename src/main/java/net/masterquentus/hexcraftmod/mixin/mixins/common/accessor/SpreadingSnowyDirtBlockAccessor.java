package net.masterquentus.hexcraftmod.mixin.mixins.common.accessor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

import java.lang.reflect.Method;

@Mixin(SpreadingSnowyDirtBlock.class)
public class SpreadingSnowyDirtBlockAccessor {

    // Store the private method references here for later use
    private Method callCanBeGrassMethod;
    private Method callCanPropagateMethod;

    // Constructor or initialization block to fetch the methods using reflection
    public SpreadingSnowyDirtBlockAccessor() {
        try {
            // Using reflection to access private methods
            callCanBeGrassMethod = SpreadingSnowyDirtBlock.class.getDeclaredMethod("canBeGrass", BlockState.class, LevelReader.class, BlockPos.class);
            callCanBeGrassMethod.setAccessible(true); // Allow access to private method

            callCanPropagateMethod = SpreadingSnowyDirtBlock.class.getDeclaredMethod("canPropagate", BlockState.class, LevelReader.class, BlockPos.class);
            callCanPropagateMethod.setAccessible(true); // Allow access to private method
        } catch (NoSuchMethodException e) {
            e.printStackTrace(); // In case the methods are not found
        }
    }

    // Method to invoke the private 'canBeGrass' method
    public boolean invokeCanBeGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
        try {
            // Accessing the method via the concrete instance of SpreadingSnowyDirtBlock
            SpreadingSnowyDirtBlock blockInstance = (SpreadingSnowyDirtBlock) state.getBlock();  // Get the block instance
            return (boolean) callCanBeGrassMethod.invoke(blockInstance, state, levelReader, pos);
        } catch (Exception e) {
            e.printStackTrace(); // Handle any reflection invocation issues
            return false; // Return false if there is an issue
        }
    }

    // Method to invoke the private 'canPropagate' method
    public boolean invokeCanPropagate(BlockState state, LevelReader levelReader, BlockPos pos) {
        try {
            // Accessing the method via the concrete instance of SpreadingSnowyDirtBlock
            SpreadingSnowyDirtBlock blockInstance = (SpreadingSnowyDirtBlock) state.getBlock();  // Get the block instance
            return (boolean) callCanPropagateMethod.invoke(blockInstance, state, levelReader, pos);
        } catch (Exception e) {
            e.printStackTrace(); // Handle any reflection invocation issues
            return false; // Return false if there is an issue
        }
    }
}