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
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.vector.weaponseffect.block.ModBlocks;
import net.vector.weaponseffect.block.entity.ModBlockEntities;
import net.vector.weaponseffect.item.ModItems;
import net.vector.weaponseffect.recipe.ModRecipes;
import net.vector.weaponseffect.recipe.SimpleCraftingTableRecipe;
import net.vector.weaponseffect.recipe.SimpleCraftingTableRecipeInput;
import net.vector.weaponseffect.screen.custom.SimpleCraftingTableMenu;

import javax.annotation.Nullable;
import java.util.Optional;

public class SimpleCraftingTableEntity extends BlockEntity implements MenuProvider {
    private RecipeHolder<SimpleCraftingTableRecipe> cachedRecipe = null;

    public final ItemStackHandler itemHandler = new ItemStackHandler(26) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);

                // Se for um slot de entrada, atualiza o resultado
                if (slot < OUTPUT_SLOT) {
                    updateResult();
                }
            }
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (slot == OUTPUT_SLOT) {
                ItemStack currentOutput = getStackInSlot(OUTPUT_SLOT);
                if (!currentOutput.isEmpty()) {
                    if (!simulate) {
                        // Só consome os ingredientes se realmente extraindo (não simulando)
                        consumeIngredients();
                        // Atualiza o resultado após consumir
                        updateResult();
                    }
                    return super.extractItem(slot, amount, simulate);
                }
            }
            return super.extractItem(slot, amount, simulate);
        }
    };


    private void updateResult() {
        Optional<RecipeHolder<SimpleCraftingTableRecipe>> recipe = getCurrentRecipe();
        if (recipe.isPresent() && canOutput(recipe.get().value().getResultItem(level.registryAccess()))) {
            cachedRecipe = recipe.get();
            ItemStack result = recipe.get().value().getResultItem(level.registryAccess()).copy();
            itemHandler.setStackInSlot(OUTPUT_SLOT, result);
        } else {
            cachedRecipe = null;
            itemHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
        }
    }

    private static final int[] INPUT_SLOT = new int[25];
    private static final int OUTPUT_SLOT = 25;

    private RecipeHolder<SimpleCraftingTableRecipe> currentRecipe = null;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public SimpleCraftingTableEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SIMPLE_CRAFTING_TABLE_BE.get(), pPos, pBlockState);
        for (int i = 0; i < 25; i++) {
            INPUT_SLOT[i] = i;
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

    //this are for saving and loading the inventory of the block in case you exit the world
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
        if (level.isClientSide()) return;

        // Se o slot de saída estiver vazio, atualiza o resultado
        if (itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            updateResult();
        }
    }

    private void consumeIngredients() {
        if (cachedRecipe != null) {
            for (int i = 0; i < INPUT_SLOT.length; i++) {
                itemHandler.extractItem(i, 1, false);
            }
        }
    }

    private boolean canOutput(ItemStack result) {
        ItemStack currentOutput = itemHandler.getStackInSlot(OUTPUT_SLOT);
        if (currentOutput.isEmpty()) return true;
        if (!ItemStack.isSameItem(currentOutput, result)) return false;
        return currentOutput.getCount() + result.getCount() <= currentOutput.getMaxStackSize();
    }

    private Optional<RecipeHolder<SimpleCraftingTableRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.SIMPLE_CRAFTING_TABLE_TYPE.get(),
                        new SimpleCraftingTableRecipeInput(itemHandler), level);
    }

    //will drop everything in it when you break the block
    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.weaponseffect.simple_crafting_table");
    }

    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SimpleCraftingTableMenu(pContainerId, pPlayerInventory, this, null);
    }

    //this 2 functions are for syncing the inventory from the server to the client and vice versa
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
