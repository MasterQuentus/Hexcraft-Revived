package net.masterquentus.hexcraftmod.block.entity.custom;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.entity.HexcraftBlockEntities;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class AltarTopBlockEntity extends BlockEntity {
    private ItemStack storedItem = ItemStack.EMPTY; // Holds the item on the altar
    private List<ItemStack> itemsOnAltar = new ArrayList<>();
    private int powerLevel = 0; // Power level of the altar

    public AltarTopBlockEntity(BlockPos pos, BlockState state) {
        super(HexcraftBlockEntities.ALTAR_TOP.get(), pos, state);
    }

    public void addItemToAltar(ItemStack item) {
        if (!item.isEmpty()) {
            itemsOnAltar.add(item);  // Add the item to the altar
            int boost = calculatePowerBoost(item);
            powerLevel += boost;  // Increase the power level based on the item
            HexcraftMod.LOGGER.info("Item added: " + ForgeRegistries.ITEMS.getKey(item.getItem()) + " Power Boost: " + boost + " Total Power: " + powerLevel);
            setChanged();  // Mark the BlockEntity as changed to trigger saving state
        }
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AltarTopBlockEntity(pos, state); // Ensure this is the correct BlockEntity
    }

    // ✅ Define a method to calculate power boost based on item type
    private int calculatePowerBoost(ItemStack item) {
        // Increase power based on item type (based on Witchery altar mechanics)
        if (item.is(Items.BONE)) {
            HexcraftMod.LOGGER.info("Power boost from Chalice: 10");
            return 10; // Witch Bone equivalent
        } else if (item.is(Items.WITHER_SKELETON_SKULL)) {
            HexcraftMod.LOGGER.info("Power boost from Chalice: 15");
            return 15; // Skull equivalent
        } else if (item.is(Items.CHISELED_STONE_BRICKS)) {
            return 12; // Chalice equivalent
        } else if (item.is(Items.FLOWER_POT)) {
            return 8; // Goblet equivalent
        } else if (item.is(Items.REDSTONE_TORCH)) {
            return 10; // Candelabra equivalent
        } else if (item.is(Items.SKELETON_SKULL)) {
            HexcraftMod.LOGGER.info("Power boost from Chalice: 15");
            return 15; // Skull (lesser version)
        } else if (item.is(Items.GHAST_TEAR)) {
            return 18; // Arthana equivalent
        } else if (item.is(Items.NETHER_STAR)) {
            return 25; // Best possible power item
        }
        return 5; // Default power boost
    }

    // ✅ Save item and power level to NBT
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("PowerLevel", this.powerLevel);
        ListTag itemListTag = new ListTag();
        for (ItemStack item : itemsOnAltar) {
            itemListTag.add(item.save(new CompoundTag()));
        }
        tag.put("ItemsOnAltar", itemListTag);
    }

    public void removeItemFromAltar(ItemStack item) {
        if (itemsOnAltar.contains(item)) {
            itemsOnAltar.remove(item);  // Remove the item from the altar
            int boost = calculatePowerBoost(item);
            powerLevel -= boost;  // Decrease power level
            HexcraftMod.LOGGER.info("Item removed: " + ForgeRegistries.ITEMS.getKey(item.getItem()) + " Power Reduction: " + boost + " Total Power: " + powerLevel);
            setChanged();  // Mark BlockEntity as changed
        }
    }

    // ✅ Load item and power level from NBT
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.powerLevel = tag.getInt("PowerLevel");
        ListTag itemListTag = tag.getList("ItemsOnAltar", 10); // 10 is the NBT tag type for compound tags
        for (int i = 0; i < itemListTag.size(); i++) {
            this.itemsOnAltar.add(ItemStack.of(itemListTag.getCompound(i)));
        }
    }
}