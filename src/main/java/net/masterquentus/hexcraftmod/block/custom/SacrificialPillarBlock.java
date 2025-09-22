package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.block.entity.SacrificialPillarBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Properties;

public class SacrificialPillarBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(2, -2, 2, 14, 15, 14),
            Block.box(1, 15, 1, 15, 16, 15));

    public SacrificialPillarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof SacrificialPillarBlockEntity pillar) {
            // pillar.broadcastMessage("⏳ DEBUG: Tick detected on Sacrificial Pillar!", ChatFormatting.BLUE);
            pillar.attemptRitual();
        }

        // Schedule next tick so ritual keeps updating
        level.scheduleTick(pos, this, 1);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof SacrificialPillarBlockEntity pillar)) {
            return InteractionResult.PASS;
        }

        ItemStack heldItem = player.getItemInHand(hand);
        ItemStack stored = pillar.getStoredItem();

        if (heldItem.isEmpty()) {
            // Player trying to retrieve item
            ItemStack retrieved = pillar.removeItem(); // uses storedItem
            if (!retrieved.isEmpty()) {
                player.addItem(retrieved);
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
                //player.displayClientMessage(Component.literal("🗿 You retrieved " + retrieved.getHoverName()), true);
                return InteractionResult.CONSUME;
            }

            // Fallback to inventory if storedItem somehow failed
            if (!pillar.inventory.getStackInSlot(0).isEmpty()) {
                ItemStack extracted = pillar.inventory.extractItem(0, 1, false);
                player.setItemInHand(hand, extracted);
                pillar.clearContents();
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
                return InteractionResult.CONSUME;
            }

        } else {
            // Player trying to place item
            if (stored.isEmpty()) {
                if (pillar.setItem(heldItem)) {
                    Component itemName = heldItem.getHoverName();

                    if (!player.isCreative()) {
                        heldItem.shrink(1);
                    } else {
                        heldItem.shrink(1);
                        if (heldItem.isEmpty()) {
                            player.setItemInHand(hand, ItemStack.EMPTY);
                        } else {
                            player.setItemInHand(hand, heldItem); // Resync hand
                        }
                    }

// Now show the correct name
                    //player.displayClientMessage(Component.literal("🗿 You placed an offering..."), true);
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
                    return InteractionResult.CONSUME;
                }
            }
        }

        // Always try ritual after interaction
        //player.displayClientMessage(Component.literal("🔮 DEBUG: Attempting ritual..."), true);
        pillar.attemptRitual();

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos,
                            BlockState pNewState, boolean pMovedByPiston) {
        if(pState.getBlock() != pNewState.getBlock()) {
            if(pLevel.getBlockEntity(pPos) instanceof SacrificialPillarBlockEntity pedestalBlockEntity) {
                pedestalBlockEntity.drops();
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SacrificialPillarBlockEntity(pos, state);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            // Schedule first tick in 1 tick (so your tick method will run)
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (lvl, pos, st, be) -> {
            if (be instanceof SacrificialPillarBlockEntity pillar) {
                pillar.tick(); // calls the tick method in your BlockEntity
            }
        };
    }

}