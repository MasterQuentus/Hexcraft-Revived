package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.vampires.VampireVindicatorEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class VampireVindicatorModel extends GeoModel<VampireVindicatorEntity> {
    @Override
    public ResourceLocation getModelResource(VampireVindicatorEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "geo/vampire_vindicator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VampireVindicatorEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/vampires/vampire_vindicator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(VampireVindicatorEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "animations/vampire_vindicator.animation.json");
    }

    @Override
    public void setCustomAnimations(VampireVindicatorEntity animatable, long instanceId, AnimationState<VampireVindicatorEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
