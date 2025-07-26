package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.BansheeEntity;
import net.masterquentus.hexcraftmod.entity.BasiliskEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BansheeModel extends GeoModel<BansheeEntity> {
    @Override
    public ResourceLocation getModelResource(BansheeEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "geo/banshee.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BansheeEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/banshee.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BansheeEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "animations/banshee.animation.json");
    }

    @Override
    public void setCustomAnimations(BansheeEntity animatable, long instanceId, AnimationState<BansheeEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}