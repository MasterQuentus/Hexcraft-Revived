package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.entity.FairyEntity;
import net.masterquentus.hexcraftmod.entity.HexcraftEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PixieLanternBlock extends LanternBlock {

    public PixieLanternBlock(Properties properties) {
        super(properties.sound(SoundType.LANTERN).lightLevel((state) -> 10));
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (level.isClientSide) {
            for (int i = 0; i < 3; i++) {
                double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.3;
                double y = pos.getY() + 0.7 + (random.nextDouble() - 0.5) * 0.3;
                double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.3;
                level.addParticle(net.minecraft.core.particles.ParticleTypes.ENCHANT, x, y, z, 0, 0.01, 0);
            }
        }
    }

    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) {
            summonPixie(level, pos);
        }
    }

    private void summonPixie(Level level, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            FairyEntity pixie = new FairyEntity(HexcraftEntities.FAIRY.get(), level);
            pixie.setPos(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
            pixie.finalizeSpawn(serverLevel, level.getCurrentDifficultyAt(pos), MobSpawnType.MOB_SUMMONED, null, null);
            level.addFreshEntity(pixie);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, net.minecraft.world.phys.BlockHitResult hit) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 1, true, false)); // Jump Boost II for 10 sec
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1, true, false)); // Speed II for 10 sec

            // Play a magical sound when used
            level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 1.0f, 1.2f);

            // Emit particles when right-clicked
            for (int i = 0; i < 5; i++) {
                double x = pos.getX() + 0.5 + (level.random.nextDouble() - 0.5) * 0.3;
                double y = pos.getY() + 0.7 + (level.random.nextDouble() - 0.5) * 0.3;
                double z = pos.getZ() + 0.5 + (level.random.nextDouble() - 0.5) * 0.3;
                level.addParticle(net.minecraft.core.particles.ParticleTypes.WITCH, x, y, z, 0, 0.05, 0);
            }

            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        List<Player> players = level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(3));
        for (Player player : players) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 100, 1, true, false)); // Jump Boost II for 5 sec
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1, true, false)); // Speed II for 5 sec
        }
        if (preventMobSpawning(level, pos)) {
            List<LivingEntity> hostileMobs = level.getEntitiesOfClass(LivingEntity.class,
                    new AABB(pos).inflate(5),
                    entity -> entity.getType().getCategory() == MobCategory.MONSTER);

            for (LivingEntity hostileMob : hostileMobs) {
                hostileMob.remove(Entity.RemovalReason.DISCARDED); // **Removes hostile mobs within the radius**
            }
        }

        level.scheduleTick(pos, this, 20); // Re-run this every second
    }

    public static boolean preventMobSpawning(Level level, BlockPos pos) {
        List<Player> players = level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(5));
        return !players.isEmpty();
    }
}