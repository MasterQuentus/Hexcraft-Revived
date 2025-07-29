package net.masterquentus.hexcraftmod.entity.ai.goal;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class SirenRandomSwimGoal extends Goal {
    private final Mob mob;
    private final double speed;
    private int cooldown;

    public SirenRandomSwimGoal(Mob mob, double speed) {
        this.mob = mob;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return mob.isInWater() && mob.getRandom().nextInt(20) == 0;
    }

    @Override
    public void start() {
        double dx = (mob.getX() + (mob.getRandom().nextDouble() - 0.5) * 16);
        double dy = mob.getY() + (mob.getRandom().nextDouble() - 0.5) * 8;
        double dz = (mob.getZ() + (mob.getRandom().nextDouble() - 0.5) * 16);
        mob.getNavigation().moveTo(dx, dy, dz, speed);
    }

    @Override
    public boolean canContinueToUse() {
        return !mob.getNavigation().isDone() && mob.isInWater();
    }

    @Override
    public void tick() {
        if (mob.getDeltaMovement().y < -0.1) {
            mob.setDeltaMovement(mob.getDeltaMovement().add(0, 0.05, 0));
        }
        super.tick();
    }
}