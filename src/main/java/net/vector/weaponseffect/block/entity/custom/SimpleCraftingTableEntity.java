package net.vector.weaponseffect.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.vector.weaponseffect.block.entity.ModBlockEntities;
import net.vector.weaponseffect.recipe.ModRecipes;
import net.vector.weaponseffect.recipe.SimpleCraftingTableRecipe;
import net.vector.weaponseffect.recipe.SimpleCraftingTableRecipeInput;
import net.vector.weaponseffect.screen.custom.SimpleCraftingTableMenu;

import javax.annotation.Nullable;
import java.util.Optional;

public class SimpleCraftingTableEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(26) {
        @Override
        protected void onContentsChanged(int slot) {
            if (slot == OUTPUT_SLOT) return;
            setChanged();
            if (!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                updateResult(slot);
            }
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (slot == OUTPUT_SLOT) {
                Optional<RecipeHolder<SimpleCraftingTableRecipe>> opt = getCurrentRecipe();
                if (opt.isEmpty()) {
                    return ItemStack.EMPTY;
                }

                SimpleCraftingTableRecipe recipe = opt.get().value();
                ItemStack currentOutput = super.getStackInSlot(slot);

                if (currentOutput.isEmpty()) {
                    return ItemStack.EMPTY;
                }

                // Calcula quantos crafts são possíveis com os ingredientes disponíveis
                int maxPossibleCrafts = Math.min(amount, currentOutput.getCount());
                int availableCrafts = Integer.MAX_VALUE;

                for (int i = 0; i < INPUT_SLOT.length; i++) {
                    int required = recipe.getRequiredCountForSlot(i);
                    if (required > 0) {
                        ItemStack ingredient = getStackInSlot(i);
                        availableCrafts = Math.min(availableCrafts, ingredient.getCount() / required);
                    }
                }

                int craftsToDo = Math.min(maxPossibleCrafts, availableCrafts);
                if (craftsToDo <= 0) {
                    return ItemStack.EMPTY;
                }

                ItemStack toExtract = currentOutput.copy();
                toExtract.setCount(craftsToDo);

                if (!simulate) {
                    // Consome os ingredientes
                    consumeIngredients(craftsToDo);
                    // Remove do output
                    super.extractItem(slot, craftsToDo, false);
                    // Atualiza o resultado
                    updateResult(slot);
                }

                return toExtract;
            }
            return super.extractItem(slot, amount, simulate);
        }

    };

    private static final int[] INPUT_SLOT = new int[25];
    private static final int OUTPUT_SLOT = 25;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public SimpleCraftingTableEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SIMPLE_CRAFTING_TABLE_BE.get(), pPos, pBlockState);
        for (int i = 0; i < 25; i++) {
            INPUT_SLOT[i] = i;
        }
    }

    private void updateResult(int slotChanged) {
        if (slotChanged == OUTPUT_SLOT) return;
        Optional<RecipeHolder<SimpleCraftingTableRecipe>> opt = getCurrentRecipe();
        if (opt.isEmpty()) {
            itemHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
            return;
        }
        SimpleCraftingTableRecipe recipe = opt.get().value();
        // calcula máximo de crafts possíveis
        int maxCrafts = Integer.MAX_VALUE;
        for (int i = 0; i < INPUT_SLOT.length; i++) {
            int req = recipe.getRequiredCountForSlot(i);
            if (req > 0) {
                ItemStack in = itemHandler.getStackInSlot(i);
                maxCrafts = Math.min(maxCrafts, in.getCount() / req);
            }
        }
        if (maxCrafts <= 0) {
            itemHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
        } else {
            ItemStack result = recipe.getResultItem(level.registryAccess()).copy();
            result.setCount(result.getCount() * maxCrafts);
            itemHandler.setStackInSlot(OUTPUT_SLOT, result);
        }
    }

    public void consumeIngredients(int times) {
        Optional<RecipeHolder<SimpleCraftingTableRecipe>> opt = getCurrentRecipe();
        if (opt.isEmpty()) return;
        SimpleCraftingTableRecipe recipe = opt.get().value();
        for (int i = 0; i < INPUT_SLOT.length; i++) {
            int req = recipe.getRequiredCountForSlot(i);
            if (req > 0) {
                itemHandler.extractItem(i, req * times, false);
            }
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (level.isClientSide) return;

        // Verifica se os slots de entrada estão vazios
        boolean isEmpty = true;
        for (int i = 0; i < INPUT_SLOT.length; i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty()) {
                isEmpty = false;
                break;
            }
        }

        // Se estiver vazio, limpa o output
        if (isEmpty) {
            itemHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
        }
    }

    private Optional<RecipeHolder<SimpleCraftingTableRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.SIMPLE_CRAFTING_TABLE_TYPE.get(),
                        new SimpleCraftingTableRecipeInput(itemHandler), level);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.weaponseffect.simple_crafting_table");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new SimpleCraftingTableMenu(id, playerInv, this, null);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}
