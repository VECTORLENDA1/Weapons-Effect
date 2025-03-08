package net.vector.weaponseffect.crafting;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AnvilRecipe {
    public final Item leftItem;
    public final Item rightItem;
    public final ItemStack result;
    public final int cost;
    public final int materialCost;

    public AnvilRecipe(Item leftItem, Item rightItem, ItemStack result, int cost, int materialCost) {
        this.leftItem = leftItem;
        this.rightItem = rightItem;
        this.result = result;
        this.cost = cost;
        this.materialCost = materialCost;
    }

    public Item getLeftItem() {
        return leftItem;
    }

    public Item getRightItem() {
        return rightItem;
    }

    public ItemStack getResult() {
        return result;
    }

    public int getCost() {
        return cost;
    }

    public int getMaterialCost() {
        return materialCost;
    }

    public int getItemsRequired() {
        return materialCost;
    }

    public int getExperienceRequired() {
        return cost;
    }
}