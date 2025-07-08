package net.masterquentus.hexcraftmod.block.custom.plants;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class DuskrootLanternBlock extends FlowerBlock {

    public DuskrootLanternBlock(Supplier<MobEffect> effectSupplier, int effectDuration, Properties properties) {
        super(effectSupplier, effectDuration, properties);
    }
}