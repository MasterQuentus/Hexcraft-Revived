package net.masterquentus.hexcraftmod.entity.ai.goal;

import net.masterquentus.hexcraftmod.entity.vampires.VampirePiglinEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.EnumSet;
import java.util.List;

public class VampirePiglinPickupGoal extends Goal {
    private final VampirePiglinEntity vampirePiglin;
    private ItemEntity targetItem;
    private final double speed;

    public VampirePiglinPickupGoal(VampirePiglinEntity vampirePiglin, double speed) {
        this.vampirePiglin = vampirePiglin;
        this.speed = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        List<ItemEntity> items = this.vampirePiglin.level().getEntitiesOfClass(ItemEntity.class,
                this.vampirePiglin.getBoundingBox().inflate(8.0D),
                item -> vampirePiglin.wantsToPickUp(item.getItem()));

        if (items.isEmpty()) return false;

        this.targetItem = items.get(0);
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetItem != null && !this.targetItem.isRemoved();
    }

    @Override
    public void tick() {
        if (this.targetItem == null || this.targetItem.isRemoved()) return;

        this.vampirePiglin.getNavigation().moveTo(this.targetItem, speed);
        this.vampirePiglin.getLookControl().setLookAt(this.targetItem, 30.0F, 30.0F);

        if (this.vampirePiglin.distanceToSqr(this.targetItem) < 1.5D) {
            ItemStack stack = this.targetItem.getItem();

            if (this.vampirePiglin.wantsToPickUp(stack)) {
                ItemStack equipped = this.vampirePiglin.getMainHandItem();

                boolean canReplace = equipped.isEmpty() || this.vampirePiglin.isLovedItem(stack);

                if (canReplace) {
                    this.vampirePiglin.setItemSlot(EquipmentSlot.MAINHAND, stack.copy());
                    this.targetItem.discard();
                    this.vampirePiglin.callPickUpItem(this.targetItem); // ✅ use public wrapper
                }
            }

            this.targetItem = null;
        }
    }

    @Override
    public void stop() {
        this.targetItem = null;
    }
}