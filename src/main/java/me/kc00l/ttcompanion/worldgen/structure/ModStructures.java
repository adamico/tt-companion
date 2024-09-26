package me.kc00l.ttcompanion.worldgen.structure;

import me.kc00l.ttcompanion.TTCompanion;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Map;
import java.util.Optional;

public class ModStructures {
    public static final ResourceKey<Structure> SIMPLE_COMMON_QUARRY_1 = createKey("simple_common_quarry_1");
    public static final ResourceKey<Structure> SIMPLE_COMMON_QUARRY_2 = createKey("simple_common_quarry_2");
    public static final ResourceKey<Structure> SIMPLE_COMMON_QUARRY_3 = createKey("simple_common_quarry_3");
    public static final ResourceKey<Structure> SIMPLE_COMMON_QUARRY_4 = createKey("simple_common_quarry_4");

    public static void bootstrap(BootstrapContext<Structure> context) {
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> templatePoolHolderGetter = context.lookup(Registries.TEMPLATE_POOL);

        Structure.StructureSettings structureSettings = new Structure.StructureSettings(biomeHolderGetter.getOrThrow(BiomeTags.HAS_MINESHAFT), Map.of(),
                GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE);

        context.register(SIMPLE_COMMON_QUARRY_1, new QuarryStructure(
                structureSettings, templatePoolHolderGetter.getOrThrow(ModTemplatePools.SIMPLE_COMMON_QUARRY_1),
                Optional.empty(), 1, ConstantHeight.of(VerticalAnchor.absolute(0)), Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                80, DimensionPadding.ZERO, LiquidSettings.APPLY_WATERLOGGING
        ));
        context.register(SIMPLE_COMMON_QUARRY_2, new QuarryStructure(
                structureSettings, templatePoolHolderGetter.getOrThrow(ModTemplatePools.SIMPLE_COMMON_QUARRY_2),
                Optional.empty(), 1, ConstantHeight.of(VerticalAnchor.absolute(0)), Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                80, DimensionPadding.ZERO, LiquidSettings.APPLY_WATERLOGGING
        ));
        context.register(SIMPLE_COMMON_QUARRY_3, new QuarryStructure(
                structureSettings, templatePoolHolderGetter.getOrThrow(ModTemplatePools.SIMPLE_COMMON_QUARRY_3),
                Optional.empty(), 1, ConstantHeight.of(VerticalAnchor.absolute(0)), Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                80, DimensionPadding.ZERO, LiquidSettings.APPLY_WATERLOGGING
        ));
        context.register(SIMPLE_COMMON_QUARRY_4, new QuarryStructure(
                structureSettings, templatePoolHolderGetter.getOrThrow(ModTemplatePools.SIMPLE_COMMON_QUARRY_4),
                Optional.empty(), 1, ConstantHeight.of(VerticalAnchor.absolute(0)), Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                80, DimensionPadding.ZERO, LiquidSettings.APPLY_WATERLOGGING
        ));
    }

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(TTCompanion.MOD_ID, name));
    }
}
