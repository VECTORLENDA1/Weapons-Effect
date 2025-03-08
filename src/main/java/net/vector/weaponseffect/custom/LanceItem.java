package net.vector.weaponseffect.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.vector.weaponseffect.entity.projectile.ThrownLance;

import java.util.List;

public class LanceItem extends SwordItem {
    public LanceItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200,3), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("lance.tooltip").withStyle(ChatFormatting.WHITE));
        pTooltipComponents.add(Component.translatable("lance.line.2"));
        pTooltipComponents.add(Component.translatable("lance.immunities").withStyle(ChatFormatting.WHITE));
        pTooltipComponents.add(Component.translatable("lance.immunities.tooltip").withStyle(ChatFormatting.WHITE));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);

        if (!pLevel.isClientSide) {
            ThrownLance lance = new ThrownLance(pLevel, pPlayer, itemStack);
            lance.setOwner(pPlayer);
            lance.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 4.0F, 1.0F);
            pLevel.addFreshEntity(lance);
        }

        if (!pPlayer.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
    }
}
