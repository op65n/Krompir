package tech.op65n.krompir;

import org.bukkit.plugin.java.JavaPlugin;
import tech.op65n.krompir.addon.bounce.BounceAddon;
import tech.op65n.krompir.addon.stonecutter.StoneCutterAddon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class KrompirPlugin extends JavaPlugin {

    private final Set<Addon> addons = new HashSet<>(Arrays.asList(
        new BounceAddon(), new StoneCutterAddon()
    ));

    @Override
    public void onEnable() {
        saveDefaultConfig();

        addons.forEach(it -> it.register(this));
    }

    @Override
    public void onDisable() {
        addons.forEach(it -> it.unregister(this));

        reloadConfig();
    }

}
