package net.masterquentus.hexcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.vampires.VampirePillagerEntity;
import net.masterquentus.hexcraftmod.entity.vampires.VampireVindicatorEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VampirePillagerRenderer extends GeoEntityRenderer<VampirePillagerEntity> {
    public VampirePillagerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VampirePillagerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(VampirePillagerEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/vampires/vampire_pillager.png");
    }

    @Override
    public void render(VampirePillagerEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}