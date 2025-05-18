package net.vector.weaponseffect.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "weaponseffect");
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, "weaponseffect");


    public static final RegistryObject<RecipeSerializer<SimpleCraftingTableRecipe>> SIMPLE_CRAFTING_TABLE_SERIALIZER =
            SERIALIZERS.register("simple_crafting_table", SimpleCraftingTableRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<SimpleCraftingTableRecipe>> SIMPLE_CRAFTING_TABLE_TYPE =
            TYPES.register("simple_crafting_table", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "simple_crafting_table";
                }
            });




    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
