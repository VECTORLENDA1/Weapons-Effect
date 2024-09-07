package net.vector.weaponseffect.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class WingsOfDoomItem extends SwordItem {
    public WingsOfDoomItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }



    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);

        target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 3), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 3), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 3), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 3), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 4), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 2), attacker);


        if (result && !target.level().isClientSide() && !target.fireImmune()) {
            target.igniteForSeconds(20);
        }else {
            for (int var1 = 0; var1 < 20; ++var1) {
                double px = target.getX() + target.level().getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
                double py = target.getY() + target.level().getRandom().nextFloat() * target.getBbHeight();
                double pz = target.getZ() + target.level().getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
                target.level().addParticle(ParticleTypes.FLAME, px, py, pz, 0.02, 0.02, 0.02);
            }
        }
        return result;
    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("wings.of.doom.tooltip").withStyle(ChatFormatting.WHITE));
        pTooltipComponents.add(Component.translatable("wings.of.doom.tooltip.2").withStyle(ChatFormatting.WHITE));
        pTooltipComponents.add(Component.translatable("wings.of.doom.tooltip.3").withStyle(ChatFormatting.WHITE));
        pTooltipComponents.add(Component.translatable("wings.of.doom.line.2"));
        pTooltipComponents.add(Component.translatable("wings.of.doom.immunities").withStyle(ChatFormatting.WHITE));
        pTooltipComponents.add(Component.translatable("wings.of.doom.immunities.tooltip").withStyle(ChatFormatting.DARK_GRAY));
        pTooltipComponents.add(Component.translatable("wings.of.doom.immunities.tooltip.2").withStyle(ChatFormatting.DARK_GRAY));
        pTooltipComponents.add(Component.translatable("wings.of.doom.immunities.tooltip.3").withStyle(ChatFormatting.GREEN));
        pTooltipComponents.add(Component.translatable("wings.of.doom.immunities.tooltip.4").withStyle(ChatFormatting.WHITE));
        pTooltipComponents.add(Component.translatable("wings.of.doom.immunities.tooltip.5").withStyle(ChatFormatting.WHITE));
        pTooltipComponents.add(Component.translatable("wings.of.doom.immunities.tooltip.6").withStyle(ChatFormatting.DARK_GRAY));
        pTooltipComponents.add(Component.translatable("wings.of.doom.immunities.tooltip.7").withStyle(ChatFormatting.RED));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
