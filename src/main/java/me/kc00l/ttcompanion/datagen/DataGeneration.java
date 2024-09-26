package me.kc00l.ttcompanion.datagen;


import me.kc00l.ttcompanion.TTCompanion;
import me.kc00l.ttcompanion.worldgen.structure.ModStructureSets;
import me.kc00l.ttcompanion.worldgen.structure.ModStructures;
import me.kc00l.ttcompanion.worldgen.structure.ModTemplatePools;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DataGeneration {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider =
                CompletableFuture.supplyAsync(DataGeneration::getLookupProvider);

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
                packOutput,
                CompletableFuture.supplyAsync(DataGeneration::getPatchedRegistries),
                Set.of(TTCompanion.MOD_ID)));
    }

    private static RegistrySetBuilder.PatchedRegistries getPatchedRegistries() {
        final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
        registryBuilder.add(Registries.STRUCTURE, ModStructures::bootstrap);
        registryBuilder.add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap);
        registryBuilder.add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap);

        // We need the BIOME registry to be present, so we can use a biome tag, doesn't matter that it's empty
        registryBuilder.add(Registries.BIOME, $ -> {});
        RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
        Cloner.Factory cloner$factory = new Cloner.Factory();
        net.neoforged.neoforge.registries.DataPackRegistriesHooks.getDataPackRegistriesWithDimensions()
                .forEach(p_311524_ -> p_311524_.runWithArguments(cloner$factory::addCodec));
        return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup(), cloner$factory);
    }

    private static HolderLookup.Provider getLookupProvider() {
        return getPatchedRegistries().full();
    }
}
