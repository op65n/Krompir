package tech.op65n.krompir.module;

import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.addon.impl.ExplodingSheepImplementation;
import tech.op65n.krompir.module.addon.impl.PhantomTargetImplementation;
import tech.op65n.krompir.module.addon.impl.stonecutter.StoneCutterImplementation;
import tech.op65n.krompir.module.addon.impl.gpteleport.GPTeleportImplementation;

public enum ModuleType {

    STONE_CUTTER(new StoneCutterImplementation()),
    PHANTOM_TARGET(new PhantomTargetImplementation()),
    EXPLODING_SHEEP(new ExplodingSheepImplementation()),
    GP_TELEPORT(new GPTeleportImplementation()),
    ;

    private final AddonImplementation implementation;

    ModuleType(final AddonImplementation implementation) {
        this.implementation = implementation;
    }

    public AddonImplementation getImplementation() {
        return this.implementation;
    }

    public static ModuleType getNullable(final String input) {
        ModuleType type = null;

        for (final ModuleType moduleType : values()) {
            if (!moduleType.name().equalsIgnoreCase(input)) {
                continue;
            }

            type = moduleType;
            break;
        }

        return type;
    }

}
