package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VampireWeaknessHandler {

    // **🔥 Vampires Burn in Sunlight (Unless Wearing Blessed Daylight Ring)**
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) {
            // **Burn if in daylight and NOT wearing a Blessed Daylight Ring**
            if (player.level().isDay() && !player.getInventory().contains(new ItemStack(HexcraftItems.BLESSED_DAYLIGHT_RING.get()))) {
                player.setSecondsOnFire(4);
            }
        }
    }

    // **⚔️ Wooden Weapons Deal More Damage to Vampires**
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) {
            DamageSource source = event.getSource();
            if (source.getEntity() instanceof Player attacker) {
                ItemStack weapon = attacker.getMainHandItem();
                if (weapon.getItem() instanceof SwordItem && ((SwordItem) weapon.getItem()).getTier() == Tiers.WOOD) {
                    event.setAmount(event.getAmount() * 1.5F); // **50% More Damage from Wood**
                }
            }
        }
    }

    // **🩸 Vervain Potion Weakens Vampires**
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get()) && player.hasEffect(HexcraftEffects.VERVAIN_BURN.get())) {
            event.setAmount(event.getAmount() * 1.25F); // **Take 25% More Damage While Affected by Vervain**
        }
    }
}