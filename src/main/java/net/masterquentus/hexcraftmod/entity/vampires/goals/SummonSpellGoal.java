package net.masterquentus.hexcraftmod.entity.vampires.goals;

import net.masterquentus.hexcraftmod.entity.vampires.VampireEvokerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class SummonSpellGoal extends Goal {
    private final VampireEvokerEntity evoker;
    private int summonCooldown;

    public SummonSpellGoal(VampireEvokerEntity evoker) {
        this.evoker = evoker;
        this.summonCooldown = 0;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return summonCooldown <= 0; // Can summon only if cooldown is over
    }

    @Override
    public void start() {
        summonCooldown = 200; // 10 seconds cooldown (200 ticks)
        evoker.setCastingSpell(true); // Play casting animation if needed
    }

    @Override
    public void tick() {
        if (evoker.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 2; i++) { // Summon 2 minions
                Vec3 summonPos = evoker.position().add(evoker.getRandom().nextInt(3) - 1, 0, evoker.getRandom().nextInt(3) - 1);
                Zombie minion = EntityType.ZOMBIE.create(serverLevel);
                if (minion != null) {
                    minion.moveTo(summonPos.x, summonPos.y, summonPos.z, evoker.getYRot(), evoker.getXRot());
                    minion.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(evoker.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                    serverLevel.addFreshEntity(minion);
                }
            }
        }
    }

    @Override
    public void stop() {
        evoker.setCastingSpell(false); // Stop casting animation
    }

    @Override
    public boolean canContinueToUse() {
        return false; // Only summon once per trigger
    }

}