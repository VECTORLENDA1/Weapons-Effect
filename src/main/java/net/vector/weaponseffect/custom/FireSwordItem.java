package net.vector.weaponseffect.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;


public class FireSwordItem extends SwordItem {
    public FireSwordItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    /*@Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffects.LEVITATION,300), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }*/


}
