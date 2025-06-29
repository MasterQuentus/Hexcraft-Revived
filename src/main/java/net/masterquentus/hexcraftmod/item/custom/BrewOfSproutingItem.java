package net.masterquentus.hexcraftmod.item.custom;

import net.masterquentus.hexcraftmod.item.entity.ThrownBrewOfSproutingEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BrewOfSproutingItem extends Item {

    public BrewOfSproutingItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!world.isClientSide) {
            // Create and throw the Brew of Sprouting entity
            throwBrew(world, player);

            // Shrink the item stack by 1
            stack.shrink(1);
        }

        // Return the result wrapped in InteractionResultHolder
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    private void throwBrew(Level world, Player player) {
        ThrownBrewOfSproutingEntity brewEntity = new ThrownBrewOfSproutingEntity(player, world);
        brewEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
        world.addFreshEntity(brewEntity);
    }
}