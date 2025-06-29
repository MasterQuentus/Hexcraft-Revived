package net.masterquentus.hexcraftmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class FairyWardBlock extends Block {

    public FairyWardBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) {
            ((ServerLevel) level).scheduleTick(pos, this, 20); // Run every second
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        List<Mob> hostileMobs = level.getEntitiesOfClass(Mob.class, new AABB(pos).inflate(5),
                mob -> mob.getType().getCategory() == MobCategory.MONSTER);

        for (Mob mob : hostileMobs) {
            mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0)); // Weakness for 10 sec
        }
        level.scheduleTick(pos, this, 20); // Re-run every second
    }
}