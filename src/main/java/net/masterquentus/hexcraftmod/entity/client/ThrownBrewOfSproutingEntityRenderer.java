package net.masterquentus.hexcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.item.entity.ThrownBrewOfSproutingEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ThrownBrewOfSproutingEntityRenderer extends EntityRenderer<ThrownBrewOfSproutingEntity> {

    public ThrownBrewOfSproutingEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(ThrownBrewOfSproutingEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
        // You can add custom rendering logic here if needed.
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownBrewOfSproutingEntity entity) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/thrown_brew_of_sprouting.png");  // Adjust the texture path
    }
}