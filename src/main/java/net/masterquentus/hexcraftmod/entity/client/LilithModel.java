package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.vampires.LilithEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class LilithModel extends GeoModel<LilithEntity> {
    @Override
    public ResourceLocation getModelResource(LilithEntity object) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "geo/lilith.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LilithEntity object) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/lilith.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LilithEntity object) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "animations/lilith.animation.json");
    }

    @Override
    public void setCustomAnimations(LilithEntity animatable, long instanceId, AnimationState<LilithEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}