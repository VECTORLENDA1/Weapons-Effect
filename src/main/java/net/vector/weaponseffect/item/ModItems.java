package net.vector.weaponseffect.item;



import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vector.weaponseffect.WeaponsEffect;
import net.vector.weaponseffect.custom.FireSwordItem;
import net.vector.weaponseffect.item.custom.FuelItem;
import net.vector.weaponseffect.item.custom.ModToolTiers;

import java.util.Map;

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
    public static final RegistryObject<Item> IGNITHRA = ITEMS.register("ignithra",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_IGNITHRA = ITEMS.register("raw_ignithra",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANTRACITE = ITEMS.register("antracite",
            () -> new FuelItem(new Item.Properties(), 3200));




    //WEAPONS
    public static final RegistryObject<Item> FIRE_SWORD = ITEMS.register("fire_sword",
            () -> new FireSwordItem(ModToolTiers.FIRE_SWORD, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.FIRE_SWORD, 4, -2.4F))));





    //isto é só para dizer ao forge que este "Defer" é o nosso "MOD_ID"
    public static void Register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
