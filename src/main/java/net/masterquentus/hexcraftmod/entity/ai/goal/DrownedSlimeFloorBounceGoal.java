package net.masterquentus.hexcraftmod.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class DrownedSlimeFloorBounceGoal extends Goal {
    private final Mob mob;
    private int cooldown;

    public DrownedSlimeFloorBounceGoal(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Only bounce if in water AND standing on solid block (ocean floor)
        return mob.isInWater() && isOnFloor();
    }

    private boolean isOnFloor() {
        BlockPos posBelow = mob.blockPosition().below();
        BlockState blockBelow = mob.level().getBlockState(posBelow);
        return blockBelow.isSolidRender(mob.level(), posBelow);
    }

    @Override
    public void tick() {
        if (--cooldown <= 0) {
            cooldown = 20 + mob.getRandom().nextInt(20); // bounce every 1–2 seconds

            // Bounce upwards
            Vec3 motion = mob.getDeltaMovement();
            mob.setDeltaMovement(motion.x, 0.4, motion.z);
            mob.hasImpulse = true;

            // Random small horizontal move so they "bounce" around a bit
            double angle = mob.getRandom().nextDouble() * 2 * Math.PI;
            double dx = Math.cos(angle) * 0.2;
            double dz = Math.sin(angle) * 0.2;
            mob.setDeltaMovement(dx, 0.4, dz);
            mob.hasImpulse = true;
        }
    }
}