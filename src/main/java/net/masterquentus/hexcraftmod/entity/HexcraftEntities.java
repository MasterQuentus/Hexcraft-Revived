package net.masterquentus.hexcraftmod.entity;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.entity.boats.HexcraftBoatEntity;
import net.masterquentus.hexcraftmod.block.entity.boats.HexcraftChestBoatEntity;
import net.masterquentus.hexcraftmod.entity.vampires.*;
import net.masterquentus.hexcraftmod.entity.vampires.VampireEvokerEntity;
import net.masterquentus.hexcraftmod.entity.vampires.VampirePiglinEntity;
import net.masterquentus.hexcraftmod.entity.vampires.VampireVindicatorEntity;
import net.masterquentus.hexcraftmod.entity.vampires.goals.DarkMagicProjectileEntity;
import net.masterquentus.hexcraftmod.custom.projectile.VampiricStaffProjectileEntity;
import net.masterquentus.hexcraftmod.item.entity.ThrownBrewOfSproutingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static software.bernie.example.registry.EntityRegistry.ENTITIES;


public class HexcraftEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HexcraftMod.MOD_ID);


    public static final RegistryObject<EntityType<HexcraftBoatEntity>> HEXCRAFT_BOAT =
            ENTITY_TYPES.register("hexcraft_boat", () -> EntityType.Builder.<HexcraftBoatEntity>of(HexcraftBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("hexcraft_boat"));

    public static final RegistryObject<EntityType<HexcraftChestBoatEntity>> HEXCRAFT_CHEST_BOAT =
            ENTITY_TYPES.register("hexcraft_chest_boat", () -> EntityType.Builder.<HexcraftChestBoatEntity>of(HexcraftChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("hexcraft_chest_boat"));

    public static final RegistryObject<EntityType<VampiricStaffProjectileEntity>> VAMPIRIC_STAFF_PROJECTILE =
            ENTITY_TYPES.register("vampiric_staff_projectile",
                    () -> EntityType.Builder.<VampiricStaffProjectileEntity>of(VampiricStaffProjectileEntity::new, MobCategory.AMBIENT)
                            .sized(0.25f, 0.25f)
                            .build(new ResourceLocation(HexcraftMod.MOD_ID, "vampiric_staff_projectile").toString()));

    public static final RegistryObject<EntityType<DarkMagicProjectileEntity>> DARK_MAGIC_PROJECTILE =
            ENTITY_TYPES.register("dark_magic_projectile",
                    () -> EntityType.Builder.<DarkMagicProjectileEntity>of(DarkMagicProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F) // Size of the projectile
                            .clientTrackingRange(4) // Required for proper rendering
                            .updateInterval(10) // Update interval
                            .build("dark_magic_projectile"));

    public static final RegistryObject<EntityType<ThrownBrewOfSproutingEntity>> THROWN_BREW_OF_SPROUTING = ENTITY_TYPES.register("thrown_brew_of_sprouting",
            () -> EntityType.Builder.<ThrownBrewOfSproutingEntity>of(ThrownBrewOfSproutingEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)  // Set the size of the thrown entity
                    .build(new ResourceLocation(HexcraftMod.MOD_ID, "thrown_brew_of_sprouting").toString()));

    public static final RegistryObject<EntityType<VampireEvokerEntity>> VAMPIRE_EVOKER = ENTITY_TYPES.register(
            "vampire_evoker",
            () -> EntityType.Builder.of(VampireEvokerEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build(new ResourceLocation(HexcraftMod.MOD_ID, "vampire_evoker").toString()));

    public static final RegistryObject<EntityType<VampireVindicatorEntity>> VAMPIRE_VINDICATOR = ENTITY_TYPES.register(
            "vampire_vindicator",
            () -> EntityType.Builder.of(VampireVindicatorEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build(new ResourceLocation(HexcraftMod.MOD_ID, "vampire_vindicator").toString()));

    public static final RegistryObject<EntityType<VampirePillagerEntity>> VAMPIRE_PILLAGER = ENTITY_TYPES.register(
            "vampire_pillager",
            () -> EntityType.Builder.of(VampirePillagerEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build(new ResourceLocation(HexcraftMod.MOD_ID, "vampire_pillager").toString()));

    public static final RegistryObject<EntityType<VampirePiglinEntity>> VAMPIRE_PIGLIN = ENTITY_TYPES.register(
            "vampire_piglin",
            () -> EntityType.Builder.of(VampirePiglinEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build(new ResourceLocation(HexcraftMod.MOD_ID, "vampire_piglin").toString()));

    public static final RegistryObject<EntityType<LilithEntity>> LILITH = ENTITIES.register("lilith",
            () -> EntityType.Builder.of(LilithEntity::new, MobCategory.MONSTER)
                    .sized(0.8F, 2.5F)
                    .build(new ResourceLocation(HexcraftMod.MOD_ID, "lilith").toString()));

    public static final RegistryObject<EntityType<WendigoEntity>> WENDIGO = ENTITIES.register("wendigo",
            () -> EntityType.Builder.of(WendigoEntity::new, MobCategory.MONSTER)
                    .sized(0.8F, 2.0F)
                    .build(new ResourceLocation(HexcraftMod.MOD_ID, "wendigo").toString()));

    public static final RegistryObject<EntityType<FairyEntity>> FAIRY = ENTITIES.register("fairy",
            () -> EntityType.Builder.of(FairyEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.9F)
                    .clientTrackingRange(10)
                    .build("fairy"));

    public static final RegistryObject<EntityType<DrownedSlimeEntity>> DROWNED_SLIME = ENTITIES.register("drowned_slime",
            () -> EntityType.Builder.of(DrownedSlimeEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 1.0F)
                    .clientTrackingRange(10)
                    .build("drowned_slime"));

    public static final RegistryObject<EntityType<BasiliskEntity>> BASILISK = ENTITY_TYPES.register(
            "basilisk",
            () -> EntityType.Builder.of(BasiliskEntity::new, MobCategory.MONSTER)
                    .sized(1.7F, 2.0F)
                    .build(new ResourceLocation(HexcraftMod.MOD_ID, "basilisk").toString()));

    public static final RegistryObject<EntityType<SirenEntity>> SIREN = ENTITY_TYPES.register(
            "siren",
            () -> EntityType.Builder.of(SirenEntity::new, MobCategory.MONSTER)
                    .sized(0.8F, 1.9F)
                    .build(new ResourceLocation(HexcraftMod.MOD_ID, "siren").toString()));

    public static final RegistryObject<EntityType<BansheeEntity>> BANSHEE = ENTITY_TYPES.register(
            "banshee",
            () -> EntityType.Builder.of(BansheeEntity::new, MobCategory.MONSTER)
                    .sized(0.8F, 2.0F) // Adjust width/height as needed
                    .build(new ResourceLocation(HexcraftMod.MOD_ID, "banshee").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    // ✅ **Register Entity Attributes**
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(VAMPIRE_EVOKER.get(), VampireEntity.createMobAttributes().build());
        event.put(VAMPIRE_VINDICATOR.get(), VampireEntity.createMobAttributes().build());
        event.put(VAMPIRE_PIGLIN.get(), VampireEntity.createMobAttributes().build());
    }
}
