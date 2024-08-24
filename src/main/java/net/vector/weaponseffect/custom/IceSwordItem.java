package net.vector.weaponseffect.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class IceSwordItem extends SwordItem {
    public IceSwordItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 4), pAttacker);
        pTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
