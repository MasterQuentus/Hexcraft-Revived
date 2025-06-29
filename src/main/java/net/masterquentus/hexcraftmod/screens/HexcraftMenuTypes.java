package net.masterquentus.hexcraftmod.screens;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HexcraftMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, HexcraftMod.MOD_ID);


    public static final RegistryObject<MenuType<WitchesOvenMenu>> WITCHES_OVEN_MENU =
            registerMenuType(WitchesOvenMenu::new, "witches_oven_menu");

    public static final RegistryObject<MenuType<DistilleryMenu>> DISTILLERY_MENU =
            registerMenuType(DistilleryMenu::new, "distillery_oven_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}