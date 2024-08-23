package net.vector.weaponseffect.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.vector.weaponseffect.WeaponsEffect;

import static net.minecraft.core.registries.Registries.ENCHANTMENT;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ENCHANTMENT, WeaponsEffect.MOD_ID);


    public static void Register (IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);

    }

}
