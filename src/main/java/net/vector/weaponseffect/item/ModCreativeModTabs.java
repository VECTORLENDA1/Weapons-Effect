package net.vector.weaponseffect.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.vector.weaponseffect.WeaponsEffect;
import net.vector.weaponseffect.block.ModBlocks;


//Isto serve para cria um guia no modo creativo dop jogo, para os teus items
public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WeaponsEffect.MODID);

    public static final RegistryObject<CreativeModeTab> WEAPONS_EFFECT = CREATIVE_MODE_TABS.register("weapons_effect",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CELESTINE.get()))
                .title(Component.translatable("creativetab.weapons_effect"))
                .displayItems((pParameters, pOutput) -> {

                    //ITEMS//
                    pOutput.accept(ModItems.CELESTINE.get());
                    pOutput.accept(ModItems.ZENITHRA.get());
                    pOutput.accept(ModItems.ASTRALITE.get());
                    pOutput.accept(ModItems.RAW_ASTRALITE.get());
                    pOutput.accept(ModItems.RAW_NEXALITE.get());
                    pOutput.accept(ModItems.NEXALITE.get());
                    pOutput.accept(ModItems.IGNITHRA.get());
                    pOutput.accept(ModItems.RAW_IGNITHRA.get());
                    pOutput.accept(ModItems.ANTRACITE.get());
                    pOutput.accept(ModItems.RAW_OBSCURIDIUM.get());
                    pOutput.accept(ModItems.OBSCURIDIUM.get());
                    pOutput.accept(ModItems.OBSCURITE.get());


                    //BLOCKS//
                    pOutput.accept(ModBlocks.END_STONE_CELESTINE_ORE.get());
                    pOutput.accept(ModBlocks.NEXALITE_BLOCK.get());
                    pOutput.accept(ModBlocks.IGNITHRA_BLOCK.get());
                    pOutput.accept(ModBlocks.RAW_IGNITHRA_BLOCK.get());
                    pOutput.accept(ModBlocks.DEEPSLATE_IGNITHRA_ORE.get());
                    pOutput.accept(ModBlocks.IGNITHRA_ORE.get());
                    pOutput.accept(ModBlocks.ASTRALITE_BLOCK.get());
                    pOutput.accept(ModBlocks.DEEPSLATE_ASTRALITE_ORE.get());
                    pOutput.accept(ModBlocks.END_STONE_ZENITHRA_ORE.get());
                    pOutput.accept(ModBlocks.RAW_NEXALITE_BLOCK.get());
                    pOutput.accept(ModBlocks.RAW_ASTRALITE_BLOCK.get());
                    pOutput.accept(ModBlocks.ZENITHRA_BLOCK.get());
                    pOutput.accept(ModBlocks.CELESTINE_BLOCK.get());
                    pOutput.accept(ModBlocks.DEEPSLATE_NEXALITE_ORE.get());
                    pOutput.accept(ModBlocks.NETHER_ANTRACITE_ORE.get());
                    pOutput.accept(ModBlocks.ANTRACITE_BLOCK.get());
                    pOutput.accept(ModBlocks.BEDROCK_OBSCURIDIUM_ORE.get());
                    pOutput.accept(ModBlocks.OBSCURIDIUM_BLOCK.get());
                    pOutput.accept(ModBlocks.RAW_OBSCURIDIUM_BLOCK.get());
                    pOutput.accept(ModBlocks.OBSCURITE_BLOCK.get());


                    //WEAPONS//
                    pOutput.accept(ModItems.FIRE_SWORD.get());
                    pOutput.accept(ModItems.WITHER_SWORD.get());
                    pOutput.accept(ModItems.BLINDNESS_DAGGER.get());
                    pOutput.accept(ModItems.DARKNESS_MACE.get());
                    pOutput.accept(ModItems.GIMLIS_AXE.get());
                    pOutput.accept(ModItems.ICE_SWORD.get());
                    pOutput.accept(ModItems.LANCE.get());
                    pOutput.accept(ModItems.POISON_SWORD.get());
                    pOutput.accept(ModItems.HAMMER.get());
                    pOutput.accept(ModItems.WINGS_OF_DOOM.get());
                    pOutput.accept(ModItems.SWIFTNESS_DAGGER.get());
            })
                .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
