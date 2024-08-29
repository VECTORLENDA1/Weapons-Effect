package net.vector.weaponseffect.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class IceSwordItem extends SwordItem {
    public IceSwordItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }


    //I want to get the effect of Powder Snow on this sword (still working on it)
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 10), pAttacker);
        pTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("ice.sword.tooltip").withStyle(ChatFormatting.WHITE));
        pTooltipComponents.add(Component.translatable("ice.line.2"));
        pTooltipComponents.add(Component.translatable("ice.immunities").withStyle(ChatFormatting.WHITE));
        pTooltipComponents.add(Component.translatable("ice.immunities.tooltip").withStyle(ChatFormatting.BLUE));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
