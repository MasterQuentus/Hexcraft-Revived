package net.masterquentus.hexcraftmod.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.worldgen.HexcraftBiomeModifers;
import net.masterquentus.hexcraftmod.worldgen.HexcraftConfiguredFeatures;
import net.masterquentus.hexcraftmod.worldgen.HexcraftPlacedFeatures;
import net.masterquentus.hexcraftmod.worldgen.biome.HexcraftBiomes;
import net.masterquentus.hexcraftmod.worldgen.dimension.HexcraftDimensions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

// Maybe Rename to HexcraftDataProvider or similar
public class HexcraftRegistrySetsProvider extends DatapackBuiltinEntriesProvider {
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.NOISE_SETTINGS, HexcraftDimensions::bootstrapNoiseSettings)
			.add(Registries.DIMENSION_TYPE, HexcraftDimensions::bootstrapType)
			.add(Registries.CONFIGURED_FEATURE, HexcraftConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, HexcraftPlacedFeatures::bootstrap)
			.add(ForgeRegistries.Keys.BIOME_MODIFIERS, HexcraftBiomeModifers::bootstrap)
			.add(Registries.BIOME, HexcraftBiomes::bootstrap)
			// MUST come after NOISE_SETTINGS, DIMENSION_TYPE, and BIOME
			.add(Registries.LEVEL_STEM, HexcraftDimensions::bootstrapStem)
			.add(Registries.DAMAGE_TYPE, HexcraftDamageTypes::bootstrap);

	public HexcraftRegistrySetsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of(HexcraftMod.MOD_ID));
	}
}