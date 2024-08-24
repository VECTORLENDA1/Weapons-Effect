package net.vector.weaponseffect.event;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vector.weaponseffect.custom.*;

@Mod.EventBusSubscriber(modid = "weaponseffect", bus =
        Mod.EventBusSubscriber.Bus.FORGE)
public class EventEffectItems {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();

            boolean hasBlindnessDaggerItem = mainHandItem.getItem() instanceof BlindnessDaggerItem;
            boolean hasDarknessMaceItem = mainHandItem.getItem() instanceof DarknessMaceItem;
            boolean hasFireSwordItem = mainHandItem.getItem() instanceof FireSwordItem;
            boolean hasGimlisAxeItem = mainHandItem.getItem() instanceof GimlisAxeItem;
            boolean hasIceSwordItem = mainHandItem.getItem() instanceof IceSwordItem;
            boolean hasLanceItem = mainHandItem.getItem() instanceof LanceItem;
            boolean hasPoisonSwordItem = mainHandItem.getItem() instanceof PoisonSwordItem;
            boolean hasStrengthHammerItem = mainHandItem.getItem() instanceof StrengthsHammerItem;
            boolean hasSwiftnessDaggerItem = mainHandItem.getItem() instanceof SwiftnessDaggerItem;
            boolean hasWingsofDoomItem = mainHandItem.getItem() instanceof WingsOfDoomItem;
            boolean hasWitherSwordItem = mainHandItem.getItem() instanceof WitherSwordItem;



            //Fire Resistance//
            if (hasFireSwordItem || hasWingsofDoomItem) {
                int fireAmplifier = 0;
                if (hasWingsofDoomItem) {
                    fireAmplifier = 0;
                } else if (hasFireSwordItem) {
                    fireAmplifier = 0;
                }
                if (!player.hasEffect(MobEffects.FIRE_RESISTANCE) || player.getEffect(MobEffects.FIRE_RESISTANCE).getAmplifier() != fireAmplifier) {
                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 12000, fireAmplifier, false, false, true));
                }
            } else {
                player.removeEffect(MobEffects.FIRE_RESISTANCE);
            }

            //Damage Boost//
            if (hasGimlisAxeItem || hasWingsofDoomItem || hasStrengthHammerItem || hasDarknessMaceItem || hasLanceItem) {
                int strengthAmplifier = 0;
                if (hasWingsofDoomItem) {
                    strengthAmplifier = 1;
                } else if (hasGimlisAxeItem) {
                    strengthAmplifier = 0;
                } else if (hasDarknessMaceItem) {
                    strengthAmplifier = 0;
                }else if (hasStrengthHammerItem) {
                    strengthAmplifier = 3;
                }else if (hasLanceItem) {
                    strengthAmplifier = 0;
                }
                if (!player.hasEffect(MobEffects.DAMAGE_BOOST) || player.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() != strengthAmplifier) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 12000, strengthAmplifier, false, false, true));
                }
            } else {
                player.removeEffect(MobEffects.DAMAGE_BOOST);
            }

            //Movement Speed//
            if (hasLanceItem || hasWingsofDoomItem || hasSwiftnessDaggerItem) {
                int speedAmplifier = 0;

                if (hasWingsofDoomItem) {
                    speedAmplifier = 1;
                } else if (hasLanceItem) {
                    speedAmplifier = 0;
                }else if (hasSwiftnessDaggerItem) {
                    speedAmplifier = 2;
                }

                if (!player.hasEffect(MobEffects.MOVEMENT_SPEED) || player.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier() != speedAmplifier) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 12000, speedAmplifier, false, false, true));
                }
            } else {
                player.removeEffect(MobEffects.MOVEMENT_SPEED);
            }

            //Blindness//
            if (hasBlindnessDaggerItem) {
                int blindAmplifier = 0;
                if (hasBlindnessDaggerItem) {
                    blindAmplifier = 1;
                }
                if (!player.hasEffect(MobEffects.BLINDNESS) || player.getEffect(MobEffects.BLINDNESS).getAmplifier() != blindAmplifier) {
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 12000, blindAmplifier, false, false, true));
                }
            } else {
                player.removeEffect(MobEffects.BLINDNESS);
            }

            //Darkness//
            if (hasDarknessMaceItem) {
                int darkAmplifier = 0;
                if (hasDarknessMaceItem) {
                    darkAmplifier = 1;
                }
                if (!player.hasEffect(MobEffects.DARKNESS) || player.getEffect(MobEffects.DARKNESS).getAmplifier() != darkAmplifier) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 12000, darkAmplifier, false, false, true));
                }
            } else {
                player.removeEffect(MobEffects.DARKNESS);
            }


            //DAMAGE_RESISTANCE//
            if (hasGimlisAxeItem || hasWingsofDoomItem) {
                int resAmplifier = 0;
                if (hasGimlisAxeItem) {
                    resAmplifier = 1;
                }else if (hasWingsofDoomItem) {
                    resAmplifier = 0;
                }
                if (!player.hasEffect(MobEffects.DAMAGE_RESISTANCE) || player.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() != resAmplifier) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 12000, resAmplifier, false, false, true));
                }
            } else {
                player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
            }


            //SATURATION//
            if (hasWingsofDoomItem) {
                int satAmplifier = 0;
                if (hasWingsofDoomItem) {
                    satAmplifier = 0;
                }
                if (!player.hasEffect(MobEffects.SATURATION) || player.getEffect(MobEffects.SATURATION).getAmplifier() != satAmplifier) {
                    player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 12000, satAmplifier, false, false, true));
                }
            } else {
                player.removeEffect(MobEffects.SATURATION);
            }


            //NIGHT_VISION//
            if (hasWingsofDoomItem) {
                int nightAmplifier = 0;
                if (hasWingsofDoomItem) {
                    nightAmplifier = 0;
                }
                if (!player.hasEffect(MobEffects.NIGHT_VISION) || player.getEffect(MobEffects.NIGHT_VISION).getAmplifier() != nightAmplifier) {
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 12000, nightAmplifier, false, false, true));
                }
            } else {
                player.removeEffect(MobEffects.NIGHT_VISION);
            }










            //Immunities//

            //Blindness//
            if (hasBlindnessDaggerItem || hasWingsofDoomItem) {
                if (player.hasEffect(MobEffects.BLINDNESS)) {
                    player.removeEffect(MobEffects.BLINDNESS);
                }
            }

            //Darkness//
            if (hasDarknessMaceItem || hasWingsofDoomItem) {
                if (player.hasEffect(MobEffects.DARKNESS)) {
                    player.removeEffect(MobEffects.DARKNESS);
                }
            }

            //Poison//
            if (hasPoisonSwordItem || hasWingsofDoomItem) {
                if (player.hasEffect(MobEffects.POISON)) {
                    player.removeEffect(MobEffects.POISON);
                }
            }

            //Movement Slowdown//
            if (hasLanceItem || hasWingsofDoomItem || hasSwiftnessDaggerItem || hasIceSwordItem) {
                if (player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                    player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                }
            }

            //Weakness//
            if (hasWingsofDoomItem) {
                if (player.hasEffect(MobEffects.WEAKNESS)) {
                    player.removeEffect(MobEffects.WEAKNESS);
                }
            }

            //Wither//
            if (hasWitherSwordItem || hasWingsofDoomItem || hasIceSwordItem) {
                if (player.hasEffect(MobEffects.WITHER)) {
                    player.removeEffect(MobEffects.WITHER);
                }
            }
        }
    }
}
