package net.vector.weaponseffect.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record SimpleCraftingTableRecipeInput(net.minecraftforge.items.ItemStackHandler input) implements RecipeInput {
    @Override
    public ItemStack getItem(int pIndex) {
        return input.getStackInSlot(pIndex);
    }

    @Override
    public int size() {
        return 25;
    }
}