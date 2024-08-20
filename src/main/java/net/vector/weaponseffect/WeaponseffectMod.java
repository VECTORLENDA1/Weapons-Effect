package net.vector.weaponseffect;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("weaponseffect")
public class WeaponseffectMod {
    public WeaponseffectMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some common setup code
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Some client setup code
    }
}
