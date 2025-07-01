package net.masterquentus.hexcraftmod.block.entity.custom.liquid;

import net.masterquentus.hexcraftmod.util.HexcraftTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DeepWaterBlock extends LiquidBlock {
    public DeepWaterBlock(FlowingFluid fluid, BlockBehaviour.Properties properties) {
        super(fluid, properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, net.minecraft.world.entity.Entity entity) {
        super.entityInside(state, level, pos, entity);

        if (!level.isClientSide && entity instanceof LivingEntity living) {
            ItemStack helmet = living.getItemBySlot(EquipmentSlot.HEAD);

            boolean hasDepthMask = helmet.is(HexcraftTags.Items.DEEPSEER_HELM); // ✔ Correct

            if (!hasDepthMask) {
                living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0, true, false));
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1, true, false));
            }
        }
    }
}