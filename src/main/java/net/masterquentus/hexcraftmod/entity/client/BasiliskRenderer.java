package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.BasiliskEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BasiliskRenderer extends GeoEntityRenderer<BasiliskEntity> {
    public BasiliskRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BasiliskModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BasiliskEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/basilisk.png");
    }

    // ✅ DO NOT MARK THIS WITH @Override
    public RenderType getRenderType(BasiliskEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}