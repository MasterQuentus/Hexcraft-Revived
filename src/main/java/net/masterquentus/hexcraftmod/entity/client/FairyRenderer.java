package net.masterquentus.hexcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.FairyEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FairyRenderer extends GeoEntityRenderer<FairyEntity> {
    public FairyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FairyModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FairyEntity entity) {
        int variant = entity.getVariant();
        return switch (variant) {
            case 1 -> new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/fairy_blue.png");
            case 2 -> new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/fairy_cyan.png");
            default -> new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/fairy.png");
        };
    }


    @Override
    public void render(FairyEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}