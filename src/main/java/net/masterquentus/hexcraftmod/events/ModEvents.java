package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.entity.*;
import net.masterquentus.hexcraftmod.entity.vampires.LilithEntity;
import net.masterquentus.hexcraftmod.client.layers.HexcraftModelLayers;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HexcraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(HexcraftEntities.VAMPIRE_EVOKER.get(), VampireEntity.setAttributes());
        event.put(HexcraftEntities.VAMPIRE_VINDICATOR.get(), VampireEntity.setAttributes());
        event.put(HexcraftEntities.VAMPIRE_PILLAGER.get(), VampireEntity.setAttributes());
        event.put(HexcraftEntities.VAMPIRE_PIGLIN.get(), VampireEntity.setAttributes());
        event.put(HexcraftEntities.LILITH.get(), LilithEntity.setAttributes());
        event.put(HexcraftEntities.WENDIGO.get(), WendigoEntity.setAttributes());
        event.put(HexcraftEntities.FAIRY.get(), FairyEntity.setAttributes());
        event.put(HexcraftEntities.DROWNED_SLIME.get(), DrownedSlimeEntity.setAttributes());
        event.put(HexcraftEntities.BASILISK.get(), BasiliskEntity.setAttributes());
        event.put(HexcraftEntities.SIREN.get(), SirenEntity.setAttributes());
        event.put(HexcraftEntities.BANSHEE.get(), BansheeEntity.setAttributes());

    }


    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(HexcraftModelLayers.EBONY_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.EBONY_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.BLOOD_OAK_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.BLOOD_OAK_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.HELL_BARK_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.HELL_BARK_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.WHITE_OAK_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.WHITE_OAK_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.ALDER_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.ALDER_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.WITCH_HAZEL_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.WITCH_HAZEL_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.WILLOW_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.WILLOW_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.HAWTHORN_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.HAWTHORN_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.CEDAR_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.CEDAR_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.DISTORTED_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.DISTORTED_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.ELDER_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.ELDER_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.JUNIPER_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.JUNIPER_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.ROWAN_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.ROWAN_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.TWISTED_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.TWISTED_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.WITCH_WOOD_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.WITCH_WOOD_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.ECHO_WOOD_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.ECHO_WOOD_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(HexcraftModelLayers.PHOENIX_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(HexcraftModelLayers.PHOENIX_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

    }
}
