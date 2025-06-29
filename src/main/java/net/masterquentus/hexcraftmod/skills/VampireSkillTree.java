package net.masterquentus.hexcraftmod.skills;

import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.*;

public class VampireSkillTree {

    private static final Map<UUID, Map<String, Boolean>> playerSkills = new HashMap<>();

    public static void unlockSkill(UUID playerUUID, String skillName) {
        playerSkills.putIfAbsent(playerUUID, new HashMap<>()); // Ensure player entry exists
        Map<String, Boolean> skills = playerSkills.get(playerUUID);

        if (skills.getOrDefault(skillName, false)) {
            return; // Skill already unlocked, do nothing
        }

        skills.put(skillName, true); // Unlock skill

        // Find the player by UUID
        Player player = getPlayerFromUUID(playerUUID);
        if (player != null) {
            player.sendSystemMessage(Component.literal("You have unlocked: " + skillName));

            switch (skillName) {
                case "Enhanced Strength" -> player.addEffect(new MobEffectInstance(HexcraftEffects.ENHANCED_STRENGTH.get(), Integer.MAX_VALUE, 0, false, false));
                case "Enhanced Speed" -> player.addEffect(new MobEffectInstance(HexcraftEffects.ENHANCED_SPEED.get(), Integer.MAX_VALUE, 0, false, false));
                case "Blood Vision" -> player.addEffect(new MobEffectInstance(HexcraftEffects.BLOOD_VISION.get(), 6000, 0, false, false)); // Lasts 5 minutes
                case "Bloodlust Mode" -> player.addEffect(new MobEffectInstance(HexcraftEffects.BLOODLUST.get(), 1200, 0, false, false)); // Lasts 1 minute
            }
        }
    }

    public static boolean isValidSkill(String skill) {
        return Set.of("Enhanced Strength", "Enhanced Speed", "Blood Vision", "Bloodlust Mode").contains(skill);
    }

    public static boolean isSkillUnlocked(UUID playerUUID, String skillName) {
        return playerSkills.getOrDefault(playerUUID, new HashMap<>()).getOrDefault(skillName, false);
    }

    private static Player getPlayerFromUUID(UUID playerUUID) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer(); // Get the current Minecraft Server
        if (server != null) {
            return server.getPlayerList().getPlayer(playerUUID); // Get the player from the UUID
        }
        return null;
    }
}