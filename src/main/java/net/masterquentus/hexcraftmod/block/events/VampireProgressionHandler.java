package net.masterquentus.hexcraftmod.block.events;

import net.masterquentus.hexcraftmod.capabilities.VampireBloodProvider;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.masterquentus.hexcraftmod.skills.VampireSkillTree;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class VampireProgressionHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player == null) return;

        // **Ensure Vampire Transformation Effect is Registered**
        if (!HexcraftEffects.VAMPIRE_TRANSFORMATION.isPresent()) {
            return; // Prevents crash if effect is missing
        }

        // **Check if player is a vampire**
        if (!player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) {
            return;
        }

        // **🩸 Handle Vampire Starvation**
        player.getCapability(VampireBloodProvider.VAMPIRE_BLOOD_CAPABILITY).ifPresent(blood -> {
            if (blood.getBloodLevel() < 3) {
                player.sendSystemMessage(Component.literal("You are starving and feel weak..."));
                player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.WEAKNESS, 100, 1));
            }
        });

        // **⏳ Track vampire progression**
        int vampireTime = player.getPersistentData().getInt("vampireTime");
        vampireTime++;
        player.getPersistentData().putInt("vampireTime", vampireTime);

        // **🧛 Unlock Vampire Abilities Over Time**
        if (vampireTime >= 6000 && !VampireSkillTree.isSkillUnlocked(player.getUUID(), "Enhanced Strength")) {
            VampireSkillTree.unlockSkill(player.getUUID(), "Enhanced Strength");
        }
        if (vampireTime >= 12000 && !VampireSkillTree.isSkillUnlocked(player.getUUID(), "Enhanced Speed")) {
            VampireSkillTree.unlockSkill(player.getUUID(), "Enhanced Speed");
        }
        if (vampireTime >= 18000 && !VampireSkillTree.isSkillUnlocked(player.getUUID(), "Blood Vision")) {
            VampireSkillTree.unlockSkill(player.getUUID(), "Blood Vision");
        }
        if (vampireTime >= 24000 && !VampireSkillTree.isSkillUnlocked(player.getUUID(), "Bloodlust Mode")) {
            VampireSkillTree.unlockSkill(player.getUUID(), "Bloodlust Mode");
        }
    }
}