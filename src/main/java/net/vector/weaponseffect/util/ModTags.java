package net.vector.weaponseffect.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.vector.weaponseffect.WeaponsEffect;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> BEDROCK_REPLACEABLES = tag("bedrock_replaceables");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("weaponseffect", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> FIRE_SWORD = tag("fire_sword");
        public static final TagKey<Item> WITHER_SWORD = tag("wither_sword");
        public static final TagKey<Item> BLINDNESS_DAGGER = tag("blindness_dagger");
        public static final TagKey<Item> DARKNESS_MACE = tag("darkness_mace");
        public static final TagKey<Item> GIMLIS_AXE = tag("gimlis_axe");
        public static final TagKey<Item> ICE_SWORD = tag("ice_sword");
        public static final TagKey<Item> LANCE = tag("lance");
        public static final TagKey<Item> POISON_SWORD = tag("poison_sword");
        public static final TagKey<Item> HAMMER = tag("strengths_hammer");
        public static final TagKey<Item> WINGS_OF_DOOM = tag("wings_of_doom");
        public static final TagKey<Item> SWIFTNESS_DAGGER = tag("swiftness_dagger");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaponseffect", name));
        }
    }
}
