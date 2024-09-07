package net.vector.weaponseffect.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vector.weaponseffect.WeaponsEffect;
import net.vector.weaponseffect.entity.projectile.ThrownLance;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WeaponsEffect.MOD_ID);

    public static final RegistryObject<EntityType<ThrownTrident>> THROWN_LANCE = ENTITY_TYPES.register("thrown_lance",
            () -> EntityType.Builder.<ThrownTrident>of(ThrownTrident::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .build(WeaponsEffect.MOD_ID + ":thrown_lance")
    );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
