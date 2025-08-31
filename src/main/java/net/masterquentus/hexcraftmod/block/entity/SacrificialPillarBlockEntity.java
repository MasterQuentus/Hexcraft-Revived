package net.masterquentus.hexcraftmod.block.entity;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SacrificialPillarBlockEntity extends BlockEntity {

    public final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private float rotation;

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    private static final int RITUAL_DELAY_TICKS = 100; // 5 seconds delay
    private static final int RITUAL_COOLDOWN_MILLIS = 60000; // 1-minute cooldown
    private long ritualCooldownEnd = 0;

    public SacrificialPillarBlockEntity(BlockPos pos, BlockState state) {
        super(HexcraftBlockEntities.SACRIFICIAL_PILLAR_ENTITY.get(), pos, state);
    }

    public void consumeItem() {
        inventory.setStackInSlot(0, ItemStack.EMPTY);
        setChanged();
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof SacrificialPillarBlockEntity pillar) {
            return pillar.onPlayerUse(level, player, hand);
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", inventory.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Inventory")) {
            inventory.deserializeNBT(tag.getCompound("Inventory"));
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    public void clearContents() {
        inventory.setStackInSlot(0, ItemStack.EMPTY);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public ItemStack getStoredItem() {
        return inventory.getStackInSlot(0);
    }

    public boolean setItem(ItemStack item) {
        if (inventory.getStackInSlot(0).isEmpty()) {
            ItemStack copy = item.copy();
            copy.setCount(1);
            inventory.setStackInSlot(0, copy);
            setChanged();
            if (level != null) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
            broadcastMessage("DEBUG: Item set on pillar: " + copy.getItem().getDescription().getString(), ChatFormatting.GREEN);
            return true;
        }
        broadcastMessage("DEBUG: Pillar already has an item!", ChatFormatting.RED);
        return false;
    }

    public ItemStack removeItem() {
        ItemStack removed = inventory.getStackInSlot(0);
        if (!removed.isEmpty()) {
            inventory.setStackInSlot(0, ItemStack.EMPTY);
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

        BlockPos center = findRitualCenter();

        broadcastMessage("🔍 DEBUG: attemptRitual() called at center " + formatPos(center), ChatFormatting.YELLOW);

        if (!canPerformRitual()) {
            broadcastMessage("⏳ The altar needs time to regain its power!", ChatFormatting.GRAY);
            return;
        }

        List<BlockPos> pillars = findSacrificialPillarsAround(center);
        broadcastMessage("🗿 DEBUG: Found " + pillars.size() + " sacrificial pillars.", ChatFormatting.YELLOW);

        if (pillars.size() < 4) {
            broadcastMessage("⚠️ The ritual is incomplete! Place all required items!", ChatFormatting.RED);
            return;
        }

        // Detailed multi-block structure check & error reporting
        List<String> structureErrors = getMultiBlockStructureErrors();
        if (!structureErrors.isEmpty()) {
            broadcastMessage("❌ Multi-block structure errors:", ChatFormatting.RED);
            for (String error : structureErrors) {
                broadcastMessage("- " + error, ChatFormatting.RED);
            }
            return;
        } else {
            broadcastMessage("✅ Multi-block structure is correct!", ChatFormatting.GREEN);
        }

        // Detailed sacrificial pillar items check & error reporting
        List<String> itemErrors = getSacrificialPillarItemErrors(pillars);
        if (!itemErrors.isEmpty()) {
            broadcastMessage("❌ Item errors on sacrificial pillars:", ChatFormatting.RED);
            for (String error : itemErrors) {
                broadcastMessage("- " + error, ChatFormatting.RED);
            }
            return;
        } else {
            broadcastMessage("✅ Correct items detected!", ChatFormatting.GREEN);
        }

        // Ritual Begins...
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
                ItemStack stack = pillar.getStoredItem();
                broadcastMessage("DEBUG: Pillar at " + formatPos(pos) + " contains item: "
                        + (stack.isEmpty() ? "EMPTY" : stack.getItem().getDescription().getString()), ChatFormatting.AQUA);
            } else {
                broadcastMessage("DEBUG: No SacrificialPillarBlockEntity at " + formatPos(pos), ChatFormatting.RED);
            }
        }

        setRitualCooldown();
        broadcastMessage("⏳ Scheduling Pandora’s Box spawn in " + RITUAL_DELAY_TICKS + " ticks!", ChatFormatting.BLUE);
        level.scheduleTick(getRitualCenter(), level.getBlockState(getRitualCenter()).getBlock(), RITUAL_DELAY_TICKS);
    }

    private List<BlockPos> findSacrificialPillarsAround(BlockPos center) {
        List<BlockPos> pillars = new ArrayList<>();
        int radius = 5;
        for (BlockPos pos : BlockPos.betweenClosed(
                center.offset(-radius, 0, -radius),
                center.offset(radius, 1, radius))) {
            if (level.getBlockState(pos).is(HexcraftBlocks.SACRIFICIAL_PILLAR.get())) {
                // Skip the center pillar itself
                if (!pos.equals(center)) {
                    pillars.add(pos);
                }
            }
        }
        return pillars;
    }

    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            boolean powered = level.hasNeighborSignal(pos);
            if (powered) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof SacrificialPillarBlockEntity pillar) {
                    pillar.attemptRitual();
                }
            }
        }
    }

    public void completeRitual() {
        if (level instanceof ServerLevel serverLevel) {
            broadcastMessage("🔥 DEBUG: completeRitual() has been called!", ChatFormatting.RED);

            BlockPos center = getRitualCenter().below(); // ➤ NEW
            BlockPos centerTop = getRitualCenter();

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

    private List<String> getMultiBlockStructureErrors() {
        List<String> errors = new ArrayList<>();
        BlockPos center = getRitualCenter().below(); // For base structure
        BlockPos centerTop = getRitualCenter();       // For soul fire and others

// Eclipsium Blocks (now 1 block lower)
        if (!isSacrificialPillarOrAbove(center) && !isEclipsiumBlock(center))
            errors.add("Missing Eclipsium Block at " + formatPos(center));
        if (!isSacrificialPillarOrAbove(center.north(2)) && !isEclipsiumBlock(center.north(2)))
            errors.add("Missing Eclipsium Block at " + formatPos(center.north(2)));
        if (!isSacrificialPillarOrAbove(center.south(2)) && !isEclipsiumBlock(center.south(2)))
            errors.add("Missing Eclipsium Block at " + formatPos(center.south(2)));
        if (!isSacrificialPillarOrAbove(center.east(2)) && !isEclipsiumBlock(center.east(2)))
            errors.add("Missing Eclipsium Block at " + formatPos(center.east(2)));
        if (!isSacrificialPillarOrAbove(center.west(2)) && !isEclipsiumBlock(center.west(2)))
            errors.add("Missing Eclipsium Block at " + formatPos(center.west(2)));

        // Soul Fire
        if (!isSoulFire(centerTop.north()))
            errors.add("Missing Soul Fire at " + formatPos(centerTop.north()));
        if (!isSoulFire(centerTop.south()))
            errors.add("Missing Soul Fire at " + formatPos(centerTop.south()));
        if (!isSoulFire(centerTop.east()))
            errors.add("Missing Soul Fire at " + formatPos(centerTop.east()));
        if (!isSoulFire(centerTop.west()))
            errors.add("Missing Soul Fire at " + formatPos(centerTop.west()));

        // Amethyst Blocks
        if (!isAmethystBlock(center.north(1).east(1)))
            errors.add("Missing Amethyst Block at " + formatPos(center.north(1).east(1)));
        if (!isAmethystBlock(center.north(1).west(1)))
            errors.add("Missing Amethyst Block at " + formatPos(center.north(1).west(1)));
        if (!isAmethystBlock(center.south(1).east(1)))
            errors.add("Missing Amethyst Block at " + formatPos(center.south(1).east(1)));
        if (!isAmethystBlock(center.south(1).west(1)))
            errors.add("Missing Amethyst Block at " + formatPos(center.south(1).west(1)));

        // Candles
        if (!isCandle(center.north(2).east(2)))
            errors.add("Missing Candle at " + formatPos(center.north(2).east(2)));
        if (!isCandle(center.north(2).west(2)))
            errors.add("Missing Candle at " + formatPos(center.north(2).west(2)));
        if (!isCandle(center.south(2).east(2)))
            errors.add("Missing Candle at " + formatPos(center.south(2).east(2)));
        if (!isCandle(center.south(2).west(2)))
            errors.add("Missing Candle at " + formatPos(center.south(2).west(2)));

        // Sacrificial Pillars (check both base and 1 block up)
        BlockPos[] pillarPositions = {
                center.north(3),
                center.south(3),
                center.east(3),
                center.west(3)
        };

        for (BlockPos pos : pillarPositions) {
            if (!isSacrificialPillar(pos) && !isSacrificialPillar(pos.above())) {
                errors.add("Missing Sacrificial Pillar at " + formatPos(pos) + " or " + formatPos(pos.above()));
            }
        }

        return errors;
    }

    private String formatPos(BlockPos pos) {
        return "X=" + pos.getX() + " Y=" + pos.getY() + " Z=" + pos.getZ();
    }

    private List<String> getSacrificialPillarItemErrors(List<BlockPos> pillars) {
        List<String> errors = new ArrayList<>();

        Map<Item, Integer> requiredItemCounts = new HashMap<>();
        requiredItemCounts.put(HexcraftItems.INFERNAL_EMBER.get(), 1);
        requiredItemCounts.put(HexcraftItems.BLOOD_BOTTLE.get(), 1);
        requiredItemCounts.put(HexcraftItems.NECROMANTIC_STONE.get(), 1);
        requiredItemCounts.put(HexcraftItems.WITHER_BONE.get(), 1);

        Map<Item, Integer> foundItemCounts = new HashMap<>();
        BlockPos center = getRitualCenter();

        for (BlockPos pos : pillars) {
            if (pos.equals(center)) continue; // Skip center pillar from item check

            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof SacrificialPillarBlockEntity pillar) {
                ItemStack stack = pillar.getStoredItem();
                broadcastMessage("DEBUG: Pillar at " + formatPos(pos) + " has storedItem: "
                        + (stack.isEmpty() ? "EMPTY" : stack.getItem().getDescription().getString()), ChatFormatting.LIGHT_PURPLE);

                if (!stack.isEmpty()) {
                    foundItemCounts.put(stack.getItem(), foundItemCounts.getOrDefault(stack.getItem(), 0) + 1);
                }
            }
        }

        // Now validate found counts against required counts as before
        for (Map.Entry<Item, Integer> entry : requiredItemCounts.entrySet()) {
            int found = foundItemCounts.getOrDefault(entry.getKey(), 0);
            if (found < entry.getValue()) {
                errors.add("Missing required item: " + entry.getKey().getDescription().getString());
            } else if (found > entry.getValue()) {
                errors.add("Too many of item: " + entry.getKey().getDescription().getString());
            }
        }

        for (Item foundItem : foundItemCounts.keySet()) {
            if (!requiredItemCounts.containsKey(foundItem)) {
                errors.add("Unexpected item on pillar: " + foundItem.getDescription().getString());
            }
        }

        return errors;
    }

    private List<BlockPos> findSacrificialPillars() {
        List<BlockPos> pillars = new ArrayList<>();
        for (BlockPos pos : BlockPos.betweenClosed(
                worldPosition.offset(-5, 0, -5),
                worldPosition.offset(5, 1, 5))) {  // Y from current to +1
            if (level.getBlockState(pos).getBlock() == HexcraftBlocks.SACRIFICIAL_PILLAR.get()) {
                pillars.add(pos);
            }
        }
        return pillars;
    }

    private boolean validateSacrificialPillars(List<BlockPos> pillars) {
        List<Item> requiredItems = List.of(
                HexcraftItems.INFERNAL_EMBER.get(),
                HexcraftItems.BLOOD_BOTTLE.get(),
                HexcraftItems.NECROMANTIC_STONE.get(),
                HexcraftItems.WITHER_BONE.get()
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

        // Check if all required items are present exactly once
        List<Item> requiredCopy = new ArrayList<>(requiredItems);
        for (Item found : foundItems) {
            requiredCopy.remove(found);
        }

        return requiredCopy.isEmpty();
    }

    private boolean validateMultiBlockStructure() {
        BlockPos center = getRitualCenter().below();  // 👈 updated center for structure
        BlockPos centerTop = getRitualCenter();       // 👈 same height as soul fire / candles

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

    public void reportRitualStatusToPlayer(ServerPlayer player) {
        List<BlockPos> pillars = findSacrificialPillars();

        if (pillars.size() < 4) {
            player.sendSystemMessage(Component.literal("⚠️ The ritual is incomplete! Place all required items!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
            player.sendSystemMessage(Component.literal("🗿 Found " + pillars.size() + " sacrificial pillars.").withStyle(ChatFormatting.YELLOW));
            return;
        } else {
            player.sendSystemMessage(Component.literal("🗿 Found " + pillars.size() + " sacrificial pillars.").withStyle(ChatFormatting.YELLOW));
        }

        List<String> structureErrors = getMultiBlockStructureErrors();
        if (!structureErrors.isEmpty()) {
            player.sendSystemMessage(Component.literal("❌ Multi-block structure errors:").withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
            for (String error : structureErrors) {
                player.sendSystemMessage(Component.literal("- " + error).withStyle(ChatFormatting.RED));
            }
            return;
        } else {
            player.sendSystemMessage(Component.literal("✅ Multi-block structure is correct!").withStyle(ChatFormatting.GREEN));
        }

        List<String> itemErrors = getSacrificialPillarItemErrors(pillars);
        if (!itemErrors.isEmpty()) {
            player.sendSystemMessage(Component.literal("❌ Item errors on sacrificial pillars:").withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
            for (String error : itemErrors) {
                player.sendSystemMessage(Component.literal("- " + error).withStyle(ChatFormatting.RED));
            }
            return;
        } else {
            player.sendSystemMessage(Component.literal("✅ All required items are present on sacrificial pillars!").withStyle(ChatFormatting.GREEN));
        }

        player.sendSystemMessage(Component.literal("✅ Ritual structure is complete and correct!").withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
    }

    public InteractionResult onPlayerUse(Level level, Player player, InteractionHand hand) {
        if (level == null || level.isClientSide()) return InteractionResult.SUCCESS;
        if (!(player instanceof ServerPlayer serverPlayer)) return InteractionResult.PASS;

        ItemStack heldItem = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            reportRitualStatusToPlayer(serverPlayer);
            attemptRitual();
            return InteractionResult.CONSUME;
        }

        if (heldItem.isEmpty()) {
            ItemStack removed = removeItem();
            if (!removed.isEmpty()) {
                player.addItem(removed);
                broadcastMessage("DEBUG: Removed item from pillar: " + removed.getItem().getDescription().getString(), ChatFormatting.YELLOW);
                return InteractionResult.SUCCESS;
            }
        } else {
            if (setItem(heldItem)) {
                heldItem.shrink(1);
                broadcastMessage("DEBUG: Player placed item " + heldItem.getItem().getDescription().getString() + " on pillar", ChatFormatting.GREEN);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    private BlockPos findRitualCenter() {
        // Try to find the center pillar within a 7x7 area around this pillar
        // Assuming center pillar is always at the center of the ritual
        for (BlockPos pos : BlockPos.betweenClosed(
                worldPosition.offset(-3, 0, -3),
                worldPosition.offset(3, 0, 3))) {
            if (level.getBlockState(pos).is(HexcraftBlocks.SACRIFICIAL_PILLAR.get())) {
                // Check if this pillar has the special property of being the center
                // or if it’s the center by structure position (e.g., the one without other pillars around)
                // For simplicity, assume the ritual center is the pillar with no other pillars
                // exactly 3 blocks north/south/east/west.
                boolean isCenter = true;
                int[] dx = {3, -3, 0, 0};
                int[] dz = {0, 0, 3, -3};
                for (int i = 0; i < dx.length; i++) {
                    BlockPos checkPos = pos.offset(dx[i], 0, dz[i]);
                    if (!level.getBlockState(checkPos).is(HexcraftBlocks.SACRIFICIAL_PILLAR.get())) {
                        isCenter = false;
                        break;
                    }
                }
                if (isCenter) {
                    return pos;
                }
            }
        }
        // Fallback to this pillar if center not found (should not happen)
        return worldPosition;
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
        return level.getBlockState(pos).is(BlockTags.CANDLES) ||
                level.getBlockState(pos.above()).is(BlockTags.CANDLES);
    }

    private boolean isSacrificialPillar(BlockPos pos) {
        return level.getBlockState(pos).is(HexcraftBlocks.SACRIFICIAL_PILLAR.get());
    }

    private void setRitualCooldown() {
        ritualCooldownEnd = System.currentTimeMillis() + RITUAL_COOLDOWN_MILLIS;
    }

    private boolean isSacrificialPillarOrAbove(BlockPos pos) {
        return isSacrificialPillar(pos) || isSacrificialPillar(pos.above());
    }

    private BlockPos getRitualCenter() {
        return findRitualCenter();
    }

    private boolean canPerformRitual() {
        return System.currentTimeMillis() > ritualCooldownEnd;
    }
}