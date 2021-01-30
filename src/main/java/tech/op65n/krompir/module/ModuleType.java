package tech.op65n.krompir.module;

import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.addon.impl.StoneCutterImplementation;

public enum ModuleType {

    STONE_CUTTER(new StoneCutterImplementation()),
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
