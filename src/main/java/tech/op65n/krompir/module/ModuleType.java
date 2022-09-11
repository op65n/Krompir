package tech.op65n.krompir.module;

import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.addon.impl.*;
import tech.op65n.krompir.module.addon.impl.gpteleport.GPTeleportImplementation;
import tech.op65n.krompir.module.addon.impl.stonecutter.StoneCutterImplementation;

public enum ModuleType {

    STONE_CUTTER(new StoneCutterImplementation()),
    PHANTOM_TARGET(new PhantomTargetImplementation()),
    EXPLODING_SHEEP(new ExplodingSheepImplementation()),
    GP_TELEPORT(new GPTeleportImplementation()),
    EXPLODING_HEAD(new ExplodingHeadImplementation()),
    BEDROCK_YEETER(new BedrockYeeterImplementation()),
    LIMITED_ITEM(new LimitedItemImplementation());

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
