package net.masterquentus.hexcraftmod.block.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.block.entity.SacrificialPillarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public class SacrificialPillarRenderer implements BlockEntityRenderer<SacrificialPillarBlockEntity> {
    private final ItemRenderer itemRenderer;

    public SacrificialPillarRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(SacrificialPillarBlockEntity entity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        ItemStack stack = entity.getStoredItem();
        if (!stack.isEmpty()) {
            poseStack.pushPose();

            // Adjust position to center of the block
            poseStack.translate(0.5, 1.0, 0.5); // Y-level controls how high the item floats

            // Rotate slowly
            long time = System.currentTimeMillis();
            poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees((time / 20) % 360));


            // Scale the item
            poseStack.scale(0.5f, 0.5f, 0.5f);

            // Render item
            poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees((time / 20) % 360));


            poseStack.popPose();
        }
    }
}