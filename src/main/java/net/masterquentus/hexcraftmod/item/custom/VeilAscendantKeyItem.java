package net.masterquentus.hexcraftmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;


public class VeilAscendantKeyItem extends Item {
    public VeilAscendantKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide && player.getServer() != null) {
            ResourceKey<Level> prisonDimKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("hexcraftmod", "prison_dim"));
            ServerLevel prisonDim = player.getServer().getLevel(prisonDimKey);
            if (prisonDim != null) {
                player.changeDimension(prisonDim, new ITeleporter() {
                    @Override
                    public PortalInfo getPortalInfo(Entity entity, ServerLevel dest, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
                        return new PortalInfo(new Vec3(0.5, 70, 0.5), Vec3.ZERO, entity.getYRot(), entity.getXRot());
                    }
                });
            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}