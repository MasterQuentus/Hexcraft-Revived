package net.masterquentus.hexcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.masterquentus.hexcraftmod.entity.vampires.goals.DarkMagicProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DarkMagicProjectileRenderer extends EntityRenderer<DarkMagicProjectileEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/entity/fireball.png");

    public DarkMagicProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(DarkMagicProjectileEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(DarkMagicProjectileEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        // Renders a simple glowing sphere for now (can be changed to use a model)
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.energySwirl(getTextureLocation(entity), 0, 0));
        poseStack.pushPose();
        poseStack.scale(0.5F, 0.5F, 0.5F); // Adjust size if needed
        poseStack.translate(0, 0.1, 0);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
