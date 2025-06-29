package net.masterquentus.hexcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.WendigoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WendigoRenderer extends GeoEntityRenderer<WendigoEntity> {
    public WendigoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WendigoModel());
    }

    @Override
    public ResourceLocation getTextureLocation(WendigoEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/wendigo.png");
    }

    @Override
    public void render(WendigoEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}