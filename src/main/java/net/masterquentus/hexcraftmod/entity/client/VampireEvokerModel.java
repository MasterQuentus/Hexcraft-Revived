package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.vampires.VampireEvokerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class VampireEvokerModel extends GeoModel<VampireEvokerEntity> {
    @Override
    public ResourceLocation getModelResource(VampireEvokerEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "geo/vampire_evoker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VampireEvokerEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/vampires/vampire_evoker.png");
    }

    @Override
    public ResourceLocation getAnimationResource(VampireEvokerEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "animations/vampire_evoker.animation.json");
    }

    @Override
    public void setCustomAnimations(VampireEvokerEntity animatable, long instanceId, AnimationState<VampireEvokerEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}