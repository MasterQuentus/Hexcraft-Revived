package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.entity.HexcraftBlockEntities;
import net.masterquentus.hexcraftmod.block.entity.client.HexcraftChestRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HexcraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HexcraftEventClientBusEvents {

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {


        event.registerBlockEntityRenderer(HexcraftBlockEntities.Hexcraft_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(HexcraftBlockEntities.Hexcraft_HANGING_SIGN.get(), HangingSignRenderer::new);
        event.registerBlockEntityRenderer(HexcraftBlockEntities.CHEST.get(), HexcraftChestRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BlockColors blockColors = Minecraft.getInstance().getBlockColors();
            ItemColors itemColors = Minecraft.getInstance().getItemColors();

            // **Ebony Leaves Biome Coloring (Lighter)**
            blockColors.register((state, world, pos, tintIndex) -> {
                if (world != null && pos != null) {
                    return BiomeColors.getAverageFoliageColor(world, pos);
                }
                return 0x98D856; // Lighter Green Default (Adjust as needed)
            }, HexcraftBlocks.EBONY_LEAVES.get());

            // **Inventory color for Ebony Leaves (Lighter Green)**
            itemColors.register((stack, tintIndex) -> 0x98D856, HexcraftBlocks.EBONY_LEAVES.get().asItem());

            // **Witch Hazel Leaves Biome Coloring (Lighter)**
            blockColors.register((state, world, pos, tintIndex) -> {
                if (world != null && pos != null) {
                    return BiomeColors.getAverageFoliageColor(world, pos);
                }
                return 0x77CC44; // Brighter Green Default (Adjust as needed)
            }, HexcraftBlocks.WITCH_HAZEL_LEAVES.get());

            // **Inventory color for Witch Hazel Leaves (Lighter)**
            itemColors.register((stack, tintIndex) -> 0x77CC44, HexcraftBlocks.WITCH_HAZEL_LEAVES.get().asItem());

            // **Make Leaf Piles Change Color Like the Leaves**
            blockColors.register((state, world, pos, tintIndex) -> {
                if (world != null && pos != null) {
                    return BiomeColors.getAverageFoliageColor(world, pos);
                }
                return 0xA0C850; // Default color for leaf piles
            }, HexcraftBlocks.EBONY_LEAVES_PILE.get(), HexcraftBlocks.WITCH_HAZEL_LEAVES_PILE.get());

            itemColors.register((stack, tintIndex) -> 0xA0C850,
                    HexcraftBlocks.EBONY_LEAVES_PILE.get().asItem(),
                    HexcraftBlocks.WITCH_HAZEL_LEAVES_PILE.get().asItem());
        });
    }
}