package tech.op65n.krompir.addon;

import tech.op65n.krompir.KrompirPlugin;

public interface Addon {

    default void register(final KrompirPlugin plugin) {}

    default void unregister(final KrompirPlugin plugin) {}

    boolean getActivityStatus();

}
