package net.vector.weaponseffect.block;


import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vector.weaponseffect.WeaponsEffect;
import net.vector.weaponseffect.item.ModItems;

import java.util.function.Supplier;



public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, WeaponsEffect.MOD_ID);

    public static final RegistryObject<Block> END_STONE_CELESTINE_ORE = registerBlockItem("end_stone_celestine_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> NEXALITE_BLOCK = registerBlockItem("nexalite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final RegistryObject<Block> IGNITHRA_BLOCK = registerBlockItem("ignithra_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final RegistryObject<Block> DEEPSLATE_IGNITHRA_ORE = registerBlockItem("deepslate_ignithra_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> IGNITHRA_ORE = registerBlockItem("ignithra_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> RAW_IGNITHRA_BLOCK = registerBlockItem("raw_ignithra_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> ASTRALITE_BLOCK = registerBlockItem("astralite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final RegistryObject<Block> DEEPSLATE_ASTRALITE_ORE = registerBlockItem("deepslate_astralite_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> END_STONE_ZENITHRA_ORE = registerBlockItem("end_stone_zenithra_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> RAW_ASTRALITE_BLOCK = registerBlockItem("raw_astralite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> ZENITHRA_BLOCK = registerBlockItem("zenithra_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> RAW_NEXALITE_BLOCK = registerBlockItem("raw_nexalite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> CELESTINE_BLOCK = registerBlockItem("celestine_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> DEEPSLATE_NEXALITE_ORE = registerBlockItem("deepslate_nexalite_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));




    private static <T extends Block> RegistryObject<T> registerBlockItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void Register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}