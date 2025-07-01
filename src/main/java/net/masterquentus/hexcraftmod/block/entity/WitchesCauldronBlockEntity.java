package net.masterquentus.hexcraftmod.block.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import net.masterquentus.hexcraftmod.block.custom.WitchesCauldron;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.masterquentus.hexcraftmod.recipe.WitchesCauldronRecipe;
import net.masterquentus.hexcraftmod.util.ITickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class WitchesCauldronBlockEntity extends BlockEntity implements ITickableBlockEntity {
	protected static final int MAX_INPUT_SLOTS = 5; // Supports up to 5 ingredients
	protected static final Set<Block> HEAT_SOURCES = Set.of(Blocks.FIRE, Blocks.CAMPFIRE, Blocks.SOUL_FIRE, Blocks.SOUL_CAMPFIRE, Blocks.LAVA);
	protected NonNullList<ItemStack> items = NonNullList.withSize(MAX_INPUT_SLOTS, ItemStack.EMPTY);
	private final List<ItemStack> brewingItems = new ArrayList<>(); // Stores tossed items

	public WitchesCauldronBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(HexcraftBlockEntities.WITCHES_CAULDRON_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	@Override
	public void tick() {
		if (level == null || level.isClientSide) return;

		boolean hasHeatSource = this.hasHeatSourceBelow();
		if (!hasHeatSource) return;

		this.pickupItems();

		if (!this.brewingItems.isEmpty()) {
			Optional<WitchesCauldronRecipe> recipe = getRecipe();
			if (recipe.isPresent() && isRecipeValid(recipe.get())) {
				this.processRecipe(recipe.get());
			} else if (brewingItems.size() >= 3) { // Only fail if enough items are tossed in
				this.triggerFailureEffect(); // ❌ Incorrect recipe → trigger failure
			}
		}
	}

	private void pickupItems() {
		BlockPos abovePos = this.worldPosition.above();
		List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, new AABB(abovePos));

		boolean pickedUpAny = false;

		for (ItemEntity itemEntity : items) {
			ItemStack stack = itemEntity.getItem();
			if (!stack.isEmpty()) {
				brewingItems.add(stack.copy());
				itemEntity.remove(Entity.RemovalReason.KILLED);
				pickedUpAny = true;
			}
		}

		if (pickedUpAny) {
			// 🎵 Play bubbling sound
			level.playSound(null, worldPosition, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 0.7F, 1.2F);

			// ✨ Spawn particle effects
			if (level instanceof ServerLevel server) {
				server.sendParticles(ParticleTypes.BUBBLE,
						worldPosition.getX() + 0.5, worldPosition.getY() + 1.0, worldPosition.getZ() + 0.5,
						6, 0.2, 0.3, 0.2, 0.02);
				server.sendParticles(ParticleTypes.SMOKE,
						worldPosition.getX() + 0.5, worldPosition.getY() + 1.0, worldPosition.getZ() + 0.5,
						4, 0.2, 0.2, 0.2, 0.01);
			}
		}
	}

	private Optional<WitchesCauldronRecipe> getRecipe() {
		SimpleContainer inventory = new SimpleContainer(brewingItems.size());
		for (int i = 0; i < brewingItems.size(); i++) {
			inventory.setItem(i, brewingItems.get(i));
		}
		return level.getRecipeManager().getRecipeFor(WitchesCauldronRecipe.Type.INSTANCE, inventory, level);
	}

	private boolean isRecipeValid(WitchesCauldronRecipe recipe) {
		List<Ingredient> requiredIngredients = recipe.getIngredients();
		List<ItemStack> currentIngredients = new ArrayList<>(brewingItems);

		for (Ingredient ingredient : requiredIngredients) {
			boolean found = false;
			for (ItemStack stack : currentIngredients) {
				if (ingredient.test(stack)) {
					currentIngredients.remove(stack);
					found = true;
					break;
				}
			}
			if (!found) return false;
		}
		return currentIngredients.isEmpty(); // Ensure no extra items are present
	}

	private void processRecipe(WitchesCauldronRecipe recipe) {
		ItemStack result = recipe.getResultItem(level.registryAccess());

		if (!result.isEmpty()) {
			// Spawn the brewed item
			ItemEntity itemEntity = new ItemEntity(level,
					worldPosition.getX() + 0.5, worldPosition.getY() + 1, worldPosition.getZ() + 0.5, result);
			level.addFreshEntity(itemEntity);

			// Consume one water level
			BlockState state = level.getBlockState(worldPosition);
			if (state.hasProperty(WitchesCauldron.LEVEL)) {
				int levelValue = state.getValue(WitchesCauldron.LEVEL);
				if (levelValue > 0) {
					level.setBlock(worldPosition, state.setValue(WitchesCauldron.LEVEL, levelValue - 1), 3);
				}
			}

			// Clear the input items
			brewingItems.clear();
		}
	}

	private void triggerFailureEffect() {
		int randomEffect = level.random.nextInt(5);
		switch (randomEffect) {
			case 0: // 💥 Explosion
				level.explode(null, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), 2.0F, Level.ExplosionInteraction.BLOCK);
				break;
			case 1: // ⚡ Lightning Strike
				LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
				if (lightning != null) {
					lightning.moveTo(Vec3.atBottomCenterOf(worldPosition));
					level.addFreshEntity(lightning);
				}
				break;
			case 2: // ☠️ Wither Effect on Player
				AABB area = new AABB(worldPosition).inflate(3);
				List<Player> players = level.getEntitiesOfClass(Player.class, area);
				for (Player player : players) {
					player.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
				}
				break;
			case 3: // 👻 Witch Spawns
				Witch witch = EntityType.WITCH.create(level);
				if (witch != null) {
					witch.moveTo(Vec3.atBottomCenterOf(worldPosition.above()));
					level.addFreshEntity(witch);
				}
				break;
			case 4: // 🎭 Potion Backfire
				areaEffectCloud();
				break;
		}

		brewingItems.clear(); // ✅ Clear items on failure
	}

	private void areaEffectCloud() {
		AreaEffectCloud cloud = new AreaEffectCloud(level, worldPosition.getX() + 0.5, worldPosition.getY() + 1, worldPosition.getZ() + 0.5);
		cloud.setRadius(2.5F);
		cloud.setDuration(100);
		cloud.setPotion(Potions.HARMING);
		level.addFreshEntity(cloud);
	}

	private boolean hasHeatSourceBelow() {
		BlockState stateBelow = level.getBlockState(worldPosition.below());
		return HEAT_SOURCES.contains(stateBelow.getBlock());
	}
}