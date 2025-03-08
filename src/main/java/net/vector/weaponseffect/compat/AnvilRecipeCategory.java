package net.vector.weaponseffect.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.vector.weaponseffect.client.ScaledDrawable;
import net.vector.weaponseffect.crafting.AnvilRecipe;
import org.jetbrains.annotations.NotNull;

public class AnvilRecipeCategory implements IRecipeCategory<AnvilRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath("weaponseffect", "anvil");
    public static final RecipeType<AnvilRecipe> ANVIL_TYPE = new RecipeType<>(UID, AnvilRecipe.class);
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("weaponseffect", "textures/gui/anvil_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public AnvilRecipeCategory(IGuiHelper helper) {
        IDrawable baseBackground = helper.createDrawable(TEXTURE, 0, 0, 125, 38);
        this.background = new ScaledDrawable(baseBackground, 1f);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Items.ANVIL));
    }

    @Override
    public @NotNull RecipeType<AnvilRecipe> getRecipeType() {
        return ANVIL_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.literal("Anvil");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, @NotNull AnvilRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addItemStack(new ItemStack(recipe.getLeftItem()));
        builder.addSlot(RecipeIngredientRole.INPUT, 49, 2).addItemStack(new ItemStack(recipe.getRightItem()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 107, 2).addItemStack(recipe.getResult());
    }

    @Override
    public void draw(AnvilRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;

        String itemsNeeded = " " + recipe.getItemsRequired();
        String xpNeeded = "Enchantment Cost: " + recipe.getExperienceRequired();

        int leftSlotX = 4;
        int leftSlotY = 13;


        int rightSlotX = 17;
        int rightSlotY = 28;


        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0, 0, 200);
        guiGraphics.drawString(font, itemsNeeded, leftSlotX, leftSlotY, 0xFFFFFF, true);
        guiGraphics.drawString(font, xpNeeded, rightSlotX, rightSlotY, 5635925, true);
        guiGraphics.pose().popPose();
    }
}