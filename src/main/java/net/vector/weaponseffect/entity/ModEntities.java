package net.vector.weaponseffect.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vector.weaponseffect.WeaponsEffect;

import static net.vector.weaponseffect.WeaponsEffect.MODID;
import static net.vector.weaponseffect.WeaponsEffect.MODID;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<ThrownTrident>> THROWN_LANCE = ENTITY_TYPES.register("thrown_lance",
            () -> EntityType.Builder.<ThrownTrident>of(ThrownTrident::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .build(MODID + ":thrown_lance")
    );

    public static final RegistryObject<EntityType<BlackHoleEntity>> BLACK_HOLE = ENTITY_TYPES.register("black_hole",
            () -> EntityType.Builder.<BlackHoleEntity>of(BlackHoleEntity::new, MobCategory.MISC)
                    .sized(2.0F, 2.0F)
                    .build(":black_hole"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
