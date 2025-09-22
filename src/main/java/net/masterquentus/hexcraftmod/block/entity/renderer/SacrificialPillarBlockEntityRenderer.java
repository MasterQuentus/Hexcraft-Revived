package net.masterquentus.hexcraftmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.entity.SacrificialPillarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SacrificialPillarBlockEntityRenderer implements BlockEntityRenderer<SacrificialPillarBlockEntity> {

    public SacrificialPillarBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(SacrificialPillarBlockEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        Level level = entity.getLevel();
        if (level == null) return;

        // --- Render the stored item on the pillar ---
        ItemStack stack = entity.getStoredItem();
        if (!stack.isEmpty()) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

            poseStack.pushPose();
            poseStack.translate(0.5f, 1.3f, 0.5f);
            poseStack.scale(0.5f, 0.5f, 0.5f);
            poseStack.mulPose(Axis.YP.rotationDegrees(entity.getRenderingRotation()));

            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED,
                    getLightLevel(level, entity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY,
                    poseStack, bufferSource, level, 1);

            poseStack.popPose();
        }

        // --- Render ghost blocks if structure is incomplete ---
        List<String> errors = entity.getMultiBlockStructureErrors();
        if (!errors.isEmpty()) {
            for (String error : errors) {
                BlockPos ghostPos = parsePosFromError(error);
                BlockState ghostState = getGhostBlockState(error);

                if (ghostState != null) {
                    poseStack.pushPose();

                    // Translate relative to the pillar
                    poseStack.translate(
                            ghostPos.getX() - entity.getBlockPos().getX(),
                            ghostPos.getY() - entity.getBlockPos().getY(),
                            ghostPos.getZ() - entity.getBlockPos().getZ()
                    );

                    VertexConsumer buffer = bufferSource.getBuffer(RenderType.translucent());

                    Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                            ghostState, poseStack, bufferSource, packedLight, packedOverlay
                    );

                    poseStack.popPose();
                }
            }
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    private BlockState getGhostBlockState(String errorMessage) {
        if (errorMessage.contains("Eclipsium")) return HexcraftBlocks.ECLIPSIUM_BLOCK.get().defaultBlockState();
        if (errorMessage.contains("Soul Fire")) return Blocks.SOUL_FIRE.defaultBlockState();
        if (errorMessage.contains("Amethyst")) return Blocks.AMETHYST_BLOCK.defaultBlockState();
        if (errorMessage.contains("Candle")) return Blocks.WHITE_CANDLE.defaultBlockState();
        if (errorMessage.contains("Sacrificial Pillar")) return HexcraftBlocks.SACRIFICIAL_PILLAR.get().defaultBlockState();
        return null;
    }

    private BlockPos parsePosFromError(String error) {
        Matcher m = Pattern.compile("-?\\d+").matcher(error);
        int[] coords = new int[3];
        int i = 0;

        while (m.find() && i < 3) {
            coords[i++] = Integer.parseInt(m.group());
        }

        if (i != 3) return BlockPos.ZERO; // fallback
        return new BlockPos(coords[0], coords[1], coords[2]);
    }
}