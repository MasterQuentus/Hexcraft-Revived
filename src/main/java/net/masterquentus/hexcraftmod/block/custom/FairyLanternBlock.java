package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.entity.FairyEntity;
import net.masterquentus.hexcraftmod.entity.HexcraftEntities;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class FairyLanternBlock extends LanternBlock {
    public FairyLanternBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            ItemStack heldItem = player.getItemInHand(hand);

            // Summon Fairy Companion (5 min duration)
            if (!player.getCooldowns().isOnCooldown(Items.LANTERN)) {
                summonFairyCompanion(level, player);
                player.getCooldowns().addCooldown(HexcraftItems.FAIRY_LANTERN_ITEM.get(), 12000);

                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
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

    private void summonFairyCompanion(Level level, Player player) {
        // Custom Entity Code: Summons a Fairy Entity that follows the player
        FairyEntity fairy = new FairyEntity(HexcraftEntities.FAIRY.get(), level);
        fairy.setPos(player.getX(), player.getY() + 1, player.getZ());
        fairy.setOwner(player);
        level.addFreshEntity(fairy);

        // Send message to player
        player.sendSystemMessage(Component.literal("A fairy has come to aid you!").withStyle(ChatFormatting.LIGHT_PURPLE));
    }
}