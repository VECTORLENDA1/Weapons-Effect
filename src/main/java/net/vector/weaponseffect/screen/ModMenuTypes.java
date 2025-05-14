package net.vector.weaponseffect.screen;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vector.weaponseffect.screen.custom.SimpleCraftingTableMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, "weaponseffect");

    public static final RegistryObject<MenuType<SimpleCraftingTableMenu>> SIMPLE_CRAFTING_TABLE_MENU =
            MENU.register("simple_crafting_table_name", () -> IForgeMenuType.create(SimpleCraftingTableMenu::new));

    public static void register(IEventBus eventBus) {
        MENU.register(eventBus);
    }
}
