package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.SirenEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SirenModel extends GeoModel<SirenEntity> {
    @Override
    public ResourceLocation getModelResource(SirenEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "geo/siren.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SirenEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/siren.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SirenEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "animations/siren.animation.json");
    }

    @Override
    public void setCustomAnimations(SirenEntity animatable, long instanceId, AnimationState<SirenEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}