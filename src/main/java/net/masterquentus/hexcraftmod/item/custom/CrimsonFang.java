package net.masterquentus.hexcraftmod.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class CrimsonFang extends SwordItem {

    private static final String UPGRADED_TAG = "upgradedCrimsonFang"; // To track if the weapon is upgraded

    public CrimsonFang() {
        super(Tiers.NETHERITE, 3, -1.6F, new Item.Properties().stacksTo(1));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Base form: Heal the attacker on hit (lifesteal)
        attacker.heal(2.0F);

        // Check if the weapon is upgraded (Soul + Contract Applied)
        if (isUpgraded(stack)) {
            // Additional logic for upgraded weapon
            if (attacker instanceof Player player && player.getHealth() == player.getMaxHealth()) {
                // Apply Vampiric Wrath - Bonus damage to full health players
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 0));
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (entity instanceof Player player) {
            if (isUpgraded(stack) && player.getHealth() == player.getMaxHealth()) {
                // Vampiric Wrath effect when fully healed
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 0));
            }
        }
    }

    // Utility method to check if the item has been upgraded
    private boolean isUpgraded(ItemStack stack) {
        return stack.getTag() != null && stack.getTag().getBoolean(UPGRADED_TAG);
    }

    // This method would be called when you apply the upgrade (e.g., using a soul and contract item)
    public static void applyUpgrade(ItemStack stack) {
        if (stack.getTag() == null) {
            stack.setTag(new CompoundTag());
        }
        stack.getTag().putBoolean(UPGRADED_TAG, true); // Mark the weapon as upgraded
    }

}