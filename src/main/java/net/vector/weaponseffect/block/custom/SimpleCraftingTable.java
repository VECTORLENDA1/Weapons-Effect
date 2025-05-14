package net.vector.weaponseffect.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.vector.weaponseffect.block.entity.custom.SimpleCraftingTableEntity;
import org.jetbrains.annotations.Nullable;

public class SimpleCraftingTable extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0.0f, 0.0f, 0.0f, 16.0f, 16.0f, 16.0f);
    public static final MapCodec<SimpleCraftingTable> CODEC = simpleCodec(SimpleCraftingTable::new);


    public SimpleCraftingTable(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    //important if you don't have this the block will be invisible//
    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }



    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SimpleCraftingTableEntity(pPos, pState);
    }

    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos,
                            BlockState pNewState, boolean pMoveByPiston) {
        if (pState.getBlock() != pNewState.getBlock()) {
            if (pLevel.getBlockEntity(pPos) instanceof SimpleCraftingTableEntity simpleCraftingTableEntity) {
               simpleCraftingTableEntity.drops();
               pLevel.updateNeighbourForOutputSignal(pPos , this);
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pMoveByPiston);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel,
                                              BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (pLevel.getBlockEntity(pPos) instanceof SimpleCraftingTableEntity simpleCraftingTableEntity) {
            if (pPlayer.isCrouching() && !pLevel.isClientSide()) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(simpleCraftingTableEntity, Component.literal("Simple Crafting Table")), pPos);
                return ItemInteractionResult.SUCCESS;
            }

            if (pPlayer.isCrouching() && pLevel.isClientSide()) {
                return ItemInteractionResult.SUCCESS;
            }
        }

        if (pLevel.getBlockEntity(pPos) instanceof SimpleCraftingTableEntity simpleCraftingTableEntity) {
            if (simpleCraftingTableEntity.inventory.getStackInSlot(0).isEmpty() && !pStack.isEmpty()) {
                simpleCraftingTableEntity.inventory.insertItem(0,pStack.copy(), false);
                pStack.shrink(1);
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1F, 2F);
            } else if (pStack.isEmpty()) {
                ItemStack stackOnTable = simpleCraftingTableEntity.inventory.extractItem(0, 1,false);
                pPlayer.setItemInHand(InteractionHand.MAIN_HAND, stackOnTable);
                simpleCraftingTableEntity.clearContents();
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            }
        }

        return ItemInteractionResult.SUCCESS;
    }

}
