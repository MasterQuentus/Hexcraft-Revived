package net.masterquentus.hexcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.vampires.VampireVindicatorEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VampireVindicatorRenderer extends GeoEntityRenderer<VampireVindicatorEntity> {
    public VampireVindicatorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VampireVindicatorModel());
    }

    @Override
    public ResourceLocation getTextureLocation(VampireVindicatorEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/vampires/vampire_vindicator.png");
    }

    @Override
    public void render(VampireVindicatorEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}