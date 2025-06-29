package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.entity.FairyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FairyModel extends GeoModel<FairyEntity> {
    @Override
    public ResourceLocation getModelResource(FairyEntity entity) {
        return new ResourceLocation("hexcraftmod", "geo/fairy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FairyEntity entity) {
        return new ResourceLocation("hexcraftmod", "textures/entity/fairy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FairyEntity entity) {
        return new ResourceLocation("hexcraftmod", "animations/fairy.animation.json");
    }
}