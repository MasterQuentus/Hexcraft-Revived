package net.masterquentus.hexcraftmod.events;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.masterquentus.hexcraftmod.spells.SiphonerDataProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HexcraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModForgeEvents {

	private static boolean isPoppetBoundToPlayer(ItemStack poppet, Player player) {
		CompoundTag tag = poppet.getTag();
		if (tag == null || !tag.contains("BoundPlayer")) {
			return false; // No taglock, poppet won't work
		}
		UUID boundUUID = tag.getUUID("BoundPlayer");
		return boundUUID.equals(player.getUUID()); // Only works if UUID matches
	}

	@SubscribeEvent
	public static void onPlayerHurtInVoid(LivingHurtEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player)) return;

		if (event.getSource().is(DamageTypes.FELL_OUT_OF_WORLD)) {
			for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
				ItemStack stack = player.getInventory().getItem(i);
				if (stack.getItem() == HexcraftItems.VOID_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {
					event.setCanceled(true);
					BlockPos respawnPos = player.getRespawnPosition() != null ? player.getRespawnPosition() : player.level().getSharedSpawnPos();
					player.teleportTo(respawnPos.getX() + 0.5, respawnPos.getY(), respawnPos.getZ() + 0.5);
					stack.shrink(1);
					break;
				}
			}
		}

		if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;

		// List of curses the poppet will remove
		List<MobEffect> curses = Arrays.asList(
				MobEffects.WEAKNESS,
				MobEffects.MOVEMENT_SLOWDOWN,
				MobEffects.DIG_SLOWDOWN,
				MobEffects.DARKNESS,
				MobEffects.BAD_OMEN,
				MobEffects.LEVITATION,
				MobEffects.CONFUSION // Nausea
		);

		for (int i = 0; i < serverPlayer.getInventory().getContainerSize(); i++) {
			ItemStack stack = serverPlayer.getInventory().getItem(i);
			if (stack.getItem() == HexcraftItems.CURSE_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, serverPlayer)) {

				boolean removedCurse = false;

				for (MobEffect curse : curses) {
					if (serverPlayer.hasEffect(curse)) {
						serverPlayer.removeEffect(curse);
						removedCurse = true;
					}
				}

				if (removedCurse) {
					stack.hurtAndBreak(1, serverPlayer, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
					break; // Stop after one poppet is used
				}
			}
		}

		if (event.getSource().is(DamageTypes.GENERIC) || event.getSource().is(DamageTypes.PLAYER_ATTACK)) {
			ItemStack heldItem = player.getMainHandItem();

			// Check if the held item is a tool and about to break
			if (!heldItem.isEmpty() && heldItem.isDamageableItem() && heldItem.getDamageValue() >= heldItem.getMaxDamage() - 1) {
				for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
					ItemStack stack = player.getInventory().getItem(i);

					if (stack.getItem() == HexcraftItems.TOOL_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {
						event.setCanceled(true); // Prevents tool from breaking

						// Set tool durability to 1 instead of breaking
						heldItem.setDamageValue(heldItem.getMaxDamage() - 1);

						// Damage the poppet
						stack.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));

						player.sendSystemMessage(Component.literal("Your Tool Protection Poppet saved your tool!").withStyle(ChatFormatting.GREEN));

						// Remove poppet if durability is fully used
						if (stack.getDamageValue() >= stack.getMaxDamage()) {
							stack.shrink(1);
							player.sendSystemMessage(Component.literal("Your Tool Protection Poppet has been destroyed!").withStyle(ChatFormatting.RED));
						}
						break;
					}
				}
			}

			if (event.getSource().is(DamageTypes.FALL)) {
				float damage = event.getAmount();
				int blocksFromDamage = Math.round(damage / 2.0f);
				for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
					ItemStack stack = player.getInventory().getItem(i);
					if (stack.getItem() == HexcraftItems.FALL_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {
						stack.hurtAndBreak(blocksFromDamage, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
						event.setCanceled(true);
						break;
					}
				}
			}

			if (event.getSource().is(DamageTypes.EXPLOSION) || event.getSource().is(DamageTypes.PLAYER_EXPLOSION)) {
				float damage = event.getAmount();
				for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
					ItemStack stack = player.getInventory().getItem(i);
					if (stack.getItem() == HexcraftItems.EXPLOSION_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {
						stack.hurtAndBreak((int) damage, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
						event.setCanceled(true);
						break;
					}
				}
			}

			if (event.getSource().is(DamageTypes.WITHER) || event.getSource().is(DamageTypes.WITHER_SKULL)) {
				float damage = event.getAmount();
				for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
					ItemStack stack = player.getInventory().getItem(i);
					if (stack.getItem() == HexcraftItems.WITHER_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {
						stack.hurtAndBreak((int) damage, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
						event.setCanceled(true);
						break;
					}
				}
			}

			if (event.getSource().is(DamageTypes.MOB_ATTACK) || event.getSource().is(DamageTypes.PLAYER_ATTACK) ||
					event.getSource().is(DamageTypes.MOB_PROJECTILE) || event.getSource().is(DamageTypes.ARROW) ||
					event.getSource().is(DamageTypes.THROWN) || event.getSource().is(DamageTypes.TRIDENT)) {

				float damage = event.getAmount();
				float absorbedDamage = damage * 0.5f; // Absorb 50%
				float remainingDamage = damage * 0.5f; // Player still takes 50%

				for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
					ItemStack stack = player.getInventory().getItem(i);
					if (stack.getItem() == HexcraftItems.ARMOR_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {
						// Absorb 50% of the damage and reduce poppet durability
						stack.hurtAndBreak((int) absorbedDamage, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));

						// Reduce the actual damage taken by the player
						event.setAmount(remainingDamage);
						break;
					}
				}
			}

			if (event.getSource().is(DamageTypes.MAGIC) || event.getSource().is(DamageTypes.INDIRECT_MAGIC)) {
				for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
					ItemStack stack = player.getInventory().getItem(i);
					if (stack.getItem() == HexcraftItems.POTION_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {
						Collection<MobEffectInstance> activeEffects = player.getActiveEffects();
						for (MobEffectInstance effectInstance : activeEffects) {
							if (!effectInstance.getEffect().isBeneficial()) {
								player.removeEffect(effectInstance.getEffect());
							}
						}
						stack.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
						event.setCanceled(true);
						break;
					}
				}
			}

			if (event.getSource().getEntity() instanceof LivingEntity attacker) {
				float damage = event.getAmount();

				for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
					ItemStack stack = player.getInventory().getItem(i);
					if (stack.getItem() == HexcraftItems.VAMPIRIC_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {
						float lifestealAmount = damage * 0.5f; // Steal 50% of damage

						// Heal the player
						player.heal(lifestealAmount);

						// Damage the attacker
						attacker.hurt(player.damageSources().indirectMagic(player, attacker), lifestealAmount);

						// Damage the poppet
						stack.hurtAndBreak((int) lifestealAmount, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));

						// Cancel or reduce the original damage
						event.setAmount(damage * 0.5f); // Player only takes half damage
						break;
					}
				}
			}

			if (event.getSource().getEntity() instanceof LivingEntity attacker) {
				float damage = event.getAmount();

				for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
					ItemStack stack = player.getInventory().getItem(i);
					if (stack.getItem() == HexcraftItems.VOODOO_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {

						// Reflect a portion of damage
						float reflectedDamage = damage * 0.3f; // 30% of damage is reflected
						attacker.hurt(player.damageSources().indirectMagic(player, attacker), reflectedDamage);

						// Transfer negative effects
						for (MobEffectInstance effect : player.getActiveEffects()) {
							if (!effect.getEffect().isBeneficial()) {
								player.removeEffect(effect.getEffect());
								attacker.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier()));
							}
						}

						// Apply additional random negative effects to the attacker
						if (player.getRandom().nextFloat() < 0.3) { // 30% chance to apply extra effect
							attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
							attacker.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 1));
						}

						// Damage the poppet
						stack.hurtAndBreak((int) damage, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));

						// Reduce the incoming damage
						event.setAmount(damage * 0.7f); // Player only takes 70% of the original damage
						break;
					}
				}
			}

			if (!(event.getEntity() instanceof ServerPlayer)) return;

			for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
				ItemStack stack = player.getInventory().getItem(i);

				// Ensure it's a Voodoo Protection Poppet and is bound to the player
				if (stack.getItem() == HexcraftItems.VOODOO_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {

					// Get the attacker (could be a player or mob)
					Entity attacker = event.getSource().getEntity();
					if (attacker instanceof LivingEntity livingAttacker) {

						// 50% chance to block the effect
						if (player.getRandom().nextFloat() < 0.5) {
							event.setCanceled(true);
							player.sendSystemMessage(Component.literal("Your Voodoo Protection Poppet blocked a curse!").withStyle(ChatFormatting.GREEN));
						} else {
							// 30% chance to reflect the curse back to the attacker
							if (player.getRandom().nextFloat() < 0.3) {
								livingAttacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
								livingAttacker.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 1));
								player.sendSystemMessage(Component.literal("Your Voodoo Protection Poppet reflected a curse!").withStyle(ChatFormatting.GOLD));
							}
						}

						// Damage the poppet when it blocks or reflects a curse
						stack.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
						break;
					}
				}
			}


			float damage = event.getAmount();
			if (damage >= player.getHealth()) {
				for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
					ItemStack stack = player.getInventory().getItem(i);
					if (stack.getItem() == HexcraftItems.DEATH_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {
						event.setCanceled(true);
						player.heal(8.0f);
						stack.shrink(1);
						break;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		if (!(event.player instanceof ServerPlayer player)) return;

		if (player.getFoodData().getFoodLevel() >= 20) return;

		for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
			ItemStack stack = player.getInventory().getItem(i);
			if (stack.getItem() == HexcraftItems.HUNGER_PROTECTION_POPPET.get() && isPoppetBoundToPlayer(stack, player)) {
				player.getFoodData().setFoodLevel(20);
				stack.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
				break;
			}
		}
	}


	private static boolean hasTotemInHand(int inventorySize, ServerPlayer player) {
		for (int i = 0; i < inventorySize; i++) {
			if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.TOTEM_OF_UNDYING
					|| player.getItemInHand(InteractionHand.OFF_HAND).getItem() == Items.TOTEM_OF_UNDYING)
				return true;
		}

		return false;
	}

	@SubscribeEvent
	public static void attachCapabilitiesPlayer(AttachCapabilitiesEvent<Player> event) {
		event.addCapability(new ResourceLocation("hexcraftmod", "siphoner_data"), new SiphonerDataProvider());
	}


	protected static BlockHitResult getPlayerPOVHitResult(Level pLevel, Player pPlayer, ClipContext.Fluid pFluidMode) {
		float f = pPlayer.getXRot();
		float f1 = pPlayer.getYRot();
		Vec3 vec3 = pPlayer.getEyePosition();
		float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
		float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
		float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
		float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d0 = pPlayer.getBlockReach();
		Vec3 vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
		return pLevel.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, pFluidMode, pPlayer));
	}

	@SubscribeEvent
	public static void giveBloodBottleItem(PlayerInteractEvent.RightClickItem event) {
		if (event.getEntity() == null) {
			return;
		}
		Player player = event.getEntity();
		HitResult hitresult = getPlayerPOVHitResult(event.getLevel(), event.getEntity(), ClipContext.Fluid.SOURCE_ONLY);
		if (hitresult.getType() == HitResult.Type.MISS) {
			return;
		}
		if (hitresult.getType() == HitResult.Type.BLOCK) {
			BlockPos blockpos = ((BlockHitResult) hitresult).getBlockPos();
			if (!event.getLevel().getFluidState(blockpos).is(HexcraftBlocks.BLOOD_BLOCK.get().getFluid())) {
				return;
			}
			ItemStack itemInHand = player.getItemInHand(event.getHand());
			if (itemInHand.is(Items.GLASS_BOTTLE)) {
				event.setCanceled(true);
				player.swing(event.getHand());

				ItemStack bloodBottle = new ItemStack(HexcraftItems.BLOOD_BOTTLE.get(), 1);
				player.level().playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
				player.level().gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);
				player.addItem(bloodBottle);
				if (!player.isCreative()) {
					itemInHand.shrink(1);
				}
				return;
			}
		}
	}
} 
