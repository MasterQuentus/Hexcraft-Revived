package net.masterquentus.hexcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.vampires.VampirePiglinEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VampirePiglinRenderer extends GeoEntityRenderer<VampirePiglinEntity> {
    public VampirePiglinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VampirePiglinModel());
    }

    @Override
    public ResourceLocation getTextureLocation(VampirePiglinEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/vampires/vampire_piglin.png");
    }

    @Override
    public void render(VampirePiglinEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}