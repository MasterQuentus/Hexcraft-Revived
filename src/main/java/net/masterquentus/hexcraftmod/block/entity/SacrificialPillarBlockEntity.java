package net.masterquentus.hexcraftmod.block.entity;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.masterquentus.hexcraftmod.recipe.RitualRecipe;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SacrificialPillarBlockEntity extends BlockEntity {
    private boolean ritualInProgress = false;
    private int ritualTick = 0;
    private static final int RITUAL_TOTAL_TICKS = 100; // how long the ritual takes (adjustable)
    private List<BlockPos> ritualPillars = new ArrayList<>();
    public final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private float rotation;

    public SacrificialPillarBlockEntity(BlockPos pos, BlockState state) {
        super(HexcraftBlockEntities.SACRIFICIAL_PILLAR_ENTITY.get(), pos, state);
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if (rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public void clearContents() {
        inventory.setStackInSlot(0, ItemStack.EMPTY);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for (int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
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

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    public void tick() {
        if (level == null || level.isClientSide()) return;

        if (ritualScheduled && System.currentTimeMillis() >= ritualCompleteTime) {
            completeRitual();
            ritualScheduled = false;
        }

        if (ritualInProgress) {
            ritualTick++;
            if (!(level instanceof ServerLevel serverLevel)) return;

            BlockPos center = getRitualCenter();

            for (BlockPos pillarPos : ritualPillars) {
                double progress = (double) ritualTick / RITUAL_TOTAL_TICKS;
                Vec3 start = Vec3.atCenterOf(pillarPos).add(0, 0.5, 0);
                Vec3 end = Vec3.atCenterOf(center).add(0, 1.0, 0);
                Vec3 particlePos = start.add(end.subtract(start).scale(progress));

                serverLevel.sendParticles(ParticleTypes.ENCHANT,
                        particlePos.x, particlePos.y, particlePos.z,
                        1, 0, 0, 0, 0);
            }

            // consume items when done
            if (ritualTick >= RITUAL_TOTAL_TICKS) {
                for (BlockPos pos : ritualPillars) {
                    BlockEntity entity = level.getBlockEntity(pos);
                    if (entity instanceof SacrificialPillarBlockEntity pillar) {
                        pillar.clearContents();
                    }
                }
                ritualInProgress = false;
                ritualScheduled = true;
                ritualCompleteTime = System.currentTimeMillis() + RITUAL_DELAY_TICKS * 50L;
            }
        }
    }

    private static final int RITUAL_DELAY_TICKS = 100; // 5 seconds delay
    private static final int RITUAL_COOLDOWN_MILLIS = 60000; // 1-minute cooldown
    private long ritualCooldownEnd = 0;


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
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
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
            return true;
        }
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

    private boolean ritualScheduled = false;
    private long ritualCompleteTime = 0;

    public void attemptRitual() {
        if (level == null || level.isClientSide) return;

        BlockPos center = getRitualCenter();
        BlockEntity centerEntity = level.getBlockEntity(center);

        if (!(centerEntity instanceof SacrificialPillarBlockEntity centerPillar)) return;

        // Structure validation
        List<String> structureErrors = getMultiBlockStructureErrors();
        if (!structureErrors.isEmpty()) {
            return; // stop the ritual if structure is incomplete
        }

        ItemStack centerItem = centerPillar.getStoredItem();
        if (centerItem.isEmpty()) {
            return; // stop if no item in center
        }

        List<BlockPos> pillars = findSacrificialPillarsAround(center);
        List<Item> pillarItems = new ArrayList<>();
        for (BlockPos pos : pillars) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof SacrificialPillarBlockEntity pillar) {
                ItemStack stack = pillar.getStoredItem();
                if (!stack.isEmpty()) {
                    pillarItems.add(stack.getItem());
                }
            }
        }

        // Find a matching ritual recipe
        RitualRecipe matched = null;
        for (RitualRecipe recipe : RITUAL_RECIPES) {
            if (recipe.matches(centerItem.getItem(), pillarItems)) {
                matched = recipe;
                break;
            }
        }

        if (matched == null) {
            return; // stop if no valid recipe
        }

        // Start the ritual
        ritualPillars = pillars;
        ritualInProgress = true;
        ritualTick = 0;
        consumeRitualItems(pillars);

        activeRecipe = matched;

        if (level instanceof ServerLevel serverLevel) {
            for (BlockPos pos : pillars) {
                serverLevel.sendParticles(ParticleTypes.ENCHANT, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                        40, 0.6, 0.6, 0.6, 0.2);
                serverLevel.playSound(null, pos, SoundEvents.AMBIENT_CAVE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }

        // Schedule ritual completion
        ritualScheduled = true;
        ritualCompleteTime = System.currentTimeMillis() + RITUAL_DELAY_TICKS * 50L; // 50ms per tick
    }

    private RitualRecipe activeRecipe;

    public void completeRitual() {
        if (!(level instanceof ServerLevel serverLevel) || activeRecipe == null) return;

        BlockPos center = getRitualCenter();
        BlockEntity centerEntity = level.getBlockEntity(center);

        if (!(centerEntity instanceof SacrificialPillarBlockEntity centerPillar)) return;

        // Consume the center item
        centerPillar.clearContents();

        if (activeRecipe.isEntityRecipe()) {
            // Summon entity
            EntityType<?> entityType = activeRecipe.getResultEntity();
            Entity entity = entityType.create(serverLevel);
            if (entity != null) {
                entity.moveTo(Vec3.atCenterOf(center).add(0, 1, 0));
                serverLevel.addFreshEntity(entity);
            }
        } else {
            // Place item into center pillar
            centerPillar.replaceItem(activeRecipe.getResultItem());
        }

        // Ritual particle + lightning effects
        for (int i = 0; i < 50; i++) {
            double offsetX = level.random.nextDouble();
            double offsetY = level.random.nextDouble() * 2;
            double offsetZ = level.random.nextDouble();
            serverLevel.sendParticles(
                    ParticleTypes.ENCHANT,
                    center.getX() + offsetX,
                    center.getY() + offsetY,
                    center.getZ() + offsetZ,
                    1, 0, 0, 0, 0
            );
        }

        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
        if (bolt != null) {
            bolt.moveTo(Vec3.atCenterOf(center));
            bolt.setVisualOnly(true);
            serverLevel.addFreshEntity(bolt);
        }

        serverLevel.playSound(null, center, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 1.5f, 1.0f);

        // Reset recipe + cooldown
        activeRecipe = null;
        setRitualCooldown();
    }

    private List<BlockPos> findSacrificialPillarsAround(BlockPos center) {
        return List.of(
                center.north(3),
                center.south(3),
                center.east(3),
                center.west(3)
        );
    }

    public List<String> getMultiBlockStructureErrors() {
        List<String> errors = new ArrayList<>();
        BlockPos center = getRitualCenter();   // center pillar
        BlockPos base = center.below();        // 1 block below pillar for blocks

        // Eclipsium Blocks (🟪) – 1 below pillar
        if (!isEclipsiumBlock(base))
            errors.add("Missing Eclipsium Block at " + formatPos(base));
        if (!isEclipsiumBlock(base.north(2)))
            errors.add("Missing Eclipsium Block at " + formatPos(base.north(2)));
        if (!isEclipsiumBlock(base.south(2)))
            errors.add("Missing Eclipsium Block at " + formatPos(base.south(2)));
        if (!isEclipsiumBlock(base.east(2)))
            errors.add("Missing Eclipsium Block at " + formatPos(base.east(2)));
        if (!isEclipsiumBlock(base.west(2)))
            errors.add("Missing Eclipsium Block at " + formatPos(base.west(2)));

        // Soul Fire (🔥) – on same Y as pillar
        if (!isSoulFire(center.north()))
            errors.add("Missing Soul Fire at " + formatPos(center.north()));
        if (!isSoulFire(center.south()))
            errors.add("Missing Soul Fire at " + formatPos(center.south()));
        if (!isSoulFire(center.east()))
            errors.add("Missing Soul Fire at " + formatPos(center.east()));
        if (!isSoulFire(center.west()))
            errors.add("Missing Soul Fire at " + formatPos(center.west()));

        // Amethyst Blocks (🔮) – 1 below pillar
        if (!isAmethystBlock(base.north(1).east(1)))
            errors.add("Missing Amethyst Block at " + formatPos(base.north(1).east(1)));
        if (!isAmethystBlock(base.north(1).west(1)))
            errors.add("Missing Amethyst Block at " + formatPos(base.north(1).west(1)));
        if (!isAmethystBlock(base.south(1).east(1)))
            errors.add("Missing Amethyst Block at " + formatPos(base.south(1).east(1)));
        if (!isAmethystBlock(base.south(1).west(1)))
            errors.add("Missing Amethyst Block at " + formatPos(base.south(1).west(1)));

        // Candles (🕯️) – same as before (can be same Y or +1)
        if (!isCandle(center.north(2).east(2)))
            errors.add("Missing Candle at " + formatPos(center.north(2).east(2)));
        if (!isCandle(center.north(2).west(2)))
            errors.add("Missing Candle at " + formatPos(center.north(2).west(2)));
        if (!isCandle(center.south(2).east(2)))
            errors.add("Missing Candle at " + formatPos(center.south(2).east(2)));
        if (!isCandle(center.south(2).west(2)))
            errors.add("Missing Candle at " + formatPos(center.south(2).west(2)));

        // Sacrificial Pillars (🗿) – check base and 1 above
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
                } else {
                }
            } else {
                errors.add("Missing pillar at " + formatPos(pos));
            }
        }

        List<Item> missingItems = new ArrayList<>(requiredItems);
        missingItems.removeAll(foundItems);

        for (Item missing : missingItems) {
            errors.add("Missing required item: " + missing.getDescription().getString());
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
                return InteractionResult.SUCCESS;
            }
        } else {
            if (setItem(heldItem)) {
                heldItem.shrink(1);
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

    private static final List<RitualRecipe> RITUAL_RECIPES = List.of(
            new RitualRecipe(
                    Items.NETHERITE_BLOCK,
                    List.of(HexcraftItems.INFERNAL_EMBER.get(),
                            HexcraftItems.BLOOD_BOTTLE.get(),
                            HexcraftItems.NECROMANTIC_STONE.get(),
                            HexcraftItems.WITHER_BONE.get()),
                    new ItemStack(HexcraftItems.PANDORAS_BOX_ITEM.get())
            ),
            new RitualRecipe(
                    Items.DIAMOND_BLOCK,
                    List.of(Items.DIAMOND, Items.EMERALD, Items.GOLD_INGOT, Items.LAPIS_LAZULI),
                    new ItemStack(Items.BEACON)
            ),
            new RitualRecipe(
                    Items.BONE, // 🟢 Center item
                    List.of(Items.GUNPOWDER, Items.ROTTEN_FLESH, HexcraftItems.BLOOD_BOTTLE.get(), Items.SPIDER_EYE),
                    EntityType.CREEPER // 🟢 Will summon a Creeper
            )
    );

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

    public BlockPos getRitualCenter() {
        return findRitualCenter();
    }

    public void replaceItem(ItemStack newItem) {
        inventory.setStackInSlot(0, newItem.copy());
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    private boolean canPerformRitual() {
        return System.currentTimeMillis() > ritualCooldownEnd;
    }

    private void consumeRitualItems(List<BlockPos> pillars) {
        for (BlockPos pos : pillars) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof SacrificialPillarBlockEntity pillar) {
                pillar.clearContents();
            }
        }
    }
}