package net.masterquentus.hexcraftmod.spells;

import net.masterquentus.hexcraftmod.magic.MagicStaminaProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static net.masterquentus.hexcraftmod.magic.CapabilityEventHandler.MAGIC_STAMINA_CAP;

/**
 * Implementation of ISiphonerData that tracks whether a player is a siphoner and their magic charge.
 */
public class SiphonerData implements ISiphonerData, INBTSerializable<CompoundTag> {

    private static final int MAX_CHARGE = 100;

    private boolean siphoner = false;
    private int charge = 0;

    @Override
    public boolean isSiphoner() {
        return siphoner;
    }

    @Override
    public void setSiphoner(boolean value) {
        this.siphoner = value;
        // TODO: Sync to client
    }

    @Override
    public int getMagicCharge() {
        return charge;
    }

    @Override
    public void recharge(int amount) {
        charge = Math.min(MAX_CHARGE, charge + amount);
        // TODO: Sync to client
    }

    @Override
    public void drainCharge(int amount) {
        charge = Math.max(0, charge - amount);
        // TODO: Sync to client
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("Siphoner", siphoner);
        tag.putInt("Charge", charge);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        siphoner = tag.getBoolean("Siphoner");
        charge = tag.getInt("Charge");
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Player> event) {
        // Attach MagicStamina
        if (!event.getObject().getCapability(MagicStaminaProvider.MAGIC_STAMINA_CAPABILITY).isPresent()) {
            event.addCapability(MAGIC_STAMINA_CAP, new MagicStaminaProvider());
        }
        // Attach SiphonerData
        if (!event.getObject().getCapability(SiphonerDataProvider.SIPHONER_DATA_CAPABILITY).isPresent()) {
            event.addCapability(new ResourceLocation("hexcraftmod", "siphoner_data"), new SiphonerDataProvider());
        }
    }
}