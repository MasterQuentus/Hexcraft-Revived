package net.masterquentus.hexcraftmod.block.entity.boats;

import net.masterquentus.hexcraftmod.entity.HexcraftEntities;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HexcraftChestBoatEntity extends ChestBoat {
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class,
			EntityDataSerializers.INT);

	public HexcraftChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	public HexcraftChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
		this(HexcraftEntities.HEXCRAFT_CHEST_BOAT.get(), pLevel);
		this.setPos(pX, pY, pZ);
		this.xo = pX;
		this.yo = pY;
		this.zo = pZ;
	}

	@Override
	public Item getDropItem() {
		return switch (getModVariant()) {
			case EBONY -> HexcraftItems.EBONY_CHEST_BOAT.get();
			case ALDER -> HexcraftItems.ALDER_CHEST_BOAT.get();
			case BLOOD_OAK -> HexcraftItems.BLOOD_OAK_CHEST_BOAT.get();
			case CEDAR -> HexcraftItems.CEDAR_CHEST_BOAT.get();
			case DISTORTED -> HexcraftItems.DISTORTED_CHEST_BOAT.get();
			case ELDER -> HexcraftItems.ELDER_CHEST_BOAT.get();
			case HAWTHORN -> HexcraftItems.HAWTHORN_CHEST_BOAT.get();
			case HELL_BARK -> HexcraftItems.HELL_BARK_CHEST_BOAT.get();
			case JUNIPER -> HexcraftItems.JUNIPER_CHEST_BOAT.get();
			case ROWAN -> HexcraftItems.ROWAN_CHEST_BOAT.get();
			case TWISTED -> HexcraftItems.TWISTED_CHEST_BOAT.get();
			case WHITE_OAK -> HexcraftItems.WHITE_OAK_CHEST_BOAT.get();
			case WILLOW -> HexcraftItems.WILLOW_CHEST_BOAT.get();
			case WITCH_HAZEL -> HexcraftItems.WITCH_HAZEL_CHEST_BOAT.get();
			case WITCH_WOOD -> HexcraftItems.WITCH_WOOD_CHEST_BOAT.get();
			case ECHO_WOOD -> HexcraftItems.ECHO_WOOD_CHEST_BOAT.get();
			case PHOENIX -> HexcraftItems.PHOENIX_CHEST_BOAT.get();
		};
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(this.getDropItem());
	}


	public void setVariant(HexcraftBoatEntity.Type variant) {
		this.entityData.set(DATA_ID_TYPE, variant.ordinal());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_ID_TYPE, -1); // -1 means "undefined"
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("BoatType", this.entityData.get(DATA_ID_TYPE)); // Store type as integer
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("BoatType")) {
			this.entityData.set(DATA_ID_TYPE, compound.getInt("BoatType"));
		}
	}

	public HexcraftBoatEntity.Type getModVariant() {
		int id = this.entityData.get(DATA_ID_TYPE);
		return id >= 0 && id < HexcraftBoatEntity.Type.values().length ? HexcraftBoatEntity.Type.byId(id) : HexcraftBoatEntity.Type.EBONY;
	}
}