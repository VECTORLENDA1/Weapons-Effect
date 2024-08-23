package net.vector.weaponseffect.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vector.weaponseffect.custom.DarknessMaceItem;
import net.vector.weaponseffect.custom.FireSwordItem;
import net.vector.weaponseffect.custom.GimlisAxeItem;
import net.vector.weaponseffect.custom.LanceItem;


@Mod.EventBusSubscriber(modid = "weaponseffect", bus =
        Mod.EventBusSubscriber.Bus.FORGE)
public class ItemEffectEventHandler {


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();


            if (mainHandItem.getItem() instanceof FireSwordItem) {

                if (!player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 12000, 0, false, false, true));
                }
            } else {

                player.removeEffect(MobEffects.FIRE_RESISTANCE);
            }


                if (mainHandItem.getItem() instanceof LanceItem) {

                    if (!player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 12000, 0, false, false, true));
                    }
                } else {

                    player.removeEffect(MobEffects.FIRE_RESISTANCE);

            }

            if (mainHandItem.getItem() instanceof DarknessMaceItem) {
                DarknessMaceItem DarknessMace = (DarknessMaceItem) mainHandItem.getItem();
                DarknessMace.hurtEnemy(mainHandItem, player, player);

                if (mainHandItem.getItem() instanceof DarknessMaceItem) {

                    if (!player.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 12000, 0, false, false, true));
                    }
                } else {

                    player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
                }
            }

                if (mainHandItem.getItem() instanceof GimlisAxeItem) {

                    if (!player.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 12000, 0, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 12000, 1, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 12000, 0, false, false, true));
                    }
                } else {

                    player.removeEffect(MobEffects.DAMAGE_BOOST);
                    player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
                    player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
            }

        }
    }
}