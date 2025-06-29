package net.masterquentus.hexcraftmod.block.entity;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class SacrificialPillarBlockEntity extends BlockEntity {
    private ItemStack storedItem = ItemStack.EMPTY;
    private static final int RITUAL_DELAY_TICKS = 100; // 5 seconds delay
    private static final int RITUAL_COOLDOWN_MILLIS = 60000; // 1-minute cooldown
    private long ritualCooldownEnd = 0;

    public SacrificialPillarBlockEntity(BlockPos pos, BlockState state) {
        super(HexcraftBlockEntities.SACRIFICIAL_PILLAR_ENTITY.get(), pos, state);
    }

    public void consumeItem() {
        if (!storedItem.isEmpty()) {
            storedItem = ItemStack.EMPTY;
            setChanged();
        }
    }

    public ItemStack getStoredItem() {
        return storedItem;
    }

    public boolean setItem(ItemStack item) {
        if (storedItem.isEmpty()) {
            storedItem = item.copy();
            storedItem.setCount(1);
            setChanged();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3); // ✅ Ensure the renderer updates
            return true;
        }
        return false;
    }

    public ItemStack removeItem() {
        if (!storedItem.isEmpty()) {
            ItemStack removed = storedItem;
            storedItem = ItemStack.EMPTY;
            setChanged();
            return removed;
        }
        return ItemStack.EMPTY;
    }

    public void broadcastMessage(String message, ChatFormatting color) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.players().forEach(player ->
                    player.sendSystemMessage(Component.literal(message).withStyle(color, ChatFormatting.BOLD))
            );
        }
    }

    public void attemptRitual() {
        if (level == null || level.isClientSide) return;

        broadcastMessage("🔍 DEBUG: attemptRitual() has been called!", ChatFormatting.YELLOW);

        if (!canPerformRitual()) {
            broadcastMessage("⏳ The altar needs time to regain its power!", ChatFormatting.GRAY);
            return;
        }

        List<BlockPos> pillars = findSacrificialPillars();
        broadcastMessage("🗿 DEBUG: Found " + pillars.size() + " sacrificial pillars.", ChatFormatting.YELLOW);

        if (pillars.size() < 4) {
            broadcastMessage("⚠️ The ritual is incomplete! Place all required items!", ChatFormatting.RED);
            return;
        }

        if (!validateMultiBlockStructure()) {
            broadcastMessage("❌ Multi-block structure is incorrect!", ChatFormatting.RED);
            return;
        } else {
            broadcastMessage("✅ Multi-block structure is correct!", ChatFormatting.GREEN);
        }

        if (!validateSacrificialPillars(pillars)) {
            broadcastMessage("❌ Items in pillars are incorrect!", ChatFormatting.RED);
            return;
        } else {
            broadcastMessage("✅ Correct items detected!", ChatFormatting.GREEN);
        }

        // Ritual Begins
        broadcastMessage("🔮 The ritual begins! Dark forces stir...", ChatFormatting.DARK_PURPLE);

        if (level instanceof ServerLevel serverLevel) {
            for (BlockPos pos : pillars) {
                serverLevel.sendParticles(ParticleTypes.ENCHANT, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                        20, 0.5, 0.5, 0.5, 0.1);
                serverLevel.playSound(null, pos, SoundEvents.AMBIENT_CAVE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }

        for (BlockPos pos : pillars) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof SacrificialPillarBlockEntity pillar) {
                pillar.consumeItem();
            }
        }

        setRitualCooldown();
        broadcastMessage("⏳ Scheduling Pandora’s Box spawn in " + RITUAL_DELAY_TICKS + " ticks!", ChatFormatting.BLUE);
        level.scheduleTick(getRitualCenter(), level.getBlockState(getRitualCenter()).getBlock(), RITUAL_DELAY_TICKS);
    }


    public void completeRitual() {
        if (level instanceof ServerLevel serverLevel) {
            broadcastMessage("🔥 DEBUG: completeRitual() has been called!", ChatFormatting.RED);

            BlockPos center = getRitualCenter();

            // ⚡ Lightning Strikes!
            for (int i = 0; i < 3; i++) {
                BlockPos strikePos = center.offset(level.random.nextInt(3) - 1, 0, level.random.nextInt(3) - 1);
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
                if (lightning != null) {
                    lightning.moveTo(Vec3.atCenterOf(strikePos));
                    serverLevel.addFreshEntity(lightning);
                }
            }

            // 🕯 Ritual Complete - Pandora's Box Appears!
            level.setBlockAndUpdate(center, HexcraftBlocks.PANDORAS_BOX.get().defaultBlockState());
            broadcastMessage("🔥 Pandora’s Box has materialized! Dare you open it?", ChatFormatting.GOLD);

            // Play a dramatic explosion sound
            serverLevel.playSound(null, center, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1.5f, 0.8f);

            // Reset Ritual Cooldown
            setRitualCooldown();
        }
    }

    private List<BlockPos> findSacrificialPillars() {
        List<BlockPos> pillars = new ArrayList<>();
        for (BlockPos pos : BlockPos.betweenClosed(worldPosition.offset(-3, 0, -3), worldPosition.offset(3, 0, 3))) {
            if (level.getBlockState(pos).getBlock() == HexcraftBlocks.SACRIFICIAL_PILLAR.get()) {
                pillars.add(pos);
            }
        }
        return pillars;
    }

    private boolean validateSacrificialPillars(List<BlockPos> pillars) {
        List<Item> requiredItems = List.of(
                Items.TOTEM_OF_UNDYING,
                Items.SOUL_SAND,
                Items.NETHER_STAR,
                HexcraftBlocks.ECLIPSIUM_BLOCK.get().asItem()
        );

        List<Item> foundItems = new ArrayList<>();

        for (BlockPos pos : pillars) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof SacrificialPillarBlockEntity pillar) {
                ItemStack stack = pillar.getStoredItem();
                if (!stack.isEmpty()) {
                    foundItems.add(stack.getItem());
                }
            }
        }

        return foundItems.containsAll(requiredItems);
    }

    private boolean validateMultiBlockStructure() {
        BlockPos center = worldPosition; // The ritual center

        // Validate Eclipsium Blocks (🟪)
        if (!isEclipsiumBlock(center)) return false;
        if (!isEclipsiumBlock(center.north(2))) return false;
        if (!isEclipsiumBlock(center.south(2))) return false;
        if (!isEclipsiumBlock(center.east(2))) return false;
        if (!isEclipsiumBlock(center.west(2))) return false;

        // Validate Soul Fire (🔥) on Soul Sand (Soul Fire must be on the same Y level)
        if (!isSoulFire(center.north()) || !isSoulFire(center.south()) ||
                !isSoulFire(center.east()) || !isSoulFire(center.west())) return false;

        // Validate Amethyst Blocks (🔮) (Same Y level)
        if (!isAmethystBlock(center.north(1).east(1))) return false;
        if (!isAmethystBlock(center.north(1).west(1))) return false;
        if (!isAmethystBlock(center.south(1).east(1))) return false;
        if (!isAmethystBlock(center.south(1).west(1))) return false;

        // Validate Candles (🕯️) (Can be on same Y or +1)
        if (!isCandle(center.north(2).east(2))) return false;
        if (!isCandle(center.north(2).west(2))) return false;
        if (!isCandle(center.south(2).east(2))) return false;
        if (!isCandle(center.south(2).west(2))) return false;

        // Validate Sacrificial Pillars (🗿) (Can be on same Y or +1)
        if (!isSacrificialPillar(center.north(3)) && !isSacrificialPillar(center.north(3).above())) return false;
        if (!isSacrificialPillar(center.south(3)) && !isSacrificialPillar(center.south(3).above())) return false;
        if (!isSacrificialPillar(center.east(3)) && !isSacrificialPillar(center.east(3).above())) return false;
        if (!isSacrificialPillar(center.west(3)) && !isSacrificialPillar(center.west(3).above())) return false;

        return true; // ✅ Ritual structure is correct
    }


    public boolean canAttemptRitual() {
        return validateMultiBlockStructure() && validateSacrificialPillars(findSacrificialPillars());
    }

    // Utility methods to check block types
    private boolean isEclipsiumBlock(BlockPos pos) {
        return level.getBlockState(pos).is(HexcraftBlocks.ECLIPSIUM_BLOCK.get());
    }

    private boolean isSoulFire(BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.SOUL_FIRE);
    }

    private boolean isAmethystBlock(BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.AMETHYST_BLOCK);
    }

    private boolean isCandle(BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.CANDLE) || level.getBlockState(pos.above()).is(Blocks.CANDLE);
    }

    private boolean isSacrificialPillar(BlockPos pos) {
        return level.getBlockState(pos).is(HexcraftBlocks.SACRIFICIAL_PILLAR.get());
    }

    private void setRitualCooldown() {
        ritualCooldownEnd = System.currentTimeMillis() + RITUAL_COOLDOWN_MILLIS;
    }


    private BlockPos getRitualCenter() {
        return worldPosition.offset(0, 1, 0);
    }

    private boolean canPerformRitual() {
        return System.currentTimeMillis() > ritualCooldownEnd;
    }
}