package net.vector.weaponseffect;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
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
import net.vector.weaponseffect.effect.ModEffect;
import net.vector.weaponseffect.enchantment.ModEnchantments;
import net.vector.weaponseffect.item.ModCreativeModTabs;
import net.vector.weaponseffect.item.ModItems;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(WeaponsEffect.MOD_ID)
public class WeaponsEffect {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "weaponseffect";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public WeaponsEffect() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


        ModItems.Register(modEventBus);
        ModBlocks.Register(modEventBus);
        ModEffect.register(modEventBus);
        ModEnchantments.Register(modEventBus);
        ModCreativeModTabs.register(modEventBus);











        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }



    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                Minecraft.getInstance().getItemColors().register((stack, tintIndex) -> {
                    if (tintIndex == 1) {
                    }
                    return -1;
                }, ModItems.CELESTINE.get(),
                        ModItems.ZENITHRA.get(),
                        ModItems.ASTRALITE.get(),
                        ModItems.RAW_ASTRALITE.get(),
                        ModItems.RAW_NEXALITE.get(),
                        ModItems.NEXALITE.get(),
                        ModItems.IGNITHRA.get(),
                        ModItems.RAW_IGNITHRA.get(),
                        ModItems.ANTRACITE.get(),
                        ModItems.FIRE_SWORD.get(),
                        ModItems.WITHER_SWORD.get(),
                        ModItems.BLINDNESS_DAGGER.get(),
                        ModItems.DARKNESS_MACE.get(),
                        ModItems.GIMLIS_AXE.get(),
                        ModItems.ICE_SWORD.get(),
                        ModItems.LANCE.get(),
                        ModItems.POISON_SWORD.get(),
                        ModItems.STRENGTHS_HAMMER.get(),
                        ModItems.WINGS_OF_DOOM.get(),
                        ModItems.SWIFTNESS_DAGGER.get(),


                        ModBlocks.END_STONE_CELESTINE_ORE.get(),
                        ModBlocks.NEXALITE_BLOCK.get(),
                        ModBlocks.IGNITHRA_BLOCK.get(),
                        ModBlocks.DEEPSLATE_IGNITHRA_ORE.get(),
                        ModBlocks.IGNITHRA_ORE.get(),
                        ModBlocks.RAW_IGNITHRA_BLOCK.get(),
                        ModBlocks.ASTRALITE_BLOCK.get(),
                        ModBlocks.DEEPSLATE_ASTRALITE_ORE.get(),
                        ModBlocks.END_STONE_ZENITHRA_ORE.get(),
                        ModBlocks.RAW_ASTRALITE_BLOCK.get(),
                        ModBlocks.ZENITHRA_BLOCK.get(),
                        ModBlocks.RAW_NEXALITE_BLOCK.get(),
                        ModBlocks.CELESTINE_BLOCK.get(),
                        ModBlocks.DEEPSLATE_NEXALITE_ORE.get(),
                        ModBlocks.NETHER_ANTRACITE_ORE.get(),
                        ModBlocks.ANTRACITE_BLOCK.get());
            });
        }
    }
}
