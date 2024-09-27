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

    public static final ResourceKey<StructureTemplatePool> SIMPLE_UNCOMMON_QUARRY_1 = createKey("simple_uncommon_quarry_1");
    public static final ResourceKey<StructureTemplatePool> SIMPLE_UNCOMMON_QUARRY_2 = createKey("simple_uncommon_quarry_2");
    public static final ResourceKey<StructureTemplatePool> SIMPLE_UNCOMMON_QUARRY_3 = createKey("simple_uncommon_quarry_3");
    public static final ResourceKey<StructureTemplatePool> SIMPLE_UNCOMMON_QUARRY_4 = createKey("simple_uncommon_quarry_4");

    public static final ResourceKey<StructureTemplatePool> SIMPLE_RARE_QUARRY_1 = createKey("simple_rare_quarry_1");
    public static final ResourceKey<StructureTemplatePool> SIMPLE_RARE_QUARRY_2 = createKey("simple_rare_quarry_2");
    public static final ResourceKey<StructureTemplatePool> SIMPLE_RARE_QUARRY_3 = createKey("simple_rare_quarry_3");

    public static final ResourceKey<StructureTemplatePool> SIMPLE_LEGENDARY_QUARRY_1 = createKey("simple_legendary_quarry_1");
    public static final ResourceKey<StructureTemplatePool> SIMPLE_LEGENDARY_QUARRY_2 = createKey("simple_legendary_quarry_2");


    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> templateGetter = context.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> emptyPoolHolder = templateGetter.getOrThrow(Pools.EMPTY);

        context.register(SIMPLE_COMMON_QUARRY_1, buildStructureTemplatePool(emptyPoolHolder, "simple_common_quarry_1"));
        context.register(SIMPLE_COMMON_QUARRY_2, buildStructureTemplatePool(emptyPoolHolder, "simple_common_quarry_2"));
        context.register(SIMPLE_COMMON_QUARRY_3, buildStructureTemplatePool(emptyPoolHolder, "simple_common_quarry_3"));
        context.register(SIMPLE_COMMON_QUARRY_4, buildStructureTemplatePool(emptyPoolHolder, "simple_common_quarry_4"));
        context.register(SIMPLE_UNCOMMON_QUARRY_1, buildStructureTemplatePool(emptyPoolHolder, "simple_uncommon_quarry_1"));
        context.register(SIMPLE_UNCOMMON_QUARRY_2, buildStructureTemplatePool(emptyPoolHolder, "simple_uncommon_quarry_2"));
        context.register(SIMPLE_UNCOMMON_QUARRY_3, buildStructureTemplatePool(emptyPoolHolder, "simple_uncommon_quarry_3"));
        context.register(SIMPLE_UNCOMMON_QUARRY_4, buildStructureTemplatePool(emptyPoolHolder, "simple_uncommon_quarry_4"));
        context.register(SIMPLE_RARE_QUARRY_1, buildStructureTemplatePool(emptyPoolHolder, "simple_rare_quarry_1"));
        context.register(SIMPLE_RARE_QUARRY_2, buildStructureTemplatePool(emptyPoolHolder, "simple_rare_quarry_2"));
        context.register(SIMPLE_RARE_QUARRY_3, buildStructureTemplatePool(emptyPoolHolder, "simple_rare_quarry_3"));
        context.register(SIMPLE_LEGENDARY_QUARRY_1, buildStructureTemplatePool(emptyPoolHolder, "simple_legendary_quarry_1"));
        context.register(SIMPLE_LEGENDARY_QUARRY_2, buildStructureTemplatePool(emptyPoolHolder, "simple_legendary_quarry_2"));
    }

    private static StructureTemplatePool buildStructureTemplatePool(
            Holder<StructureTemplatePool> emptyPoolHolder,
            String templateId) {
        String TEMPLATE_PREFIX = "ttcompanion:";
        return new StructureTemplatePool(
                emptyPoolHolder,
                List.of(Pair.of(StructurePoolElement.single(TEMPLATE_PREFIX + templateId), 1)),
                StructureTemplatePool.Projection.RIGID
        );
    }

    public static ResourceKey<StructureTemplatePool> createKey(String path) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TTCompanion.MOD_ID, path));
    }
}
