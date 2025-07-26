package net.masterquentus.hexcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.DrownedSlimeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DrownedSlimeRenderer extends GeoEntityRenderer<DrownedSlimeEntity> {
    public DrownedSlimeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DrownedSlimeModel());
    }

    @Override
    public ResourceLocation getTextureLocation(DrownedSlimeEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/drowned_slime.png");
    }

    // ✅ DO NOT MARK THIS WITH @Override
    public RenderType getRenderType(DrownedSlimeEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}