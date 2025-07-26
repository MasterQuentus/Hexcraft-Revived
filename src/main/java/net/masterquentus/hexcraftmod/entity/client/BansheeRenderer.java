package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.BansheeEntity;
import net.masterquentus.hexcraftmod.entity.BasiliskEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BansheeRenderer extends GeoEntityRenderer<BansheeEntity> {
    public BansheeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BansheeModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BansheeEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/banshee.png");
    }
}