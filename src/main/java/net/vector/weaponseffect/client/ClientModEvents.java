package net.vector.weaponseffect.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.GustParticle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vector.weaponseffect.WeaponsEffect;
import net.vector.weaponseffect.client.renderer.entity.ThrownLanceRenderer;
import net.vector.weaponseffect.entity.ModEntities;
import net.vector.weaponseffect.registry.ModParticles;

@Mod.EventBusSubscriber(modid = WeaponsEffect.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.THROWN_LANCE.get(), ThrownLanceRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.GUST.get(), GustParticle.Provider::new);
    }
}
