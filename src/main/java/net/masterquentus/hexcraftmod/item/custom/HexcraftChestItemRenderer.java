package net.masterquentus.hexcraftmod.item.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.custom.HexcraftChestBlock;
import net.masterquentus.hexcraftmod.block.entity.custom.HexcraftChestBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class HexcraftChestItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final Map<Block, BlockEntity> chestEntities = new HashMap<>();

    public HexcraftChestItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        // Initialize the chest entities for each chest block type
        makeInstance(HexcraftBlocks.ALDER_CHEST);
        makeInstance(HexcraftBlocks.BLOOD_OAK_CHEST);
        makeInstance(HexcraftBlocks.CEDAR_CHEST);
        makeInstance(HexcraftBlocks.DISTORTED_CHEST);
        makeInstance(HexcraftBlocks.EBONY_CHEST);
        makeInstance(HexcraftBlocks.ECHO_WOOD_CHEST);
        makeInstance(HexcraftBlocks.ELDER_CHEST);
        makeInstance(HexcraftBlocks.HAWTHORN_CHEST);
        makeInstance(HexcraftBlocks.HELL_BARK_CHEST);
        makeInstance(HexcraftBlocks.JUNIPER_CHEST);
        makeInstance(HexcraftBlocks.ROWAN_CHEST);
        makeInstance(HexcraftBlocks.TWISTED_CHEST);
        makeInstance(HexcraftBlocks.WHITE_OAK_CHEST);
        makeInstance(HexcraftBlocks.WILLOW_CHEST);
        makeInstance(HexcraftBlocks.WITCH_HAZEL_CHEST);
        makeInstance(HexcraftBlocks.WITCH_WOOD_CHEST);
    }

    public void renderByItem(ItemStack stack, PoseStack ms, MultiBufferSource buffers, int light, int overlay) {
        Item item = stack.getItem();
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            if (block instanceof HexcraftChestBlock) {
                BlockEntity blockEntity = chestEntities.get(block);
                if (blockEntity != null) {
                    // Render the custom chest block item with its entity data
                    BlockEntityRenderDispatcher rendererDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
                    BlockEntityRenderer<BlockEntity> renderer = rendererDispatcher.getRenderer(blockEntity);
                    if (renderer != null) {
                        renderer.render(blockEntity, 0, ms, buffers, light, overlay);
                    }
                }
            }
        }
    }

    private void makeInstance(RegistryObject<? extends Block> registryObject) {
        Block block = registryObject.get();
        // Create a BlockEntity instance and store it
        BlockEntity blockEntity = new HexcraftChestBlockEntity(BlockPos.ZERO, block.defaultBlockState());
        chestEntities.put(block, blockEntity);
    }
}