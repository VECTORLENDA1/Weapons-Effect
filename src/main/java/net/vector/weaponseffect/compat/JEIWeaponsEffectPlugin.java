package net.vector.weaponseffect.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.vector.weaponseffect.block.ModBlocks;
import net.vector.weaponseffect.recipe.ModRecipes;
import net.vector.weaponseffect.recipe.SimpleCraftingTableRecipe;
import net.vector.weaponseffect.screen.custom.SimpleCraftingTableScreen;

import java.util.List;


@JeiPlugin
public class JEIWeaponsEffectPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath("weaponseffect", "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new SimpleCraftingTableRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<SimpleCraftingTableRecipe> simpleCraftingTableRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.SIMPLE_CRAFTING_TABLE_TYPE.get())
                .stream()
                .map(RecipeHolder::value)
                .toList();
        registration.addRecipes(SimpleCraftingTableRecipeCategory.SIMPLE_CRAFTING_TABLE_RECIPE_CATEGORY_RECIPE_TYPE, simpleCraftingTableRecipes);
    }

    // A clickable area for the Simple Crafting Table arrow
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(SimpleCraftingTableScreen.class,112,54,22,16,
                SimpleCraftingTableRecipeCategory.SIMPLE_CRAFTING_TABLE_RECIPE_CATEGORY_RECIPE_TYPE);
    }

    // You can houver the Simple Crafting Table in JEI to see the recipes
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIMPLE_CRAFTING_TABLE.get().asItem()),
                SimpleCraftingTableRecipeCategory.SIMPLE_CRAFTING_TABLE_RECIPE_CATEGORY_RECIPE_TYPE);
    }
}