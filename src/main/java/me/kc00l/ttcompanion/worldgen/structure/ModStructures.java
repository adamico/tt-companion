// taken from https://github.com/TelepathicGrunt/StructureTutorialMod/blob/1.21-Neoforge-Jigsaw/src/main/java/com/telepathicgrunt/structuretutorial/STStructures.java
// by TelepathicGrunt
package me.kc00l.ttcompanion.worldgen.structure;

import com.mojang.serialization.MapCodec;
import me.kc00l.ttcompanion.TTCompanion;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModStructures {

    /**
     * We are using the Deferred Registry system to register our structure as this is the preferred way on Forge.
     * This will handle registering the base structure for us at the correct time so we don't have to handle it ourselves.
     */
    public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, TTCompanion.MOD_ID);

    /**
     * Registers the base structure itself and sets what its path is. In this case,
     * this base structure will have the resource location of tt_companion:quarry_structures.
     */
    public static final DeferredHolder<StructureType<?>, StructureType<QuarryStructures>> QUARRY_STRUCTURES =
            DEFERRED_REGISTRY_STRUCTURE.register("quarry_structures",
                    () -> explicitStructureTypeTyping(QuarryStructures.CODEC));

    /**
     * Originally, I had a double lambda ()->()-> for the RegistryObject line above, but it turns out that
     * some IDEs cannot resolve the typing correctly. This method explicitly states what the return type
     * is so that the IDE can put it into the DeferredRegistry properly.
     */
    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(MapCodec<T> structureCodec) {
        return () -> structureCodec;
    }
}
