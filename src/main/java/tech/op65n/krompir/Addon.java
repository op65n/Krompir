package tech.op65n.krompir;

public interface Addon {

    default void register(final KrompirPlugin plugin) {}

    default void unregister(final KrompirPlugin plugin) {}

}
