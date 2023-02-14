package net.kaupenjoe.tutorialmod;

import com.mojang.logging.LogUtils;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.enchantment.ModEnchantments;
import net.kaupenjoe.tutorialmod.loot.ModLootModifiers;
import net.kaupenjoe.tutorialmod.tabs.ModCreativeModeTabs;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MOD_ID)
public class TutorialMod {
    public static final String MOD_ID = "tutorialmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Very Important Comment
    public TutorialMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.POTTY);
            event.accept(ModItems.MAGICAL_CRYSTAL);
            event.accept(ModItems.MAGICAL_SHARD);
        }

        if(event.getTab() == CreativeModeTabs.BUILDING_BLOCKS) {

        }

        if(event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModItems.MAGICAL_CRYSTAL);
            event.accept(ModItems.MAGICAL_SHARD);
        }

        if(event.getTab() == ModCreativeModeTabs.TUTORIAL_TAB) {
            //Items
            event.accept(ModItems.POTTY);
            event.accept(ModItems.MAGICAL_CRYSTAL);
            event.accept(ModItems.MAGICAL_SHARD);
            event.accept(ModItems.MAGICAL_STAFF);
            //Blocks
            event.accept(ModBlocks.GEM_INFUSING_STATION);
        }
    }
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
