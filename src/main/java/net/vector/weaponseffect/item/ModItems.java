package net.vector.weaponseffect.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vector.weaponseffect.WeaponsEffect;
import net.vector.weaponseffect.custom.*;
import net.vector.weaponseffect.item.custom.FuelItem;
import net.vector.weaponseffect.item.custom.ModToolTiers;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WeaponsEffect.MODID);

    //Items//
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
    public static final RegistryObject<Item> RAW_OBSCURIDIUM = ITEMS.register("raw_obscuridium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OBSCURIDIUM = ITEMS.register("obscuridium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OBSCURITE = ITEMS.register("obscurite",
            () -> new Item(new Item.Properties()));




    //WEAPONS//
    public static final RegistryObject<Item> FIRE_SWORD = ITEMS.register("fire_sword",
            () -> new FireSwordItem(ModToolTiers.FIRE_SWORD, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.FIRE_SWORD,6,-2.2F))));
    public static final RegistryObject<Item> WITHER_SWORD = ITEMS.register("wither_sword",
            () -> new WitherSwordItem(ModToolTiers.WITHER_SWORD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.WITHER_SWORD, 6, -2.2F))));
    public static final RegistryObject<Item> BLINDNESS_DAGGER = ITEMS.register("blindness_dagger",
            () -> new BlindnessDaggerItem(ModToolTiers.BLINDNESS_DAGGER, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.BLINDNESS_DAGGER, 4, -0.3F))));
    public static final RegistryObject<Item> DARKNESS_MACE = ITEMS.register("darkness_mace",
            () -> new DarknessMaceItem(ModToolTiers.DARKNESS_MACE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.DARKNESS_MACE, 10, -2.8F))));
    public static final RegistryObject<Item> GIMLIS_AXE = ITEMS.register("gimlis_axe",
            () -> new GimlisAxeItem(ModToolTiers.GIMLIS_AXE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.GIMLIS_AXE, 8, -2.6F))));
    public static final RegistryObject<Item> ICE_SWORD = ITEMS.register("ice_sword",
            () -> new IceSwordItem(ModToolTiers.ICE_SWORD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.ICE_SWORD, 6, -2.2F))));
    public static final RegistryObject<Item> LANCE = ITEMS.register("lance",
            () -> new LanceItem(ModToolTiers.LANCE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.LANCE, 5, -1.3F))));
    public static final RegistryObject<Item> POISON_SWORD = ITEMS.register("poison_sword",
            () -> new PoisonSwordItem(ModToolTiers.POISON_SWORD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.POISON_SWORD, 6, -2.2F))));
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer",
            () -> new HammerItem(ModToolTiers.HAMMER, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.HAMMER, 13, -3.0F))));
    public static final RegistryObject<Item> WINGS_OF_DOOM = ITEMS.register("wings_of_doom",
            () -> new WingsOfDoomItem(ModToolTiers.WINGS_OF_DOOM, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.WINGS_OF_DOOM, 28, -0F))));
    public static final RegistryObject<Item> SWIFTNESS_DAGGER = ITEMS.register("swiftness_dagger",
            () -> new SwiftnessDaggerItem(ModToolTiers.SWIFTNESS_DAGGER, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.SWIFTNESS_DAGGER, 4, -0.3F))));


    public static void Register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
