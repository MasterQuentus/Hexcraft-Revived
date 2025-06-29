package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DamageEventHandler {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();

        // Check if the entity is a vampire (Player)
        if (entity instanceof Player player && player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) {
            // Check if the attacker is using a wooden weapon
            if (event.getSource().getEntity() instanceof Player attacker) {
                ItemStack weapon = attacker.getMainHandItem();
                if (weapon.getItem() instanceof SwordItem && ((SwordItem) weapon.getItem()).getTier() == Tiers.WOOD) {
                    event.setAmount(event.getAmount() * 1.5F); // Increase damage by 50%
                }
            }
        }
    }
}