package net.masterquentus.hexcraftmod.potions;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.effects.BlisterEffect;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HexcraftPotions {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HexcraftMod.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, HexcraftMod.MOD_ID);

    // Custom potion effect: Blistering Vitality
    public static final RegistryObject<MobEffect> BLISTERING_VITALITY_EFFECT = EFFECTS.register("blistering_vitality",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xFFA500) {
                @Override
                public void applyEffectTick(LivingEntity entity, int amplifier) {
                    if (entity.getHealth() < entity.getMaxHealth()) {
                        entity.heal(1.0f);
                    }
                }
            });

    // Custom blister effect (harmful)
    public static final RegistryObject<MobEffect> BLISTER_EFFECT = EFFECTS.register("blister_effect",
            () -> new BlisterEffect(MobEffectCategory.HARMFUL, 0xFF6347));

    public static final RegistryObject<Potion> VERVAIN_POTION =
            POTIONS.register("vervain_potion", () -> new Potion(
                    new MobEffectInstance(MobEffects.WEAKNESS, 600, 1),
                    new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1),
                    new MobEffectInstance(HexcraftEffects.VERVAIN_BURN.get(), 400, 0) // Lasts 20 sec
            ));


}