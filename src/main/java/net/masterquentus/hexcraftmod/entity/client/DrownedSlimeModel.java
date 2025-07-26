package net.masterquentus.hexcraftmod.entity.client;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.DrownedSlimeEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DrownedSlimeModel extends GeoModel<DrownedSlimeEntity> {
    @Override
    public ResourceLocation getModelResource(DrownedSlimeEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "geo/drowned_slime.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DrownedSlimeEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "textures/entity/drowned_slime.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DrownedSlimeEntity animatable) {
        return new ResourceLocation(HexcraftMod.MOD_ID, "animations/drowned_slime.animation.json");
    }

    @Override
    public void setCustomAnimations(DrownedSlimeEntity animatable, long instanceId, AnimationState<DrownedSlimeEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}