package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.vampires.VampirePiglinEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class VampirePiglinModel extends GeoModel<VampirePiglinEntity> {
    @Override
    public ResourceLocation getModelResource(VampirePiglinEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "geo/vampire_piglin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VampirePiglinEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/vampires/vampire_piglin.png");
    }

    @Override
    public ResourceLocation getAnimationResource(VampirePiglinEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "animations/vampire_piglin.animation.json");
    }

    @Override
    public void setCustomAnimations(VampirePiglinEntity animatable, long instanceId, AnimationState<VampirePiglinEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
