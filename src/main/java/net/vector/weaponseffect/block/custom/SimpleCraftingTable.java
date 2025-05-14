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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.vector.weaponseffect.block.entity.ModBlockEntities;
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

    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (pLevel.getBlockEntity(pPos) instanceof SimpleCraftingTableEntity simpleCraftingTableEntity) {
                simpleCraftingTableEntity.drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }


    //This will open the Menu of the block (Simple Crafting Table)
    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos,
                                              Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof SimpleCraftingTableEntity simpleCraftingTableEntity) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(simpleCraftingTableEntity, Component.literal("Simple Crafting Table")), pPos);
            } else {
                throw new IllegalStateException("Our container provider is missing!");
            }
        }

        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()) {
            return null;
        }


        return createTickerHelper(pBlockEntityType, ModBlockEntities.SIMPLE_CRAFTING_TABLE_BE.get(),
                (level, blockPos, blockState, simpleCraftingTableEntity)
                        -> simpleCraftingTableEntity.tick(level, blockPos, blockState));
    }
}

