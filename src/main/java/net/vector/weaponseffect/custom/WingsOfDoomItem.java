package net.vector.weaponseffect.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class WingsOfDoomItem extends SwordItem {
    public WingsOfDoomItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }



    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);

        target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 1), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 1), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 1), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1), attacker);


        if (result && !target.level().isClientSide() && !target.fireImmune()) {
            target.igniteForSeconds(10);
        } else {
            for (int var1 = 0; var1 < 20; ++var1) {
                double px = target.getX() + target.level().getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
                double py = target.getY() + target.level().getRandom().nextFloat() * target.getBbHeight();
                double pz = target.getZ() + target.level().getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
                target.level().addParticle(ParticleTypes.FLAME, px, py, pz, 0.02, 0.02, 0.02);
            }
        }
        return result;
    }
}
