package net.masterquentus.hexcraftmod.potions;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class HexcraftPotionEffects {
    // Regeneration effect is already part of Minecraft, but if you want to customize it, you can.
    public static final MobEffect REGENERATION_EFFECT = MobEffects.REGENERATION;

    // Custom Armor Weakening effect
    public static final MobEffect ARMOR_WEAKENING = new MobEffect(MobEffectCategory.HARMFUL, 0x7F7F7F) {
        @Override
        public void applyEffectTick(LivingEntity entity, int amplifier) {
            // Loop through armor slots and reduce durability
            int i = 0;
            for (ItemStack armorItem : entity.getArmorSlots()) {
                if (!armorItem.isEmpty() && armorItem.getItem() != Items.AIR) {
                    // Reduce durability by 1 for each armor piece
                    armorItem.hurtAndBreak(1, entity, (e) -> e.broadcastBreakEvent(e.getUsedItemHand()));
                }
                i++;
            }
        }
    };
}