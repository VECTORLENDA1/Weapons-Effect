package net.vector.weaponseffect.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.UUID;

public class BlackHoleEntity extends Entity {
    public static final int DEFAULT_LIFETIME = 2000;
    public static final float DEFAULT_MAX_RADIUS = 10.0F;
    private static final float GROWTH_RATE = 0.05F;

    private int lifetime;
    private float currentRadius;
    private float maxRadius;
    private UUID ownerUUID;

    public BlackHoleEntity(EntityType<? extends BlackHoleEntity> entityType, Level level) {
        super(entityType, level);
    }

    public BlackHoleEntity(Level world, double x, double y, double z, int lifetime, float maxRadius, UUID ownerUUID) {
        this(ModEntities.BLACK_HOLE.get(), world);
        this.setPos(x, y, z);
        this.lifetime = lifetime;
        this.maxRadius = maxRadius;
        this.currentRadius = 2.0F;
        this.ownerUUID = ownerUUID;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            return;
        }

        if (this.lifetime-- <= 0) {
            this.discard();
            return;
        }

        if (this.currentRadius < this.maxRadius) {
            this.currentRadius = Math.min(this.currentRadius + GROWTH_RATE, this.maxRadius);
        }

        this.suckBlocksAndEntities();
    }

    private void suckBlocksAndEntities() {
        int range = (int) Math.ceil(this.currentRadius);
        BlockPos centerPos = this.blockPosition();

        for (BlockPos pos : BlockPos.betweenClosed(centerPos.offset(-range, -range, -range), centerPos.offset(range, range, range))) {
            double distance = Math.sqrt(pos.distSqr(centerPos));
            if (distance <= this.currentRadius) {
                BlockState state = this.level().getBlockState(pos);
                if (!state.isAir() && state.getDestroySpeed(this.level(), pos) >= 0) {
                    this.level().destroyBlock(pos, false);
                }
            }
        }

        List<Entity> entities = this.level().getEntities(this, this.getBoundingBox().inflate(range),
                entity -> !(entity instanceof BlackHoleEntity) && !entity.getUUID().equals(this.ownerUUID));
        for (Entity entity : entities) {
            Vec3 directionToBlackHole = this.position().subtract(entity.position()).normalize();
            double distance = entity.distanceTo(this);
            double strength = 1.0 - (distance / this.currentRadius);
            if (strength > 0) {
                entity.setDeltaMovement(entity.getDeltaMovement().add(directionToBlackHole.scale(strength * 0.1)));
                if (distance < 1.0) {
                    if (entity instanceof LivingEntity) {
                        entity.hurt(this.level().damageSources().magic(), 20.0F);
                    } else {
                        entity.discard();
                    }
                }
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {}

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {}

    public Level getLevel() {
        return this.level();
    }
}
