package net.masterquentus.hexcraftmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.entity.SacrificialPillarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.BiConsumer;

public class SacrificialPillarBlockEntityRenderer implements BlockEntityRenderer<SacrificialPillarBlockEntity> {
    public SacrificialPillarBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(SacrificialPillarBlockEntity pillarEntity, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (pillarEntity.getLevel() == null) return;

        BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
        Level level = pillarEntity.getLevel();

        // Ritual center is the block above the current pillar entity block position
        BlockPos ritualCenter = pillarEntity.getBlockPos().above();

        // BlockStates for ghost rendering
        BlockState eclipsium = HexcraftBlocks.ECLIPSIUM_BLOCK.get().defaultBlockState();
        BlockState amethyst = Blocks.AMETHYST_BLOCK.defaultBlockState();
        BlockState candle = Blocks.CANDLE.defaultBlockState();
        BlockState soulFire = Blocks.SOUL_FIRE.defaultBlockState();
        BlockState pillar = HexcraftBlocks.SACRIFICIAL_PILLAR.get().defaultBlockState();

        // Helper to render ghost blocks relative to ritual center
        BiConsumer<BlockState, BlockPos> renderGhost = (state, pos) -> {
            renderGhostBlock(blockRenderer, state, pos, poseStack, bufferSource);
        };

        // Eclipsium blocks (center + 4 around)
        renderGhost.accept(eclipsium, ritualCenter);
        renderGhost.accept(eclipsium, ritualCenter.north(2));
        renderGhost.accept(eclipsium, ritualCenter.south(2));
        renderGhost.accept(eclipsium, ritualCenter.east(2));
        renderGhost.accept(eclipsium, ritualCenter.west(2));

        // Soul Fire (must be on soul sand at same Y)
        renderGhost.accept(soulFire, ritualCenter.north());
        renderGhost.accept(soulFire, ritualCenter.south());
        renderGhost.accept(soulFire, ritualCenter.east());
        renderGhost.accept(soulFire, ritualCenter.west());

        // Amethyst blocks at diagonals (Y same)
        renderGhost.accept(amethyst, ritualCenter.north(1).east(1));
        renderGhost.accept(amethyst, ritualCenter.north(1).west(1));
        renderGhost.accept(amethyst, ritualCenter.south(1).east(1));
        renderGhost.accept(amethyst, ritualCenter.south(1).west(1));

        // Candles at corners (Y same or Y+1)
        renderGhost.accept(candle, ritualCenter.north(2).east(2));
        renderGhost.accept(candle, ritualCenter.north(2).west(2));
        renderGhost.accept(candle, ritualCenter.south(2).east(2));
        renderGhost.accept(candle, ritualCenter.south(2).west(2));

        // Sacrificial pillars at distance 3 (Y same or Y+1)
        // We'll check both Y and Y+1 for ghost rendering (render both so player sees options)
        List<BlockPos> pillarPositions = List.of(
                ritualCenter.north(3),
                ritualCenter.north(3).above(),
                ritualCenter.south(3),
                ritualCenter.south(3).above(),
                ritualCenter.east(3),
                ritualCenter.east(3).above(),
                ritualCenter.west(3),
                ritualCenter.west(3).above()
        );
        for (BlockPos pos : pillarPositions) {
            renderGhost.accept(pillar, pos);
        }

        // **Render floating items above sacrificial pillars (ghost items)**
        for (BlockPos pos : pillarPositions) {
            // Check if the actual block entity exists and has stored item
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof SacrificialPillarBlockEntity sacPillar) {
                ItemStack stored = sacPillar.getStoredItem();
                if (!stored.isEmpty()) {
                    poseStack.pushPose();

                    // Translate to the pillar position relative to camera
                    double camX = Minecraft.getInstance().getCameraEntity().getX();
                    double camY = Minecraft.getInstance().getCameraEntity().getY();
                    double camZ = Minecraft.getInstance().getCameraEntity().getZ();

                    double dx = pos.getX() + 0.5 - camX;
                    double dy = pos.getY() + 1.15 - camY; // floating slightly above the pillar
                    double dz = pos.getZ() + 0.5 - camZ;

                    poseStack.translate(dx, dy, dz);
                    poseStack.scale(0.5f, 0.5f, 0.5f);

                    // Optional: rotate the item for effect
                    float rotation = sacPillar.getRenderingRotation();
                    poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

                    Minecraft.getInstance().getItemRenderer().renderStatic(
                            stored,
                            ItemDisplayContext.FIXED,
                            getLightLevel(level, pos),
                            OverlayTexture.NO_OVERLAY,
                            poseStack,
                            bufferSource,
                            level,
                            0
                    );

                    poseStack.popPose();
                }
            }
        }
    }

    private void renderGhostBlock(BlockRenderDispatcher renderer, BlockState state, BlockPos pos,
                                  PoseStack poseStack, MultiBufferSource buffer) {
        poseStack.pushPose();

        // Translate relative to camera for world space rendering
        double camX = Minecraft.getInstance().getCameraEntity().getX();
        double camY = Minecraft.getInstance().getCameraEntity().getY();
        double camZ = Minecraft.getInstance().getCameraEntity().getZ();

        poseStack.translate(pos.getX() - camX, pos.getY() - camY, pos.getZ() - camZ);

        // Render block with full brightness and no overlay
        renderer.renderSingleBlock(state, poseStack, buffer, 15728880, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}