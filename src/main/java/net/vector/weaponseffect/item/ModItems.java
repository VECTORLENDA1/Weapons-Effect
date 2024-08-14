package net.vector.weaponseffect.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vector.weaponseffect.WeaponsEffect;

public class ModItems {
    //Defer = Uma lista onde os nossos items vão ficar no "Weapons Effect"
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WeaponsEffect.MOD_ID);


    public static final RegistryObject<Item> CELESTINE = ITEMS.register("celestine",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZENITHRA = ITEMS.register("zenithra",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ASTRALITE = ITEMS.register("astralite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ASTRALITE = ITEMS.register("raw_astralite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_NEXALITE = ITEMS.register("raw_nexalite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NEXALITE = ITEMS.register("nexalite",
            () -> new Item(new Item.Properties()));


    //isto é só para dizer ao forge que este "Defer" é o nosso "MOD_ID"
    public static void Register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
