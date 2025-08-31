package net.masterquentus.hexcraftmod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.logging.LogUtils;
import net.masterquentus.hexcraftmod.block.entity.renderer.SacrificialPillarBlockEntityRenderer;
import net.masterquentus.hexcraftmod.block.events.VampireProgressionHandler;
import net.masterquentus.hexcraftmod.block.render.PandorasBoxRenderer;
import net.masterquentus.hexcraftmod.block.entity.client.HexcraftBoatRenderer;
import net.masterquentus.hexcraftmod.block.entity.client.HexcraftChestRenderer;
import net.masterquentus.hexcraftmod.block.events.PandoraMobLootHandler;
import net.masterquentus.hexcraftmod.block.render.SacrificialPillarRenderer;
import net.masterquentus.hexcraftmod.client.render.VampireHUDRenderer;
import net.masterquentus.hexcraftmod.comands.VampireCommand;
import net.masterquentus.hexcraftmod.config.HexcraftConfig;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.masterquentus.hexcraftmod.entity.HexcraftEntities;
import net.masterquentus.hexcraftmod.block.entity.HexcraftBlockEntities;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.entity.client.*;
import net.masterquentus.hexcraftmod.events.*;
import net.masterquentus.hexcraftmod.fluid.HexcraftFluidTypes;
import net.masterquentus.hexcraftmod.fluid.HexcraftFluids;
import net.masterquentus.hexcraftmod.item.HexcraftCreativeModTabs;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.masterquentus.hexcraftmod.loot.HexcraftLootModifier;
import net.masterquentus.hexcraftmod.magic.CapabilityEventHandler;
import net.masterquentus.hexcraftmod.magic.MagicStamina;
import net.masterquentus.hexcraftmod.magic.MagicStaminaProvider;
import net.masterquentus.hexcraftmod.magic.MagicStaminaTickHandler;
import net.masterquentus.hexcraftmod.packets.HexcraftModNetworking;
import net.masterquentus.hexcraftmod.recipe.HexcraftRecipeTypes;
import net.masterquentus.hexcraftmod.recipe.HexcraftRecipes;
import net.masterquentus.hexcraftmod.screens.HexcraftMenuTypes;
import net.masterquentus.hexcraftmod.screens.WitchesOvenScreen;
import net.masterquentus.hexcraftmod.spells.*;
import net.masterquentus.hexcraftmod.util.HexcraftWoodTypes;
import net.masterquentus.hexcraftmod.worldgen.biome.HexcraftTerraBlenderAPI;
import net.masterquentus.hexcraftmod.worldgen.tree.custom.HexcraftFoliagePlacers;
import net.masterquentus.hexcraftmod.worldgen.tree.custom.HexcraftTrunkPlacers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static net.masterquentus.hexcraftmod.HexcraftMod.MOD_ID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MOD_ID)
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HexcraftMod {
	public static final String MOD_ID = "hexcraftmod";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final Random RANDOM = new Random();
	public static final RandomSource RANDOMSOURCE = RandomSource.create();


	public static final ModelLayerLocation LAYER_CHEST = new ModelLayerLocation(new ResourceLocation(MOD_ID, "chest"), "main");
	public static final ModelLayerLocation LAYER_DOUBLE_CHEST_LEFT = new ModelLayerLocation(new ResourceLocation(MOD_ID, "double_chest_left"), "main");
	public static final ModelLayerLocation LAYER_DOUBLE_CHEST_RIGHT = new ModelLayerLocation(new ResourceLocation(MOD_ID, "double_chest_right"), "main");

	// Declare the modEventBus as a class-level field
	private final IEventBus modEventBus;

	public HexcraftMod() {
		// Initialize modEventBus in the constructor
		this.modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HexcraftConfig.CONFIG);


		// Register creative tabs, items, blocks, etc.
		HexcraftCreativeModTabs.register(modEventBus);
		HexcraftItems.register(modEventBus);
		HexcraftBlocks.register(modEventBus);
		HexcraftBlockEntities.register(modEventBus);
		HexcraftMenuTypes.register(modEventBus);
		HexcraftRecipes.register(modEventBus);
		HexcraftRecipeTypes.register(modEventBus);
		HexcraftLootModifier.register(modEventBus);
		HexcraftEntities.register(modEventBus);
		HexcraftTerraBlenderAPI.registerRegions();
		HexcraftFluidTypes.register(modEventBus);
		HexcraftFluids.register(modEventBus);
		HexcraftEffects.register(modEventBus);
		HexcraftFoliagePlacers.register(modEventBus);
		HexcraftTrunkPlacers.register(modEventBus);
		HexcraftModNetworking.register();

		// Register entity attributes and event handlers
		MinecraftForge.EVENT_BUS.addListener(HexcraftEntities::registerEntityAttributes);
		MinecraftForge.EVENT_BUS.register(new PandoraMobLootHandler());
		MinecraftForge.EVENT_BUS.register(PlayerEventHandler.class);
		MinecraftForge.EVENT_BUS.register(DamageEventHandler.class);
		MinecraftForge.EVENT_BUS.register(VampireFeedingHandler.class);
		MinecraftForge.EVENT_BUS.register(VampireProgressionHandler.class);
		MinecraftForge.EVENT_BUS.register(VampireHUDRenderer.class);
		MinecraftForge.EVENT_BUS.register(VampireBloodEventHandler.class);
		MinecraftForge.EVENT_BUS.register(VampireWeaknessHandler.class);
		MinecraftForge.EVENT_BUS.register(HexcraftEventClientBusEvents.class);
		MinecraftForge.EVENT_BUS.register(MagicStaminaTickHandler.class);
		MinecraftForge.EVENT_BUS.register(CapabilityEventHandler.class);


		// Register the commonSetup method
		modEventBus.addListener(this::commonSetup);

	}


	// Helper method to create ResourceLocation
	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	// Helper method to create translation keys
	public static String translationKey(String prefix, String suffix) {
		return String.format("%s.%s.%s", prefix, MOD_ID, suffix);
	}

	// Common setup (register block entities and blocks)
	private void commonSetup(final FMLCommonSetupEvent event) {
		SpellRegistry.registerSpell(new SanatoreSpell());
		SpellRegistry.registerSpell(new AquafortisSpell());
		SpellRegistry.registerSpell(new FulmenSpell());
		SpellRegistry.registerSpell(new AquafortisSpell());
		SpellRegistry.registerSpell(new FulmenSpell());
		SpellRegistry.registerSpell(new LuxAeternamSpell());
		SpellRegistry.registerSpell(new PhasmatosNaturalisSpell());
		SpellRegistry.registerSpell(new MotusSpell());
		SpellRegistry.registerSpell(new ClavemSpell());
		SpellRegistry.registerSpell(new ManusSpiritusSpell());
		SpellRegistry.registerSpell(new VitaReverteturSpell());
		SpellRegistry.registerSpell(new SiccitasSpell());
		SpellRegistry.registerSpell(new ClaustrumSpell());
		SpellRegistry.registerSpell(new ExpellereSpell());
		HexcraftItems.registerCompostables();
		event.enqueueWork(() -> {


			CapabilityManager.get(new CapabilityToken<MagicStamina>() {});  // No need to call register() manually
			CapabilityManager.get(new CapabilityToken<SiphonerData>() {});


			// Register the Leach Chest Block Entity
			HexcraftBlockEntities.BLOCK_ENTITIES.register(modEventBus);

			// Other setup logic, such as adding plants to flower pots, etc.
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.VAMPIRE_ORCHID.getId(),
					HexcraftBlocks.POTTED_VAMPIRE_ORCHID);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.BLOODY_ROSE.getId(),
					HexcraftBlocks.POTTED_BLOODY_ROSE);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.SOUL_FLOWER.getId(),
					HexcraftBlocks.POTTED_SOUL_FLOWER);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.BLOOD_MUSHROOM.getId(),
					HexcraftBlocks.POTTED_BLOOD_MUSHROOM);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.VILESHROOM.getId(),
					HexcraftBlocks.POTTED_VILESHROOM);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.GHOSTSHROOM.getId(),
					HexcraftBlocks.POTTED_GHOSTSHROOM);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.WISPY_COTTON.getId(),
					HexcraftBlocks.POTTED_WISPY_COTTON);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.DUSKROOT_LANTERN.getId(),
					HexcraftBlocks.POTTED_DUSKROOT_LANTERN);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.SCORCHSTALKS.getId(),
					HexcraftBlocks.POTTED_SCORCHASTALKS);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.EBONY_SAPLING.getId(),
					HexcraftBlocks.POTTED_EBONY_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.BLOOD_OAK_SAPLING.getId(),
					HexcraftBlocks.POTTED_BLOOD_OAK_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.HELL_BARK_SAPLING.getId(),
					HexcraftBlocks.POTTED_HELL_BARK_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.WHITE_OAK_SAPLING.getId(),
					HexcraftBlocks.POTTED_WHITE_OAK_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.ALDER_SAPLING.getId(),
					HexcraftBlocks.POTTED_ALDER_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.WITCH_HAZEL_SAPLING.getId(),
					HexcraftBlocks.POTTED_WITCH_HAZEL_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.WILLOW_SAPLING.getId(),
					HexcraftBlocks.POTTED_WILLOW_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.HAWTHORN_SAPLING.getId(),
					HexcraftBlocks.POTTED_HAWTHORN_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.CEDAR_SAPLING.getId(),
					HexcraftBlocks.POTTED_CEDAR_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.DISTORTED_SAPLING.getId(),
					HexcraftBlocks.POTTED_DISTORTED_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.ELDER_SAPLING.getId(),
					HexcraftBlocks.POTTED_ELDER_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.JUNIPER_SAPLING.getId(),
					HexcraftBlocks.POTTED_JUNIPER_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.ROWAN_SAPLING.getId(),
					HexcraftBlocks.POTTED_ROWAN_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.TWISTED_SAPLING.getId(),
					HexcraftBlocks.POTTED_TWISTED_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.WITCH_WOOD_SAPLING.getId(),
					HexcraftBlocks.POTTED_WITCH_WOOD_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.ECHO_WOOD_SAPLING.getId(),
					HexcraftBlocks.POTTED_ECHO_WOOD_SAPLING);

			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(HexcraftBlocks.PHOENIX_SAPLING.getId(),
					HexcraftBlocks.POTTED_PHOENIX_SAPLING);


			BlockEntityRenderers.register(HexcraftBlockEntities.CHEST.get(), HexcraftChestRenderer::new);


		});

		// Register projectiles and entities
		EntityRenderers.register(HexcraftEntities.VAMPIRIC_STAFF_PROJECTILE.get(), ThrownItemRenderer::new);
	}


	// Add creative tabs
	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			// Add creative items to tabs
		}
	}

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		CommandDispatcher<CommandSourceStack> dispatcher = event.getServer().getCommands().getDispatcher();

		// Existing command
		VampireCommand.register(dispatcher);

		// ✅ /stamina command
		dispatcher.register(Commands.literal("stamina")
				.executes(context -> {
					ServerPlayer player = context.getSource().getPlayerOrException();
					player.getCapability(MagicStaminaProvider.MAGIC_STAMINA_CAPABILITY).ifPresent(stamina -> {
						context.getSource().sendSuccess(
								() -> Component.literal("Stamina: " + stamina.getStamina()), false
						);
					});
					return 1;
				}));

		// ✅ /setsiphoner true|false command
		dispatcher.register(Commands.literal("setsiphoner")
				.then(Commands.argument("value", BoolArgumentType.bool())
						.executes(context -> {
							boolean value = BoolArgumentType.getBool(context, "value");
							ServerPlayer player = context.getSource().getPlayerOrException();
							player.getCapability(SiphonerDataProvider.SIPHONER_DATA_CAPABILITY).ifPresent(data -> {
								data.setSiphoner(value);
								context.getSource().sendSuccess(
										() -> Component.literal("Siphoner set to: " + value), false
								);
							});
							return 1;
						})));
	}

	@Mod.EventBusSubscriber(modid = HexcraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {

		@SubscribeEvent
		public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
			event.registerBlockEntityRenderer(HexcraftBlockEntities.SACRIFICIAL_PILLAR_ENTITY.get(), SacrificialPillarBlockEntityRenderer::new);
			// Register your custom chest block entity renderer
			event.registerBlockEntityRenderer(HexcraftBlockEntities.CHEST.get(), HexcraftChestRenderer::new);
		}


		@SubscribeEvent
		public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
			// Register the chest model layers used by your renderer
			event.registerLayerDefinition(LAYER_CHEST, HexcraftChestRenderer::createSingleBodyLayer);
			event.registerLayerDefinition(LAYER_DOUBLE_CHEST_LEFT, HexcraftChestRenderer::createDoubleBodyLeftLayer);
			event.registerLayerDefinition(LAYER_DOUBLE_CHEST_RIGHT, HexcraftChestRenderer::createDoubleBodyRightLayer);
		}

		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {

			// Entity renderers registration
			EntityRenderers.register(HexcraftEntities.VAMPIRE_EVOKER.get(), VampireEvokerRenderer::new);
			EntityRenderers.register(HexcraftEntities.VAMPIRE_VINDICATOR.get(), VampireVindicatorRenderer::new);
			EntityRenderers.register(HexcraftEntities.VAMPIRE_PILLAGER.get(), VampirePillagerRenderer::new);
			EntityRenderers.register(HexcraftEntities.VAMPIRE_PIGLIN.get(), VampirePiglinRenderer::new);
			EntityRenderers.register(HexcraftEntities.LILITH.get(), LilithRenderer::new);
			EntityRenderers.register(HexcraftEntities.WENDIGO.get(), WendigoRenderer::new);
			EntityRenderers.register(HexcraftEntities.FAIRY.get(), FairyRenderer::new);
			EntityRenderers.register(HexcraftEntities.DROWNED_SLIME.get(), DrownedSlimeRenderer::new);
			EntityRenderers.register(HexcraftEntities.BASILISK.get(), BasiliskRenderer::new);
			EntityRenderers.register(HexcraftEntities.SIREN.get(), SirenRenderer::new);
			EntityRenderers.register(HexcraftEntities.BANSHEE.get(), BansheeRenderer::new);
			EntityRenderers.register(HexcraftEntities.DARK_MAGIC_PROJECTILE.get(), DarkMagicProjectileRenderer::new);
			EntityRenderers.register(HexcraftEntities.THROWN_BREW_OF_SPROUTING.get(), ThrownBrewOfSproutingEntityRenderer::new);


			event.enqueueWork(() -> {
				// Add wood types for various blocks
				Sheets.addWoodType(HexcraftWoodTypes.EBONY);
				Sheets.addWoodType(HexcraftWoodTypes.BLOOD_OAK);
				Sheets.addWoodType(HexcraftWoodTypes.HELL_BARK);
				Sheets.addWoodType(HexcraftWoodTypes.WHITE_OAK);
				Sheets.addWoodType(HexcraftWoodTypes.ALDER);
				Sheets.addWoodType(HexcraftWoodTypes.WITCH_HAZEL);
				Sheets.addWoodType(HexcraftWoodTypes.WILLOW);
				Sheets.addWoodType(HexcraftWoodTypes.HAWTHORN);
				Sheets.addWoodType(HexcraftWoodTypes.CEDAR);
				Sheets.addWoodType(HexcraftWoodTypes.DISTORTED);
				Sheets.addWoodType(HexcraftWoodTypes.ELDER);
				Sheets.addWoodType(HexcraftWoodTypes.JUNIPER);
				Sheets.addWoodType(HexcraftWoodTypes.ROWAN);
				Sheets.addWoodType(HexcraftWoodTypes.TWISTED);
				Sheets.addWoodType(HexcraftWoodTypes.WITCH_WOOD);
				Sheets.addWoodType(HexcraftWoodTypes.ECHO_WOOD);
				Sheets.addWoodType(HexcraftWoodTypes.PHOENIX);

				// Register custom menu screens
				MenuScreens.register(HexcraftMenuTypes.WITCHES_OVEN_MENU.get(), WitchesOvenScreen::new);

				// Set fluid render types (e.g., for blood fluids)
				ItemBlockRenderTypes.setRenderLayer(HexcraftFluids.SOURCE_BLOOD.get(), RenderType.translucent());
				ItemBlockRenderTypes.setRenderLayer(HexcraftFluids.FLOWING_BLOOD.get(), RenderType.translucent());
				ItemBlockRenderTypes.setRenderLayer(HexcraftFluids.SOURCE_DEEP_WATER.get(), RenderType.translucent());
				ItemBlockRenderTypes.setRenderLayer(HexcraftFluids.FLOWING_DEEP_WATER.get(), RenderType.translucent());


				// Register boat renderers
				EntityRenderers.register(HexcraftEntities.HEXCRAFT_BOAT.get(), pContext -> new HexcraftBoatRenderer(pContext, false));
				EntityRenderers.register(HexcraftEntities.HEXCRAFT_CHEST_BOAT.get(), pContext -> new HexcraftBoatRenderer(pContext, true));

				// Register other block entity renderers
				BlockEntityRenderers.register(HexcraftBlockEntities.PANDORAS_BOX_ENTITY.get(), PandorasBoxRenderer::new);
				BlockEntityRenderers.register(HexcraftBlockEntities.SACRIFICIAL_PILLAR_ENTITY.get(), SacrificialPillarRenderer::new);
			});
		}
	}
}