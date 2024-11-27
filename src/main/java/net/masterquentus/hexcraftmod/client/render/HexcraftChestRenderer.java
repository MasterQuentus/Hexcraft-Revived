package net.masterquentus.hexcraftmod.client.render;

//import com.google.common.collect.ImmutableMap;
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.masterquentus.hexcraftmod.HexcraftMod;
//import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
//import net.masterquentus.hexcraftmod.block.entity.custom.HexcraftChestBlockEntity;
//import net.minecraft.client.model.geom.ModelLayerLocation;
//import net.minecraft.client.model.geom.ModelPart;
//import net.minecraft.client.model.geom.PartPose;
//import net.minecraft.client.model.geom.builders.CubeListBuilder;
//import net.minecraft.client.model.geom.builders.LayerDefinition;
//import net.minecraft.client.model.geom.builders.MeshDefinition;
//import net.minecraft.client.model.geom.builders.PartDefinition;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.Sheets;
//import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
//import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
//import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
//import net.minecraft.client.renderer.blockentity.ChestRenderer;
//import net.minecraft.client.resources.model.Material;
//import net.minecraft.core.Direction;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.*;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.ChestBlockEntity;
//import net.minecraft.world.level.block.entity.LidBlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.properties.ChestType;
//
//import javax.annotation.Nonnull;
//import java.util.EnumMap;
//import java.util.Map;
//
//public class HexcraftChestRenderer<T extends HexcraftChestBlockEntity> extends ChestRenderer<T> {
//
//    public static final Map<Block, EnumMap<ChestType, Material>> MATERIALS;
//
//    static {
//        ImmutableMap.Builder<Block, EnumMap<ChestType, Material>> builder = ImmutableMap.builder();
//
//        builder.put(HexcraftBlocks.CHEST_ALDER.get(), chestMaterial("alder"));
//        builder.put(HexcraftBlocks.CHEST_BLOOD_OAK.get(), chestMaterial("blood_oak"));
//        builder.put(HexcraftBlocks.CHEST_CEDAR.get(), chestMaterial("cedar"));
//        builder.put(HexcraftBlocks.CHEST_DISTORTED.get(), chestMaterial("distorted"));
//        builder.put(HexcraftBlocks.CHEST_EBONY.get(), chestMaterial("ebony"));
//        builder.put(HexcraftBlocks.CHEST_ECHO_WOOD.get(), chestMaterial("echo_wood"));
//        builder.put(HexcraftBlocks.CHEST_ELDER.get(), chestMaterial("elder"));
//        builder.put(HexcraftBlocks.CHEST_HAWTHORN.get(), chestMaterial("hawthorn"));
//        builder.put(HexcraftBlocks.CHEST_HELL_BARK.get(), chestMaterial("hell_bark"));
//        builder.put(HexcraftBlocks.CHEST_JUNIPER.get(), chestMaterial("juniper"));
//        builder.put(HexcraftBlocks.CHEST_ROWAN.get(), chestMaterial("rowan"));
//        builder.put(HexcraftBlocks.CHEST_TWISTED.get(), chestMaterial("twisted"));
//        builder.put(HexcraftBlocks.CHEST_WHITE_OAK.get(), chestMaterial("white_oak"));
//        builder.put(HexcraftBlocks.CHEST_WILLOW.get(), chestMaterial("willow"));
//        builder.put(HexcraftBlocks.CHEST_WITCH_HAZEL.get(), chestMaterial("witch_hazel"));
//        builder.put(HexcraftBlocks.CHEST_WITCH_WOOD.get(), chestMaterial("witch_wood"));
//
//        MATERIALS = builder.build();
//    }
//
//    public HexcraftChestRenderer(BlockEntityRendererProvider.Context context) {
//        super(context);
//    }
//
//    @Override
//    protected Material getMaterial(T blockEntity, ChestType chestType) {
//        EnumMap<ChestType, Material> b = MATERIALS.get(blockEntity.getBlockState().getBlock());
//
//        if (b == null) return super.getMaterial(blockEntity, chestType);
//
//        Material material = b.get(chestType);
//
//        return material != null ? material : super.getMaterial(blockEntity, chestType);
//    }
//
//    private static EnumMap<ChestType, Material> chestMaterial(String type) {
//        EnumMap<ChestType, Material> map = new EnumMap<>(ChestType.class);
//
//        map.put(ChestType.SINGLE, new Material(Sheets.CHEST_SHEET,
//                new ResourceLocation(HexcraftMod.MOD_ID, "entity/chest/" + type + "_normal")));
//        map.put(ChestType.LEFT, new Material(Sheets.CHEST_SHEET,
//                new ResourceLocation(HexcraftMod.MOD_ID, "entity/chest/" + type + "_normal_left")));
//        map.put(ChestType.RIGHT, new Material(Sheets.CHEST_SHEET,
//                new ResourceLocation(HexcraftMod.MOD_ID, "entity/chest/" + type + "_normal_right")));
//
//        return map;
//    }
//
//}