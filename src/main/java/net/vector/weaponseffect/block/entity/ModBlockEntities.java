package net.vector.weaponseffect.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vector.weaponseffect.block.ModBlocks;
import net.vector.weaponseffect.block.entity.custom.SimpleCraftingTableEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "weaponseffect");

public static final RegistryObject<BlockEntityType<SimpleCraftingTableEntity>> SIMPLE_CRAFTING_TABLE_BE =
        BLOCK_ENTITIES.register("simple_crafting_table_be", () -> BlockEntityType.Builder.of(
                SimpleCraftingTableEntity::new, ModBlocks.SIMPLE_CRAFTING_TABLE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
