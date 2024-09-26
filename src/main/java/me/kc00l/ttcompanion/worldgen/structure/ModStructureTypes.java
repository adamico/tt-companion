package me.kc00l.ttcompanion.worldgen.structure;

import com.mojang.serialization.MapCodec;
import me.kc00l.ttcompanion.TTCompanion;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, TTCompanion.MOD_ID);

    public static final DeferredHolder<StructureType<?>, StructureType<QuarryStructure>> QUARRY_STRUCTURES =
            STRUCTURE_TYPES.register("quarry_structures", () ->
                    explicitStructureTypeTyping(QuarryStructure.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(MapCodec<T> structureCodec) {
        return () -> structureCodec;
    }
}
