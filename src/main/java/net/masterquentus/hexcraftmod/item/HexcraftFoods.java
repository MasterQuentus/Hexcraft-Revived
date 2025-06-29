package net.masterquentus.hexcraftmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class HexcraftFoods {
    public static final FoodProperties JUNIPER_BERRIES = new FoodProperties.Builder().alwaysEat().saturationMod(0.1F)
            .nutrition(2).fast().build();

    public static final FoodProperties BLOOD_BERRIES = new FoodProperties.Builder().alwaysEat().saturationMod(0.1F)
            .nutrition(2).fast().build();

    public static final FoodProperties BLOOD_APPLE = new FoodProperties.Builder().alwaysEat().saturationMod(0.3F)
            .nutrition(4).fast().build();

    public static final FoodProperties BLISTER_CACTUS_FRUIT = new FoodProperties.Builder().nutrition(2).fast()
            .saturationMod(0.3f).effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0), 0.8f)
        .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 600, 0), 0.7f).build();

    public static final FoodProperties COOKED_BLISTER_FRUIT = new FoodProperties.Builder()
            .nutrition(6) // Increases nutrition to make it more valuable
            .saturationMod(0.8f) // Better saturation than raw
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2400, 0), 1.0f) // Fire resistance for 2 minutes (2400 ticks)
            .build();

    public static final FoodProperties LIVING_KELP_SALAD_FOOD = new FoodProperties.Builder()
            .nutrition(6) // Restores 6 hunger points
            .saturationMod(0.6f) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 600, 0), 1.0f) // Water Breathing for 30 seconds
            .effect(() -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 600, 0), 1.0f) // Swim Speed Boost for 30 seconds
            .build();
}
