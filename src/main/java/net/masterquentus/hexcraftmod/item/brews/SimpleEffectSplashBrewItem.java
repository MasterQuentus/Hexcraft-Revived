package net.masterquentus.hexcraftmod.item.brews;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraftforge.registries.ForgeRegistries;

public class SimpleEffectSplashBrewItem extends SplashPotionItem {

    private final MobEffect effect;
    private final int duration;
    private final int amplifier;

    public SimpleEffectSplashBrewItem(MobEffect effect, int duration, int amplifier, Properties properties) {
        super(properties);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(this);
        stack.getOrCreateTag().put("CustomPotionEffects", createEffectTag());
        return stack;
    }

    private ListTag createEffectTag() {
        ListTag effectsList = new ListTag();

        CompoundTag effectTag = new CompoundTag();
        // Using string ID for effect
        effectTag.putString("Id", ForgeRegistries.MOB_EFFECTS.getKey(effect).toString());
        effectTag.putInt("Duration", duration);
        effectTag.putInt("Amplifier", amplifier);

        effectsList.add(effectTag);
        return effectsList;
    }
}