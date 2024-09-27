package me.kc00l.ttcompanion.worldgen.structure;

import me.kc00l.ttcompanion.TTCompanion;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

import java.util.List;
import java.util.Optional;

public class ModStructureSets {
    public static final ResourceKey<StructureSet> SIMPLE_COMMON_QUARRIES = register("simple_common_quarries");
    public static final ResourceKey<StructureSet> SIMPLE_UNCOMMON_QUARRIES = register("simple_uncommon_quarries");
    public static final ResourceKey<StructureSet> SIMPLE_RARE_QUARRIES = register("simple_rare_quarries");
    public static final ResourceKey<StructureSet> SIMPLE_LEGENDARY_QUARRIES = register("simple_legendary_quarries");

    public static void bootstrap(BootstrapContext<StructureSet> context) {
        context.register(SIMPLE_COMMON_QUARRIES, buildStructureSet(context, ModStructures.SIMPLE_COMMON_QUARRIES,
                2043480411,16, 4, 32));
        context.register(SIMPLE_UNCOMMON_QUARRIES, buildStructureSet(context, ModStructures.SIMPLE_UNCOMMON_QUARRIES,
                2126950442,24, 8, 16));
        context.register(SIMPLE_RARE_QUARRIES, buildStructureSet(context, ModStructures.SIMPLE_RARE_QUARRIES,
                1612224828, 32,6, 9));
        context.register(SIMPLE_LEGENDARY_QUARRIES, buildStructureSet(context, ModStructures.SIMPLE_LEGENDARY_QUARRIES,
                1867895815, 48,4, 8));
    }

    private static StructureSet buildStructureSet (
            BootstrapContext<StructureSet> context,
            List<ResourceKey<Structure>> structures,
            int salt, int distance, int spread, int count) {

        HolderGetter<Structure> structureHolderGetter = context.lookup(Registries.STRUCTURE);

        List<StructureSet.StructureSelectionEntry> structureEntries = structures.stream()
                .map(structure -> StructureSet.entry(structureHolderGetter.getOrThrow(structure)))
                .toList();
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);
        return new StructureSet(
                structureEntries,
                new ConcentricRingsStructurePlacement(
                        Vec3i.ZERO,
                        StructurePlacement.FrequencyReductionMethod.DEFAULT,
                        1f, salt, Optional.empty(), distance, spread, count,
                        biomeHolderGetter.getOrThrow(BiomeTags.HAS_MINESHAFT))
        );
    }

    private static ResourceKey<StructureSet> register(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(TTCompanion.MOD_ID, name));
    }
}
