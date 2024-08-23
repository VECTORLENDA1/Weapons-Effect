package net.vector.weaponseffect.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vector.weaponseffect.WeaponsEffect;

public class ModEffect {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, WeaponsEffect.MOD_ID);



    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
