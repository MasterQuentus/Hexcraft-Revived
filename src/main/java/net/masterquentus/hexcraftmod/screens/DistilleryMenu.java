package net.masterquentus.hexcraftmod.screens;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class DistilleryMenu extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;

    public DistilleryMenu(int id, Inventory playerInventory) {
        this(id, playerInventory, new SimpleContainer(4), new SimpleContainerData(1));
    }

    public DistilleryMenu(int id, Inventory playerInventory, Container container, ContainerData data) {
        super(HexcraftMenuTypes.DISTILLERY_MENU.get(), id);
        this.container = container;
        this.data = data;

        // Add Slots
        this.addSlot(new Slot(container, 0, 44, 17)); // Ingredient 1
        this.addSlot(new Slot(container, 1, 62, 17)); // Ingredient 2
        this.addSlot(new Slot(container, 2, 44, 53)); // Catalyst
        this.addSlot(new Slot(container, 3, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        // Add Player Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }

        this.addDataSlots(data);
    }

    // ✅ Added missing constructor to fix '::new' error
    public DistilleryMenu(int id, Inventory playerInventory, FriendlyByteBuf buf) {
        this(id, playerInventory, new SimpleContainer(4), new SimpleContainerData(1));
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY; // Auto-shifting logic can be added later
    }
}
