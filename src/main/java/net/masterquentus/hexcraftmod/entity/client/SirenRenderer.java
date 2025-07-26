package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.SirenEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SirenRenderer extends GeoEntityRenderer<SirenEntity> {
    public SirenRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SirenModel());
    }

    @Override
    public ResourceLocation getTextureLocation(SirenEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/siren.png");
    }

    // ✅ DO NOT MARK THIS WITH @Override
    public RenderType getRenderType(SirenEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}