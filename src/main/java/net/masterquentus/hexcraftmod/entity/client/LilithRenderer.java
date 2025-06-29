package net.masterquentus.hexcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.vampires.LilithEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LilithRenderer extends GeoEntityRenderer<LilithEntity> {
    public LilithRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LilithModel());
    }

    @Override
    public ResourceLocation getTextureLocation(LilithEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/vampires/lilith.png");
    }

    @Override
    public void render(LilithEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}