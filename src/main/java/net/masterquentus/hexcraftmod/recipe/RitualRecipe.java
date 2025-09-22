package net.masterquentus.hexcraftmod.recipe;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashSet;
import java.util.List;

public class RitualRecipe {
    private final Item centerItem;
    private final List<Item> sacrificialItems;
    private final ItemStack resultItem;
    private final EntityType<?> resultEntity;

    // Constructor for item result
    public RitualRecipe(Item centerItem, List<Item> sacrificialItems, ItemStack resultItem) {
        this.centerItem = centerItem;
        this.sacrificialItems = sacrificialItems;
        this.resultItem = resultItem;
        this.resultEntity = null;
    }

    // Constructor for entity result
    public RitualRecipe(Item centerItem, List<Item> sacrificialItems, EntityType<?> resultEntity) {
        this.centerItem = centerItem;
        this.sacrificialItems = sacrificialItems;
        this.resultItem = ItemStack.EMPTY;
        this.resultEntity = resultEntity;
    }

    public boolean matches(Item center, List<Item> pillars) {
        if (center != centerItem) return false;
        return new HashSet<>(pillars).containsAll(sacrificialItems)
                && sacrificialItems.size() == pillars.size();
    }

    public boolean isEntityRecipe() {
        return resultEntity != null;
    }

    public ItemStack getResultItem() {
        return resultItem;
    }

    public EntityType<?> getResultEntity() {
        return resultEntity;
    }
}