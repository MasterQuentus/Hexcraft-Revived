package net.masterquentus.hexcraftmod.spells;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class VitaReverteturSpell extends HexSpell {
    @Override
    public String getId() {
        return "vita_revertetur";
    }

    @Override
    public void cast(ServerLevel level, Player caster) {
        // Heal the caster fully as a simple resurrection stand-in
        caster.setHealth(caster.getMaxHealth());
        caster.clearFire();
        caster.removeAllEffects();  // Remove negative potion effects
        caster.displayClientMessage(Component.literal("You feel revitalized!"), true);
        level.playSound(null, caster.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}