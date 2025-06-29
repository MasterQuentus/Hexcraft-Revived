package net.masterquentus.hexcraftmod.item.entity;

import net.masterquentus.hexcraftmod.entity.HexcraftEntities;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ThrownBrewOfSproutingEntity extends ThrowableProjectile {

    private boolean soundPlayed = false;  // Flag to prevent repeated sound plays

    // Constructor for the thrown entity when it is created by a LivingEntity
    public ThrownBrewOfSproutingEntity(LivingEntity entity, Level world) {
        super(HexcraftEntities.THROWN_BREW_OF_SPROUTING.get(), entity, world);
        this.setItem(new ItemStack(HexcraftItems.BREW_OF_SPROUTING.get())); // Set the item in the entity
    }

    // Constructor for the thrown entity (default constructor for entity type)
    public ThrownBrewOfSproutingEntity(EntityType<? extends ThrownBrewOfSproutingEntity> type, Level world) {
        super(type, world);  // Call the constructor of the parent class
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getCommandSenderWorld().isClientSide) {  // Check if it's the server side
            if (this.getDeltaMovement().length() > 0 && !soundPlayed) {
                // Play the throw sound only once, when the entity is thrown
                this.getCommandSenderWorld().playSound(null, this.getX(), this.getY(), this.getZ(),
                        SoundEvents.SPLASH_POTION_THROW,
                        SoundSource.NEUTRAL, 1.0F, 1.0F);
                soundPlayed = true;  // Set the flag to prevent repeated play
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        // Apply effects to the entity here (if needed)
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        Level world = this.getCommandSenderWorld(); // Correct way to get the level
        BlockPos pos = result.getBlockPos();

        // Play the break sound only once when the brew lands
        world.playSound(null, pos, SoundEvents.SPLASH_POTION_BREAK, SoundSource.NEUTRAL, 1.0F, 1.0F);

        // When the Brew of Sprouting lands, it will spread plants
        spreadVegetation(world, pos);
    }

    private void spreadVegetation(Level world, BlockPos pos) {
        RandomSource random = world.getRandom(); // Use RandomSource

        // Iterate over a small area around the target block
        for (BlockPos targetPos : BlockPos.betweenClosed(pos.offset(-3, 0, -3), pos.offset(3, 0, 3))) {
            BlockState state = world.getBlockState(targetPos);

            // Only affect grass or dirt blocks
            if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT)) {
                // First, ensure the block is grass or dirt, then place flowers
                if (state.is(Blocks.DIRT) && random.nextFloat() < 0.5) {
                    // Turn dirt into grass (ensure the block is valid and placed correctly)
                    world.setBlock(targetPos, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
                }

                // Now place flowers on top of grass
                if (state.is(Blocks.GRASS_BLOCK) && random.nextFloat() < 0.3) {
                    // Place flowers on top of grass or dirt, ensuring they are breakable
                    BlockState flowerState = getRandomFlower(random); // Get the BlockState
                    if (flowerState.getBlock().canSurvive(flowerState, world, targetPos.above())) {
                        world.setBlock(targetPos.above(), flowerState, 3); // Place the BlockState directly
                    }
                }
            }
        }
    }

    private BlockState getRandomFlower(RandomSource random) {
        Block[] flowers = {Blocks.DANDELION, Blocks.POPPY, Blocks.ALLIUM, Blocks.AZURE_BLUET};
        return flowers[random.nextInt(flowers.length)].defaultBlockState();
    }

    // Optional: you don't need entityData here; handle the item stack directly
    public void setItem(ItemStack itemStack) {
        // Do whatever is needed for your item here. The entityData part is not necessary anymore.
    }

    // Optional: You can add additional getter methods if you need them
    public ItemStack getItem() {
        // No need for entityData; return the current item here if needed
        return new ItemStack(HexcraftItems.BREW_OF_SPROUTING.get());
    }

    // Override this method, even though it's empty for now.
    @Override
    protected void defineSynchedData() {
        // No need for any custom data synchronization. You can leave this empty.
    }
}