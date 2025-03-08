package net.vector.weaponseffect.crafting;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.vector.weaponseffect.item.ModItems;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModAnvilRecipe {
    public static final Map<String, AnvilRecipe> RECIPES = new HashMap<>();

    public ModAnvilRecipe() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static Collection<AnvilRecipe> getRecipes() {
        return RECIPES.values();
    }

    @SubscribeEvent
    public void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        Player player = event.getPlayer();

        // Verifica se os itens são Netherite Ingot e Obscuridium (em qualquer ordem)
        if ((left.getItem() == Items.NETHERITE_INGOT && right.getItem() == ModItems.OBSCURIDIUM.get()) ||
                (right.getItem() == Items.NETHERITE_INGOT && left.getItem() == ModItems.OBSCURIDIUM.get())) {

            // Determina qual stack é Netherite e qual é Obscuridium
            ItemStack netheriteStack = left.getItem() == Items.NETHERITE_INGOT ? left : right;
            ItemStack obscuridiumStack = left.getItem() == ModItems.OBSCURIDIUM.get() ? left : right;

            // Calcula a quantidade de itens que podem ser criados
            int craftableAmount = Math.min(netheriteStack.getCount(), obscuridiumStack.getCount());

            // Cria o item resultante (Obscurite) com a quantidade apropriada
            ItemStack result = new ItemStack(ModItems.OBSCURITE.get(), craftableAmount);
            event.setOutput(result);

            // Define o custo da bigorna (níveis de experiência)
            event.setCost(15 * craftableAmount);

            // Define o custo da durabilidade na bigorna
            event.setMaterialCost(craftableAmount);
        }
    }

    @SubscribeEvent
    public void onAnvilRepair(AnvilRepairEvent event) {
        ItemStack output = event.getOutput();

        // Verifica se o item de saída é Obscurite
        if (output.getItem() == ModItems.OBSCURITE.get()) {
            int craftedAmount = output.getCount();

            // Obtém os slots do inventário da bigorna
            Player player = event.getEntity();
            Container anvilContainer = player.containerMenu.getSlot(0).container;
            ItemStack leftStack = anvilContainer.getItem(0);
            ItemStack rightStack = anvilContainer.getItem(1);

            // Reduz a quantidade de itens de entrada
            if (leftStack.getItem() == Items.NETHERITE_INGOT) {
                leftStack.shrink(craftedAmount);
                rightStack.shrink(craftedAmount);
            } else {
                rightStack.shrink(craftedAmount);
                leftStack.shrink(craftedAmount);
            }

            // Atualiza os itens na bigorna
            anvilContainer.setItem(0, leftStack);
            anvilContainer.setItem(1, rightStack);
        }
    }
}