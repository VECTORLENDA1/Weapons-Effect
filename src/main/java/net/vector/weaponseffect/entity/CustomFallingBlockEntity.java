package net.vector.weaponseffect.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CustomFallingBlockEntity extends Entity {
    private BlockState blockState;
    public boolean onGround;

    public CustomFallingBlockEntity(EntityType<? extends CustomFallingBlockEntity> entityType, Level level) {
        super(entityType, level);
    }

    public CustomFallingBlockEntity(Level world, BlockPos pos, BlockState state) {
        this(ModEntities.CUSTOM_FALLING_BLOCK.get(), world);
        this.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        this.blockState = state;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));  // Efeito de gravidade
            this.move(MoverType.SELF, this.getDeltaMovement());

            if (this.onGround) {
                this.level().setBlock(this.blockPosition(), this.blockState, 3);
                this.discard();
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {}

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {}

    public BlockState getBlockState() {
        return this.blockState;
    }
}
