package net.vector.weaponseffect.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.vector.weaponseffect.WeaponsEffect;


//Isto cria um guia para os Items do Mod
public class ModWeaponsEffectTabs {
    public static final DeferredRegister<CreativeModeTab> Weapons_Effect =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WeaponsEffect.MOD_ID);


    public static void register(IEventBus eventBus) {
        Weapons_Effect.register(eventBus);
    }
}
