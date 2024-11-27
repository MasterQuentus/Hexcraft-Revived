package net.masterquentus.hexcraftmod.datagen;

//import com.mojang.blaze3d.vertex.PoseStack;
//import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
//import net.masterquentus.hexcraftmod.block.custom.HexcraftChestBlock;
//import net.masterquentus.hexcraftmod.block.entity.HexcraftChestBlockEntity;
//import net.minecraft.Util;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.block.model.ItemTransforms;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.item.BlockItem;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.ChestBlock;
//import net.minecraft.world.level.block.EntityBlock;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ISTER extends BlockEntityWithoutLevelRenderer {
//
//    private final Map<Block, HexcraftChestBlockEntity> chestEntities = Util.make(new HashMap<>(), map -> {
//       makeInstance(map, HexcraftBlocks.CHEST_ALDER);
//        makeInstance(map, HexcraftBlocks.CHEST_BLOOD_OAK);
//        makeInstance(map, HexcraftBlocks.CHEST_CEDAR);
//        makeInstance(map, HexcraftBlocks.CHEST_DISTORTED);
//        makeInstance(map, HexcraftBlocks.CHEST_EBONY);
//        makeInstance(map, HexcraftBlocks.CHEST_ECHO_WOOD);
//        makeInstance(map, HexcraftBlocks.CHEST_ELDER);
//        makeInstance(map, HexcraftBlocks.CHEST_HAWTHORN);
//        makeInstance(map, HexcraftBlocks.CHEST_HELL_BARK);
//        makeInstance(map, HexcraftBlocks.CHEST_JUNIPER);
//        makeInstance(map, HexcraftBlocks.CHEST_ROWAN);
//        makeInstance(map, HexcraftBlocks.CHEST_TWISTED);
//        makeInstance(map, HexcraftBlocks.CHEST_WHITE_OAK);
//        makeInstance(map, HexcraftBlocks.CHEST_WILLOW);
//        makeInstance(map, HexcraftBlocks.CHEST_WITCH_HAZEL);
//        makeInstance(map, HexcraftBlocks.CHEST_WITCH_WOOD);
//    });
//
//    public ISTER() {
//        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
//    }
//
//    @Override
//    public void renderByItem(ItemStack stack, ItemTransforms.TransformType camera, PoseStack ms, MultiBufferSource buffers, int light, int overlay) {
//        Item item = stack.getItem();
//        if (item instanceof BlockItem blockItem) {
//            Block block = blockItem.getBlock();
//            if (block instanceof HexcraftChestBlock) {
//                Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(this.chestEntities.get(block), ms, buffers, light, overlay);
//            } else {
//                if (block instanceof EntityBlock be) {
//                   BlockEntity blockEntity = be.newBlockEntity(BlockPos.ZERO, block.defaultBlockState());
//                   if (blockEntity != null) {
//                        Minecraft.getInstance().getBlockEntityRenderDispatcher().getRenderer(blockEntity).render(null, 0, ms, buffers, light, overlay);
//                   }
//                }
//            }
//        }
//    }
//
//    public static void makeInstance(Map<Block, HexcraftChestBlockEntity> map, RegistryObject<? extends ChestBlock> registryObject) {
//        ChestBlock block = registryObject.get();
//
//        map.put(block, new HexcraftChestBlockEntity(BlockPos.ZERO, block.defaultBlockState()));
//    }
//}