package me.kc00l.ttcompanion;

import me.kc00l.ttcompanion.worldgen.biome.region.*;
import me.kc00l.ttcompanion.worldgen.biome.surface.ModSurfaceRules;
import me.kc00l.ttcompanion.worldgen.structure.ModStructureSets;
import me.kc00l.ttcompanion.worldgen.structure.ModStructureTypes;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(TTCompanion.MOD_ID)
public class TTCompanion {
    public static final String MOD_ID = "ttcompanion";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TTCompanion(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModStructureTypes.STRUCTURE_TYPES.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
           Regions.register(new ModRegion1(ResourceLocation.fromNamespaceAndPath(MOD_ID, "overworld_1"), 2));
           Regions.register(new ModRegion2(ResourceLocation.fromNamespaceAndPath(MOD_ID, "overworld_2"), 2));

            // Register our surface rules
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
