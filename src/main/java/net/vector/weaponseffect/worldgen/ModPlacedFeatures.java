package net.vector.weaponseffect.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.vector.weaponseffect.WeaponsEffect;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> IGNITHRA_ORE_PLACED_KEY = registerKey( "ignithra_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_WEAPONSEFFECT_ORE_PLACED_KEY = registerKey( "weaponseffect_ore_placed");
    public static final ResourceKey<PlacedFeature> END_STONE_CELESTINE_ORE_PLACED_KEY = registerKey( "celestine_ore_placed");

    public static void bootstrap(BootstrapContext <PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

         register(context, IGNITHRA_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_IGNITHRA_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(8,
                         HeightRangePlacement.uniform(VerticalAnchor.absolute(20), VerticalAnchor.absolute(60))));

        register(context, END_STONE_CELESTINE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_STONE_CELESTINE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.absolute(50))));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(WeaponsEffect.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }



}