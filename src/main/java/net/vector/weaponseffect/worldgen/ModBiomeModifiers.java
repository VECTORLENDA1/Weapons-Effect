package net.vector.weaponseffect.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.vector.weaponseffect.WeaponsEffect;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_IGNITHRA_ORE = registerKey("add_ignithra_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_STONE_CELESTINE_ORE = registerKey("add_end_stone_celestine_ore");


public static void bootstrap(BootstrapContext<BiomeModifier> context) {
    var placeFeatures = context.lookup(Registries.PLACED_FEATURE);
    var biomes = context.lookup(Registries.BIOME);

    context.register(ADD_IGNITHRA_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
            biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
            HolderSet.direct(placeFeatures.getOrThrow(ModPlacedFeatures.IGNITHRA_ORE_PLACED_KEY)),
            GenerationStep.Decoration.UNDERGROUND_ORES));

    context.register(ADD_END_STONE_CELESTINE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
            biomes.getOrThrow(BiomeTags.IS_END),
            HolderSet.direct(placeFeatures.getOrThrow(ModPlacedFeatures.END_STONE_CELESTINE_ORE_PLACED_KEY)),
            GenerationStep.Decoration.UNDERGROUND_ORES));


}

    public static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(WeaponsEffect.MOD_ID, name));
    }
}