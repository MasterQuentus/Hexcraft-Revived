package net.masterquentus.hexcraftmod.comands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.masterquentus.hexcraftmod.skills.VampireSkillTree;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class VampireCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("unlockvampireskill")
                .requires(source -> source.hasPermission(2)) // Requires OP permission (level 2)
                .then(Commands.argument("skill", StringArgumentType.string())
                        .executes(context -> {
                            String skill = StringArgumentType.getString(context, "skill");
                            CommandSourceStack source = context.getSource();

                            // Ensure the command source is a player
                            if (source.getEntity() instanceof Player player) {
                                // Debug message to confirm command execution
                                player.sendSystemMessage(Component.literal("Debug: Command executed. Skill: " + skill));

                                // Ensure skill exists before unlocking
                                if (VampireSkillTree.isValidSkill(skill)) {
                                    // Unlock the skill using the player's UUID
                                    VampireSkillTree.unlockSkill(player.getUUID(), skill);
                                    player.sendSystemMessage(Component.literal("Skill unlocked: " + skill));
                                    return 1; // Success
                                } else {
                                    player.sendSystemMessage(Component.literal("Invalid skill: " + skill));
                                    return 0; // Failure
                                }
                            }

                            // If the command was not run by a player
                            source.sendFailure(Component.literal("Only players can use this command!"));
                            return 0;
                        })));
    }
}