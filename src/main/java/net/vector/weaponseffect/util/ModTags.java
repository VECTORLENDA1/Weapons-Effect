package net.vector.weaponseffect.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> FIRE_SWORD = tag("fire_sword");
        public static final TagKey<Block> WITHER_SWORD = tag("wither_sword");
        public static final TagKey<Block> BLINDNESS_DAGGER = tag("blindness_dagger");
        public static final TagKey<Block> DARKNESS_MACE = tag("darkness_mace");
        public static final TagKey<Block> GIMLIS_AXE = tag("gimlis_axe");
        public static final TagKey<Block> ICE_SWORD = tag("ice_sword");
        public static final TagKey<Block> LANCE = tag("lance");
        public static final TagKey<Block> POISON_SWORD = tag("poison_sword");
        public static final TagKey<Block> STRENGTHS_HAMMER = tag("strengths_hammer");
        public static final TagKey<Block> WINGS_OF_DOOM = tag("wings_of_doom");
        public static final TagKey<Block> SWIFTNESS_DAGGER = tag("swiftness_dagger");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("weaponseffect", name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("weaponseffect", name));
        }
    }
}
