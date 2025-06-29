package net.masterquentus.hexcraftmod.item.custom;

import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TagLockKitFilled extends Item {
    public static final String TARGET_TAG = "entity";
    public static final String NAME_TAG = "entityName";

    public TagLockKitFilled(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        if (stack.hasTag() && stack.getTag().contains(NAME_TAG)) {
            tooltip.add(Component.literal("Bound to: " + stack.getTag().getString(NAME_TAG)).withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.literal("Unbound").withStyle(ChatFormatting.DARK_RED));
        }
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.EPIC;
    }

    // Retrieves the stored UUID
    public static UUID getUUID(ItemStack stack) {
        if (stack.getItem() == HexcraftItems.TAGLOCK_KIT_FULL.get() && stack.hasTag()) {
            CompoundTag tag = stack.getTag();
            if (tag.contains(TARGET_TAG)) {
                return NbtUtils.loadUUID(tag.get(TARGET_TAG));
            }
        }
        return null;
    }

    // Binds a player’s UUID and name to the taglock kit
    public static void bindToPlayer(ItemStack stack, Player player) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putUUID(TARGET_TAG, player.getUUID());
        tag.putString(NAME_TAG, player.getName().getString());
    }

    @Override
    public int getUseDuration(ItemStack item) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack item) {
        return UseAnim.DRINK;
    }
}