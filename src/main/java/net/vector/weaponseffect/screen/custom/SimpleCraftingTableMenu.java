package net.vector.weaponseffect.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import net.vector.weaponseffect.block.ModBlocks;
import net.vector.weaponseffect.block.entity.ModBlockEntities;
import net.vector.weaponseffect.block.entity.custom.SimpleCraftingTableEntity;
import net.vector.weaponseffect.screen.ModMenuTypes;
import org.jetbrains.annotations.Nullable;

public class SimpleCraftingTableMenu extends AbstractContainerMenu {
    public final SimpleCraftingTableEntity blockEntity;
    private final Level level;

    public SimpleCraftingTableMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public SimpleCraftingTableMenu(int pContainerId, Inventory inv, BlockEntity blockEntity) {
        super(ModMenuTypes.SIMPLE_CRAFTING_TABLE_MENU.get(), pContainerId);
        this.blockEntity = ((SimpleCraftingTableEntity) blockEntity);
        this.level = inv.player.level();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 18, 18));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 36, 18));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 54, 18));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 72, 18));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 90, 18));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 18, 36));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 36, 36));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 54, 36));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 72, 36));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 90, 36));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 18, 54));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 36, 54));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 54, 54));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 72, 54));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 90, 54));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 18, 72));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 36, 72));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 54, 72));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 72, 72));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 90, 72));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 18, 90));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 36, 90));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 54, 90));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 72, 90));
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 90, 90));

    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 25;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.SIMPLE_CRAFTING_TABLE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 124 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 182));
        }
    }
}
