package net.masterquentus.hexcraftmod.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HexcraftEffects {
    // Create the DeferredRegister for effects
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "hexcraftmod");

    // 🩸 Vampire-Related Effects
    public static final RegistryObject<MobEffect> VAMPIRE_INFECTION = EFFECTS.register("vampire_infection",
            VampireInfectionEffect::new);

    public static final RegistryObject<MobEffect> VAMPIRE_TRANSFORMATION = EFFECTS.register("vampire_transformation",
            VampireTransformationEffect::new);

    // 🔥 Blister Effect
    public static final RegistryObject<MobEffect> BLISTER_EFFECT = EFFECTS.register("blister_effect",
            () -> new BlisterEffect(MobEffectCategory.HARMFUL, 0xFF6347));

    // 🌿 Vervain Burn
    public static final RegistryObject<MobEffect> VERVAIN_BURN = EFFECTS.register("vervain_burn",
            () -> new MobEffect(MobEffectCategory.HARMFUL, 0x2E8B57) {
                @Override
                public void applyEffectTick(LivingEntity entity, int amplifier) {
                    if (entity instanceof Player player) {
                        player.hurt(player.level().damageSources().magic(), 2.0F * (amplifier + 1));
                        player.setSecondsOnFire(3 + amplifier);
                    }
                }

                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return duration % 40 == 0;
                }
            });

    // 💪 Enhanced Strength
    public static final RegistryObject<MobEffect> ENHANCED_STRENGTH = EFFECTS.register("enhanced_strength",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x8B0000) {
                @Override
                public void applyEffectTick(LivingEntity entity, int amplifier) {
                    entity.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST, 40, amplifier));
                }

                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return true;
                }
            });

    // ⚡ Enhanced Speed
    public static final RegistryObject<MobEffect> ENHANCED_SPEED = EFFECTS.register("enhanced_speed",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xFF0000) {
                @Override
                public void applyEffectTick(LivingEntity entity, int amplifier) {
                    entity.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 40, amplifier));
                }

                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return true;
                }
            });

    // 👀 Blood Vision
    public static final RegistryObject<MobEffect> BLOOD_VISION = EFFECTS.register("blood_vision",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x800000) {
                @Override
                public void applyEffectTick(LivingEntity entity, int amplifier) {
                    if (entity instanceof Player player) {
                        player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.NIGHT_VISION, 200, 0));
                        player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.GLOWING, 200, 0));
                    }
                }

                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return duration % 200 == 0;
                }
            });

    // 🩸 Bloodlust Mode
    public static final RegistryObject<MobEffect> BLOODLUST = EFFECTS.register("bloodlust",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xB22222) {
                @Override
                public void applyEffectTick(LivingEntity entity, int amplifier) {
                    if (entity instanceof Player player) {
                        player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST, 400, 2));
                        player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 400, 2));
                        player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.HUNGER, 400, 2));
                    }
                }

                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return duration % 400 == 0;
                }
            });

    // 🛡️ Bloodhorn (like thorns, heals you when hit)
    public static final RegistryObject<MobEffect> BLOODHORN = EFFECTS.register("bloodhorn",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xAA0033) {
                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return false; // handled in event
                }
            });

    // ❄️ Paralyzed (immobilizes the mob/player)
    public static final RegistryObject<MobEffect> PARALYZED = EFFECTS.register("paralyzed",
            () -> new MobEffect(MobEffectCategory.HARMFUL, 0xAAAAFF) {
                @Override
                public void applyEffectTick(LivingEntity entity, int amplifier) {
                    entity.setDeltaMovement(0, 0, 0);
                    if (entity instanceof net.minecraft.world.entity.Mob mob) {
                        mob.setNoAi(true);
                    }
                }

                @Override
                public void removeAttributeModifiers(LivingEntity entity, net.minecraft.world.entity.ai.attributes.AttributeMap map, int amplifier) {
                    if (entity instanceof net.minecraft.world.entity.Mob mob) {
                        mob.setNoAi(false);
                    }
                    super.removeAttributeModifiers(entity, map, amplifier);
                }

                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return true;
                }
            });

    // 🌟 Magical Boost (for Mira Brew)
    public static final RegistryObject<MobEffect> MAGICAL_BOOST = EFFECTS.register("magical_boost",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x7FFF00) { // bright green color, change if you want
                @Override
                public void applyEffectTick(LivingEntity entity, int amplifier) {
                    // You can add your logic here later for magic boost once you add magic
                    // For now, just a placeholder - e.g., give Luck and Jump boosts
                    entity.addEffect(new MobEffectInstance(MobEffects.LUCK, 40, amplifier));
                    entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, amplifier));
                }
                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return true; // tick every tick to maintain effects
                }
            });

    // 🌀 Magic Drain (for Xerifae Brew)
    public static final RegistryObject<MobEffect> MAGIC_DRAIN = EFFECTS.register("magic_drain",
            () -> new MobEffect(MobEffectCategory.HARMFUL, 0x551A8B) { // purple-ish color
                @Override
                public void applyEffectTick(LivingEntity entity, int amplifier) {
                    // Placeholder logic for draining magic - for now, just weaken and slow
                    entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, amplifier));
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, amplifier));
                }
                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return true;
                }
            });

    // ✅ Register Effects
    public static void register(IEventBus modEventBus) {
        EFFECTS.register(modEventBus);
    }
}