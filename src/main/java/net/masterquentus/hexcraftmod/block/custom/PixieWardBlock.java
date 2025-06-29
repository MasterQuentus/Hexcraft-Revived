package net.masterquentus.hexcraftmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PixieWardBlock extends Block {

    public PixieWardBlock(Properties properties) {
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
        List<Player> players = level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(5));

        for (Player player : players) {
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 0, true, false)); // 10 sec invisibility
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, 200, 0, true, false)); // 10 sec luck
        }
        level.scheduleTick(pos, this, 20); // Re-run every second
    }
}