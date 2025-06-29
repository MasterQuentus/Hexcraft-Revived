package net.masterquentus.hexcraftmod.block.entity.client;

import com.google.common.collect.ImmutableMap;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.entity.custom.HexcraftChestBlockEntity;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.ChestType;

import java.util.EnumMap;
import java.util.Map;

public class HexcraftChestRenderer<T extends HexcraftChestBlockEntity> extends ChestRenderer<T> {

    /**
     * We use a lazy holder so that MATERIALS is not initialized until first access.
     */
    private static class MaterialsHolder {
        static final Map<Block, EnumMap<ChestType, Material>> MATERIALS = createMaterials();
    }

    public static Map<Block, EnumMap<ChestType, Material>> getMaterials() {
        return MaterialsHolder.MATERIALS;
    }

    private static Map<Block, EnumMap<ChestType, Material>> createMaterials() {
        ImmutableMap.Builder<Block, EnumMap<ChestType, Material>> builder = ImmutableMap.builder();

        // Use try-catch to avoid crashing if a registry object is not present.
        try {
            builder.put(HexcraftBlocks.ALDER_CHEST.get(), chestMaterial("alder"));
        } catch (Exception e) {
            // Log the error if desired, but do not crash.
            System.err.println("Failed to register alder chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.BLOOD_OAK_CHEST.get(), chestMaterial("blood_oak"));
        } catch (Exception e) {
            System.err.println("Failed to register blood oak chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.CEDAR_CHEST.get(), chestMaterial("cedar"));
        } catch (Exception e) {
            System.err.println("Failed to register cedar chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.DISTORTED_CHEST.get(), chestMaterial("distorted"));
        } catch (Exception e) {
            System.err.println("Failed to register distorted chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.EBONY_CHEST.get(), chestMaterial("ebony"));
        } catch (Exception e) {
            System.err.println("Failed to register ebony chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.ECHO_WOOD_CHEST.get(), chestMaterial("echo_wood"));
        } catch (Exception e) {
            System.err.println("Failed to register echo wood chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.ELDER_CHEST.get(), chestMaterial("elder"));
        } catch (Exception e) {
            System.err.println("Failed to register elder chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.HAWTHORN_CHEST.get(), chestMaterial("hawthorn"));
        } catch (Exception e) {
            System.err.println("Failed to register hawthorn chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.HELL_BARK_CHEST.get(), chestMaterial("hell_bark"));
        } catch (Exception e) {
            System.err.println("Failed to register hell bark chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.JUNIPER_CHEST.get(), chestMaterial("juniper"));
        } catch (Exception e) {
            System.err.println("Failed to register juniper chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.ROWAN_CHEST.get(), chestMaterial("rowan"));
        } catch (Exception e) {
            System.err.println("Failed to register rowan chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.TWISTED_CHEST.get(), chestMaterial("twisted"));
        } catch (Exception e) {
            System.err.println("Failed to register twisted chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.WHITE_OAK_CHEST.get(), chestMaterial("white_oak"));
        } catch (Exception e) {
            System.err.println("Failed to register white oak chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.WILLOW_CHEST.get(), chestMaterial("willow"));
        } catch (Exception e) {
            System.err.println("Failed to register willow chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.WITCH_HAZEL_CHEST.get(), chestMaterial("witch_hazel"));
        } catch (Exception e) {
            System.err.println("Failed to register witch hazel chest material: " + e);
        }
        try {
            builder.put(HexcraftBlocks.WITCH_WOOD_CHEST.get(), chestMaterial("witch_wood"));
        } catch (Exception e) {
            System.err.println("Failed to register witch wood chest material: " + e);
        }

        try {
            builder.put(HexcraftBlocks.PHOENIX_CHEST.get(), chestMaterial("phoenix"));
        } catch (Exception e) {
            System.err.println("Failed to register phoenix wood chest material: " + e);
        }

        return builder.build();
    }

    public HexcraftChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Material getMaterial(T blockEntity, ChestType chestType) {
        EnumMap<ChestType, Material> map = getMaterials().get(blockEntity.getBlockState().getBlock());
        if (map == null) {
            return super.getMaterial(blockEntity, chestType);
        }
        Material material = map.get(chestType);
        return material != null ? material : super.getMaterial(blockEntity, chestType);
    }

    private static EnumMap<ChestType, Material> chestMaterial(String type) {
        EnumMap<ChestType, Material> map = new EnumMap<>(ChestType.class);
        // Here we assume that Sheets.CHEST_SHEET is the correct atlas.
        map.put(ChestType.SINGLE, new Material(Sheets.CHEST_SHEET,
                new ResourceLocation(HexcraftMod.MOD_ID, "entity/chest/" + type + "_normal")));
        map.put(ChestType.LEFT, new Material(Sheets.CHEST_SHEET,
                new ResourceLocation(HexcraftMod.MOD_ID, "entity/chest/" + type + "_normal_left")));
        map.put(ChestType.RIGHT, new Material(Sheets.CHEST_SHEET,
                new ResourceLocation(HexcraftMod.MOD_ID, "entity/chest/" + type + "_normal_right")));
        return map;
    }
}