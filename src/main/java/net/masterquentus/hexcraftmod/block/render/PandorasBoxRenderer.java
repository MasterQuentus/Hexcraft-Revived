package net.masterquentus.hexcraftmod.block.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.masterquentus.hexcraftmod.block.entity.custom.PandorasBoxBlockEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PandorasBoxRenderer implements BlockEntityRenderer<PandorasBoxBlockEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("hexcraftmod:textures/entity/pandoras_box.png");
    private final ModelPart lid;

    public PandorasBoxRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(ModelLayers.CHEST);
        this.lid = root.getChild("lid");
    }

    @Override
    public void render(PandorasBoxBlockEntity box, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int light, int overlay) {
        stack.pushPose();
        stack.translate(0.5, 0.5, 0.5);

        float animationProgress = Math.min(box.getAnimationTicks() / 40.0f, 1.0f); // Use getter
        lid.xRot = -(animationProgress * (float) Math.PI / 2); // Open animation

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(TEXTURE));
        lid.render(stack, vertexConsumer, light, overlay);
        stack.popPose();
    }
}