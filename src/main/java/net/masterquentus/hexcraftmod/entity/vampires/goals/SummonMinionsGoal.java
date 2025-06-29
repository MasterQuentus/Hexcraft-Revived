package net.masterquentus.hexcraftmod.entity.vampires.goals;

import net.masterquentus.hexcraftmod.entity.vampires.VampireEvokerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.Vec3;

public class SummonMinionsGoal extends Goal {
    private final VampireEvokerEntity evoker;
    private int summonCooldown;

    public SummonMinionsGoal(VampireEvokerEntity evoker) {
        this.evoker = evoker;
        this.summonCooldown = 0;
    }

    @Override
    public boolean canUse() {
        return summonCooldown <= 0 && evoker.getTarget() != null;
    }

    @Override
    public void start() {
        summonCooldown = 200; // 10 seconds cooldown
        summonMinions();
    }

    @Override
    public void tick() {
        summonCooldown--;
    }

    private void summonMinions() {
        if (evoker.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 2; i++) { // Summon 2 minions
                Vec3 summonPos = evoker.position().add(evoker.getRandom().nextInt(3) - 1, 0, evoker.getRandom().nextInt(3) - 1);
                Monster minion = evoker.getRandom().nextBoolean() ? EntityType.ZOMBIE.create(serverLevel) : EntityType.SKELETON.create(serverLevel);
                if (minion != null) {
                    minion.moveTo(summonPos.x, summonPos.y, summonPos.z, evoker.getYRot(), evoker.getXRot());
                    minion.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(evoker.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                    serverLevel.addFreshEntity(minion);
                }
            }
        }
    }
}