package me.kc00l.ttcompanion.worldgen.structure;

import com.mojang.datafixers.util.Pair;
import me.kc00l.ttcompanion.TTCompanion;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.List;

public class ModTemplatePools {
    public static final ResourceKey<StructureTemplatePool> SIMPLE_COMMON_QUARRY_1 = createKey("simple_common_quarry_1");
    public static final ResourceKey<StructureTemplatePool> SIMPLE_COMMON_QUARRY_2 = createKey("simple_common_quarry_2");
    public static final ResourceKey<StructureTemplatePool> SIMPLE_COMMON_QUARRY_3 = createKey("simple_common_quarry_3");
    public static final ResourceKey<StructureTemplatePool> SIMPLE_COMMON_QUARRY_4 = createKey("simple_common_quarry_4");

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> templateGetter = context.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> emptyPoolHolder = templateGetter.getOrThrow(Pools.EMPTY);

        context.register(SIMPLE_COMMON_QUARRY_1, new StructureTemplatePool(emptyPoolHolder,
                List.of(Pair.of(StructurePoolElement.single("ttcompanion:simple_common_quarry_1"), 1)),
                StructureTemplatePool.Projection.RIGID));
        context.register(SIMPLE_COMMON_QUARRY_2, new StructureTemplatePool(emptyPoolHolder,
                List.of(Pair.of(StructurePoolElement.single("ttcompanion:simple_common_quarry_2"), 1)),
                StructureTemplatePool.Projection.RIGID));
        context.register(SIMPLE_COMMON_QUARRY_3, new StructureTemplatePool(emptyPoolHolder,
                List.of(Pair.of(StructurePoolElement.single("ttcompanion:simple_common_quarry_3"), 1)),
                StructureTemplatePool.Projection.RIGID));
        context.register(SIMPLE_COMMON_QUARRY_4, new StructureTemplatePool(emptyPoolHolder,
                List.of(Pair.of(StructurePoolElement.single("ttcompanion:simple_common_quarry_4"), 1)),
                StructureTemplatePool.Projection.RIGID));
    }

    public static ResourceKey<StructureTemplatePool> createKey(String path) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TTCompanion.MOD_ID, path));
    }
}
