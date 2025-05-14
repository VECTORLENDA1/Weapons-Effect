package net.vector.weaponseffect;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.vector.weaponseffect.block.ModBlocks;
import net.vector.weaponseffect.block.entity.ModBlockEntities;
import net.vector.weaponseffect.block.entity.renderer.SimpleCraftingTableEntityRenderer;
import net.vector.weaponseffect.client.renderer.BlackHoleRenderer;
import net.vector.weaponseffect.effect.ModEffect;
import net.vector.weaponseffect.entity.ModEntities;
import net.vector.weaponseffect.item.ModCreativeModTabs;
import net.vector.weaponseffect.item.ModItems;
import net.vector.weaponseffect.registry.ModParticles;
import net.vector.weaponseffect.screen.ModMenuTypes;
import net.vector.weaponseffect.screen.custom.SimpleCraftingTableScreen;
import org.slf4j.Logger;

@Mod(WeaponsEffect.MODID)
public class WeaponsEffect {
    public static final String MODID = "weaponseffect";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WeaponsEffect() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        ModItems.Register(modEventBus);
        ModBlocks.Register(modEventBus);
        ModEffect.register(modEventBus);
        ModEntities.register(modEventBus);
        ModParticles.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.BLACK_HOLE.get(), BlackHoleRenderer::new);

            MenuScreens.register(ModMenuTypes.SIMPLE_CRAFTING_TABLE_MENU.get(), SimpleCraftingTableScreen::new);

        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.CRAFTING_BE.get(), SimpleCraftingTableEntityRenderer::new);
        }


    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    }

}
