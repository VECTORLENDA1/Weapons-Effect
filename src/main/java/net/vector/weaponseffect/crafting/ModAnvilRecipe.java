package net.vector.weaponseffect.crafting;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vector.weaponseffect.block.ModBlocks;
import net.vector.weaponseffect.item.ModItems;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class ModAnvilRecipe {
    public static final Map<String, AnvilRecipe> RECIPES = new HashMap<>();

    public ModAnvilRecipe() {
        MinecraftForge.EVENT_BUS.register(this);
        registerRecipes();
    }

    public static Collection<AnvilRecipe> getRecipes() {
        return RECIPES.values();
    }

    public void registerRecipes() {
        //ORES//
        registerRecipe(Items.NETHERITE_INGOT, ModItems.OBSCURIDIUM.get(), new ItemStack(ModItems.OBSCURITE.get()), 15, 1);



        /*
        //TOOLS//
        registerRecipe(Blocks.STONE.asItem(), ModItems.SIMPLE_BREAKER.get(), new ItemStack(ModItems.MEGA_SIMPLE_BREAKER.get()), 15, 64);

        registerRecipe((ModBlocks.IGNITHRA_BLOCK.get().asItem()), ModItems.SIMPLE_BREAKER.get(), new ItemStack(ModItems.ADVANCE_BREAKER.get()), 50, 15);
        registerRecipe((ModBlocks.IGNITHRA_BLOCK.get().asItem()),ModItems.ADVANCE_BREAKER.get(), new ItemStack(ModItems.MEGA_ADVANCE_BREAKER.get()), 50, 32);
        registerRecipe((ModBlocks.IGNITHRA_BLOCK.get().asItem()), ModItems.MEGA_SIMPLE_BREAKER.get(), new ItemStack(ModItems.MEGA_ADVANCE_BREAKER.get()), 50, 64);

        registerRecipe((ModBlocks.ASTRALITE_BLOCK.get().asItem()), ModItems.ADVANCE_BREAKER.get(), new ItemStack(ModItems.ULTRA_BREAKER.get()), 60, 15);
        registerRecipe((ModBlocks.ASTRALITE_BLOCK.get().asItem()), ModItems.ULTRA_BREAKER.get(), new ItemStack(ModItems.MEGA_ULTRA_BREAKER.get()), 60, 32);
        registerRecipe((ModBlocks.ASTRALITE_BLOCK.get().asItem()), ModItems.MEGA_ADVANCE_BREAKER.get(), new ItemStack(ModItems.MEGA_ULTRA_BREAKER.get()), 60, 64);

        registerRecipe((ModBlocks.NEXALITE_BLOCK.get().asItem()), ModItems.ULTRA_BREAKER.get(), new ItemStack(ModItems.ULTIMATE_BREAKER.get()), 70, 15);
        registerRecipe((ModBlocks.NEXALITE_BLOCK.get().asItem()), ModItems.ULTIMATE_BREAKER.get(), new ItemStack(ModItems.MEGA_ULTIMATE_BREAKER.get()), 70, 32);
        registerRecipe((ModBlocks.NEXALITE_BLOCK.get().asItem()), ModItems.MEGA_ULTRA_BREAKER.get(), new ItemStack(ModItems.MEGA_ULTIMATE_BREAKER.get()), 70, 64);

        registerRecipe((ModBlocks.CELESTINE_BLOCK.get().asItem()), ModItems.ULTIMATE_BREAKER.get(), new ItemStack(ModItems.LEGENDARY_BREAKER.get()), 80, 15);
        registerRecipe((ModBlocks.CELESTINE_BLOCK.get().asItem()), ModItems.LEGENDARY_BREAKER.get(), new ItemStack(ModItems.MEGA_LEGENDARY_BREAKER.get()), 80, 32);
        registerRecipe((ModBlocks.CELESTINE_BLOCK.get().asItem()), ModItems.MEGA_ULTIMATE_BREAKER.get(), new ItemStack(ModItems.MEGA_LEGENDARY_BREAKER.get()), 80, 64);

        registerRecipe((ModBlocks.OBSCURITE_BLOCK.get().asItem()), ModItems.LEGENDARY_BREAKER.get(), new ItemStack(ModItems.ATOMIC_BREAKER.get()), 90, 15);
        registerRecipe((ModBlocks.OBSCURITE_BLOCK.get().asItem()), ModItems.ATOMIC_BREAKER.get(), new ItemStack(ModItems.MEGA_ATOMIC_BREAKER.get()), 90, 32);
        registerRecipe((ModBlocks.OBSCURITE_BLOCK.get().asItem()), ModItems.MEGA_LEGENDARY_BREAKER.get(), new ItemStack(ModItems.MEGA_ATOMIC_BREAKER.get()), 90, 64);

         */
    }

    private void registerRecipe(Item leftItem, Item rightItem, ItemStack result, int cost, int materialCost) {
        String key = getItemKey(leftItem) + "_" + getItemKey(rightItem);
        RECIPES.put(key, new AnvilRecipe(leftItem, rightItem, result, cost, materialCost));
    }

    private String getItemKey(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString();
    }

    @SubscribeEvent
    public void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (left.isEmpty() || right.isEmpty()) {
            return;
        }

        AnvilRecipe recipe = findRecipe(left.getItem(), right.getItem());
        if (recipe != null) {
            if (recipe.getLeftItem() == Items.NETHERITE_INGOT && recipe.getRightItem() == ModItems.OBSCURIDIUM.get()) {
                if (left.getCount() != 1 || right.getCount() != 1) {
                    event.setOutput(ItemStack.EMPTY);
                    return;
                }

                ItemStack result = recipe.getResult().copy();
                event.setOutput(result);
                event.setCost(recipe.getCost());
                event.setMaterialCost(1);
                return;
            }

            boolean isLeftMaterial = left.getItem() == recipe.getLeftItem();
            ItemStack materialStack = isLeftMaterial ? left : right;
            if (materialStack.getCount() != recipe.getMaterialCost()) {
                event.setOutput(ItemStack.EMPTY);
                return;
            }
            event.setOutput(recipe.getResult().copy());
            event.setCost(recipe.getCost());
            event.setMaterialCost(recipe.getMaterialCost());
        } else {
            event.setOutput(ItemStack.EMPTY);
        }
    }

    @SubscribeEvent
    public void onAnvilRepair(AnvilRepairEvent event) {
        ItemStack output = event.getOutput();
        Player player = event.getEntity();
        Container anvilContainer = player.containerMenu.getSlot(0).container;

        ItemStack leftStack = anvilContainer.getItem(0);
        ItemStack rightStack = anvilContainer.getItem(1);

        AnvilRecipe recipe = findRecipe(leftStack.getItem(), rightStack.getItem());
        if (recipe != null && !leftStack.isEmpty() && !rightStack.isEmpty()) {

            boolean isLeftMaterial = leftStack.getItem() == recipe.getLeftItem();
            boolean isRightMaterial = rightStack.getItem() == recipe.getRightItem();

            ItemStack materialStack = isLeftMaterial ? leftStack : rightStack;
            ItemStack toolStack = isLeftMaterial ? rightStack : leftStack;

            if (materialStack.getCount() != recipe.getMaterialCost()) {
                event.setCanceled(true);
                return;
            }

            toolStack.shrink(1);
            materialStack.shrink(recipe.getMaterialCost());

            anvilContainer.setItem(0, leftStack);
            anvilContainer.setItem(1, rightStack);
        }
    }

    private AnvilRecipe findRecipe(Item leftItem, Item rightItem) {
        String key1 = getItemKey(leftItem) + "_" + getItemKey(rightItem);
        String key2 = getItemKey(rightItem) + "_" + getItemKey(leftItem);

        return RECIPES.getOrDefault(key1, RECIPES.get(key2));
    }
}