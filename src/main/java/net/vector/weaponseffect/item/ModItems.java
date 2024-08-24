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
                    .attributes(SwordItem.createAttributes(ModToolTiers.FIRE_SWORD,4,-2.4F))));
    public static final RegistryObject<Item> WITHER_SWORD = ITEMS.register("wither_sword",
            () -> new WitherSwordItem(ModToolTiers.WITHER_SWORD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.WITHER_SWORD, 5, -2F))));
    public static final RegistryObject<Item> BLINDNESS_DAGGER = ITEMS.register("blindness_dagger",
            () -> new BlindnessDaggerItem(ModToolTiers.BLINDNESS_DAGGER, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.BLINDNESS_DAGGER, 5, -2F))));
    public static final RegistryObject<Item> DARKNESS_MACE = ITEMS.register("darkness_mace",
            () -> new DarknessMaceItem(ModToolTiers.DARKNESS_MACE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.DARKNESS_MACE, 5, -2F))));
    public static final RegistryObject<Item> GIMLIS_AXE = ITEMS.register("gimlis_axe",
            () -> new GimlisAxeItem(ModToolTiers.GIMLIS_AXE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.GIMLIS_AXE, 5, -2F))));
    public static final RegistryObject<Item> ICE_SWORD = ITEMS.register("ice_sword",
            () -> new IceSwordItem(ModToolTiers.ICE_SWORD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.ICE_SWORD, 5, -2F))));
    public static final RegistryObject<Item> LANCE = ITEMS.register("lance",
            () -> new LanceItem(ModToolTiers.LANCE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.LANCE, 5, -2F))));
    public static final RegistryObject<Item> POISON_SWORD = ITEMS.register("poison_sword",
            () -> new PoisonSwordItem(ModToolTiers.POISON_SWORD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.POISON_SWORD, 5, -2F))));
    public static final RegistryObject<Item> STRENGTH_HAMMER = ITEMS.register("strength_hammer",
            () -> new StrengthHammerItem(ModToolTiers.STRENGTH_HAMMER, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.STRENGTH_HAMMER, 5, -2F))));
    public static final RegistryObject<Item> WINGS_OF_DOOM = ITEMS.register("wings_of_doom",
            () -> new WingsOfDoomItem(ModToolTiers.WINGS_OF_DOOM, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.WINGS_OF_DOOM, 5, -2F))));
    public static final RegistryObject<Item> SWIFTNESS_DAGGER = ITEMS.register("swiftness_dagger",
            () -> new SwiftnessDaggerItem(ModToolTiers.SWIFTNESS_DAGGER, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.SWIFTNESS_DAGGER, 5, -2F))));




    //isto é só para dizer ao forge que este "Defer" é o nosso "MOD_ID"
    public static void Register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private ItemStack fireSwordItem;

    public ItemStack getFireSwordItem() {
        return fireSwordItem;
    }





}
