package net.masterquentus.hexcraftmod.entity.ai.goal;

import net.masterquentus.hexcraftmod.entity.SirenEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class SirenSwimAttackGoal extends Goal {
    private final SirenEntity siren;
    private final double speed;

    public SirenSwimAttackGoal(SirenEntity siren, double speed) {
        this.siren = siren;
        this.speed = speed;
    }

    @Override
    public boolean canUse() {
        return siren.getTarget() != null && siren.isInWater();
    }

    @Override
    public void tick() {
        LivingEntity target = siren.getTarget();
        if (target != null) {
            siren.getLookControl().setLookAt(target, 30.0F, 30.0F);
            siren.getNavigation().moveTo(target, speed);
        }
    }
}