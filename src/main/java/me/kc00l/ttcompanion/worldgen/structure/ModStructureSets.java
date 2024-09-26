package me.kc00l.ttcompanion.worldgen.structure;

import me.kc00l.ttcompanion.TTCompanion;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;

import java.util.List;

public class ModStructureSets {
    public static final ResourceKey<StructureSet> SIMPLE_COMMON_QUARRIES = register("simple_common_quarries");

    public static void bootstrap(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> structureHolderGetter = context.lookup(Registries.STRUCTURE);
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);

        context.register(SIMPLE_COMMON_QUARRIES, new StructureSet(
                List.of(
                        StructureSet.entry(structureHolderGetter.getOrThrow(ModStructures.SIMPLE_COMMON_QUARRY_1)),
                        StructureSet.entry(structureHolderGetter.getOrThrow(ModStructures.SIMPLE_COMMON_QUARRY_2)),
                        StructureSet.entry(structureHolderGetter.getOrThrow(ModStructures.SIMPLE_COMMON_QUARRY_3)),
                        StructureSet.entry(structureHolderGetter.getOrThrow(ModStructures.SIMPLE_COMMON_QUARRY_4))
                ), new ConcentricRingsStructurePlacement(16, 4, 32, biomeHolderGetter.getOrThrow(BiomeTags.HAS_MINESHAFT))
        ));
    }

    private static ResourceKey<StructureSet> register(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(TTCompanion.MOD_ID, name));
    }
}
