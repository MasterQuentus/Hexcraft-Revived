package net.masterquentus.hexcraftmod.entity.ai.goal;

import java.util.function.Consumer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class UseAbilityGoal extends Goal {
    private final LivingEntity entity;
    private final Consumer<LivingEntity> ability;
    private final int cooldown;
    private int cooldownTimer;

    public UseAbilityGoal(LivingEntity entity, Consumer<LivingEntity> ability, int cooldown) {
        this.entity = entity;
        this.ability = ability;
        this.cooldown = cooldown;
        this.cooldownTimer = cooldown;
    }

    @Override
    public boolean canUse() {
        return entity.getHealth() < entity.getMaxHealth() * 0.7 && cooldownTimer <= 0;
    }

    @Override
    public void start() {
        ability.accept(entity);
        cooldownTimer = cooldown; // Reset cooldown
    }

    @Override
    public void tick() {
        if (cooldownTimer > 0) {
            cooldownTimer--;
        }
    }
}