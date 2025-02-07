package net.theivan066.randomholos;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.theivan066.randomholos.block.ModBlocks;
import net.theivan066.randomholos.block.entity.ModBlockEntities;
import net.theivan066.randomholos.effect.ModEffects;
import net.theivan066.randomholos.enchantment.ModEnchantments;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.fluid.ModFluidTypes;
import net.theivan066.randomholos.fluid.ModFluids;
import net.theivan066.randomholos.item.ModArmorMaterials;
import net.theivan066.randomholos.item.ModCreativeModeTabs;
import net.theivan066.randomholos.item.ModItemProperties;
import net.theivan066.randomholos.item.ModItems;
import net.theivan066.randomholos.loot.ModLootModifiers;
import net.theivan066.randomholos.particle.ModParticles;
import net.theivan066.randomholos.potion.ModPotions;
import net.theivan066.randomholos.recipe.ModRecipes;
import net.theivan066.randomholos.registries.*;
import net.theivan066.randomholos.screen.ModMenuTypes;
import net.theivan066.randomholos.sound.ModSounds;
import net.theivan066.randomholos.worldgen.tree.trunk_placer.ModTrunkPlacerTypes;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RandomHolos.MOD_ID)
public class RandomHolos {
    public static final String MOD_ID = "randomholos";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RandomHolos(IEventBus modEventBus, ModContainer modContainer) {
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModPotions.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModFluids.register(modEventBus);

        ModEnchantments.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModParticles.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModArmorMaterials.register(modEventBus);

        ModTrunkPlacerTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        FlowerPotBlockRegistries.registerFlowerPots();
        BrewingRecipeRegistries.registerBrewingRecipes();
        CompostableRegistries.registerCompostableItems(event);

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                WoodTypeRegistries.registerWoodTypes();
                ModItemProperties.addCustomItemProperties();
                ItemRenderLayerRegistries.registerRenderLayers();
            });
        }
    }
}
