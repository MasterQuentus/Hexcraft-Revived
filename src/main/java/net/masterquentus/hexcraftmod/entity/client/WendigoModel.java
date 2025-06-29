package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.WendigoEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WendigoModel extends GeoModel<WendigoEntity> {
    @Override
    public ResourceLocation getModelResource(WendigoEntity object) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "geo/wendigo.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WendigoEntity object) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/wendigo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WendigoEntity object) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "animations/wendigo.animation.json");
    }

    @Override
    public void setCustomAnimations(WendigoEntity animatable, long instanceId, AnimationState<WendigoEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
