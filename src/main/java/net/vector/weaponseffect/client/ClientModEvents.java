package net.vector.weaponseffect.client;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.particle.GustParticle;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vector.weaponseffect.WeaponsEffect;
import net.vector.weaponseffect.client.renderer.BlackHoleRenderer;
import net.vector.weaponseffect.client.renderer.entity.ThrownLanceRenderer;
import net.vector.weaponseffect.entity.ModEntities;
import net.vector.weaponseffect.registry.ModParticles;

import java.util.Collections;

@Mod.EventBusSubscriber(modid = WeaponsEffect.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.THROWN_LANCE.get(), ThrownLanceRenderer::new);
        EntityRenderers.register(ModEntities.BLACK_HOLE.get(), BlackHoleRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.GUST.get(), GustParticle.Provider::new);
    }


}
