package net.vector.weaponseffect.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vector.weaponseffect.custom.FireSwordItem;

@Mod.EventBusSubscriber(modid = "weaponseffect", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ItemEffectEventHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();

            // Verifique se o item na mão principal é a FireSwordItem ou o item desejado
            if (mainHandItem.getItem() instanceof FireSwordItem) {
                // Adicione o efeito de força
                if (!player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 12000, 1, false, false, true));
                }
            } else {
                // Remova o efeito se o item não estiver mais sendo segurado
                player.removeEffect(MobEffects.DAMAGE_BOOST);
            }
        }
    }
}
