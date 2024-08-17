package net.vector.weaponseffect.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.vector.weaponseffect.WeaponsEffect;
import net.vector.weaponseffect.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_IGNITHRA_ORE_KEY = registerKey("ignithra_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_WEAPONSEFFECT_ORE_KEY = registerKey("weaponseffect_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_STONE_CELESTINE_ORE_KEY = registerKey("end_stone_celestine_ore");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>>context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new TagMatchTest(Tags.Blocks.NETHERRACK);
        RuleTest endReplaceables = new TagMatchTest(Tags.Blocks.END_STONES);


        List<OreConfiguration.TargetBlockState> overworldIgnithraOres = List.of(OreConfiguration.target(stoneReplaceables,
                ModBlocks.IGNITHRA_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_IGNITHRA_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_IGNITHRA_ORE_KEY, Feature.ORE, new OreConfiguration(overworldIgnithraOres, 5));
        register(context, NETHER_WEAPONSEFFECT_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.DEEPSLATE_IGNITHRA_ORE.get().defaultBlockState(),0));
        register(context, END_STONE_CELESTINE_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.END_STONE_CELESTINE_ORE.get().defaultBlockState(),4));


    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(WeaponsEffect.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> Key, F feature, FC configuration) {
        context.register(Key, new ConfiguredFeature<>(feature, configuration));
    }
}
