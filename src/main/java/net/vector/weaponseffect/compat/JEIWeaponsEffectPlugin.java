package net.vector.weaponseffect.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.vector.weaponseffect.WeaponsEffect;
import net.vector.weaponseffect.crafting.ModAnvilRecipe;

import java.util.ArrayList;

@JeiPlugin
public class JEIWeaponsEffectPlugin implements IModPlugin {

    private static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath("WeaponsEffect", "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
}

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IModPlugin.super.registerCategories(registration);
        registration.addRecipeCategories(
                new AnvilRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IModPlugin.super.registerRecipes(registration);
        new ModAnvilRecipe();
        registration.addRecipes(AnvilRecipeCategory.ANVIL_TYPE, new ArrayList<>(ModAnvilRecipe.getRecipes()));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        IModPlugin.super.registerGuiHandlers(registration);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Items.ANVIL), AnvilRecipeCategory.ANVIL_TYPE);
    }
}
