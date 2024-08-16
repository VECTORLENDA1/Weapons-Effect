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

import java.awt.*;


//Isto serve para cria um guia no modo creativo dop jogo, para os teus items
public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WeaponsEffect.MOD_ID);

    public static final RegistryObject<CreativeModeTab> WEAPONS_EFFECT = CREATIVE_MODE_TABS.register("weapons_effect",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CELESTINE.get()))
                .title(Component.translatable("creativetab.weapons_effect"))
                .displayItems((pParameters, pOutput) -> {
                    pOutput.accept(ModItems.CELESTINE.get());
                    pOutput.accept(ModItems.ZENITHRA.get());
                    pOutput.accept(ModItems.ASTRALITE.get());
                    pOutput.accept(ModItems.RAW_ASTRALITE.get());
                    pOutput.accept(ModItems.RAW_NEXALITE.get());
                    pOutput.accept(ModItems.NEXALITE.get());
                    pOutput.accept(ModItems.IGNITHRA.get());
                    pOutput.accept(ModItems.RAW_IGNITHRA.get());

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
            })
                .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
