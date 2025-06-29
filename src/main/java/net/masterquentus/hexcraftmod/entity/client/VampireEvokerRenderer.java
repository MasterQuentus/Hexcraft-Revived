package net.masterquentus.hexcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.vampires.VampireEvokerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VampireEvokerRenderer extends GeoEntityRenderer<VampireEvokerEntity> {
    public VampireEvokerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VampireEvokerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(VampireEvokerEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/vampires/vampire_evoker.png");
    }

    @Override
    public void render(VampireEvokerEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}