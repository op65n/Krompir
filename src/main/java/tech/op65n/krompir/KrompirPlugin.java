package tech.op65n.krompir;

import me.mattstudios.mf.base.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import tech.op65n.krompir.addon.Addon;
import tech.op65n.krompir.addon.implementations.BounceAddon;
import tech.op65n.krompir.addon.implementations.StoneCutterAddon;
import tech.op65n.krompir.command.AddonCommand;

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

        final CommandManager commandManager = new CommandManager(this);
        commandManager.register(
                new AddonCommand(this)
        );
    }

    @Override
    public void onDisable() {
        addons.forEach(it -> it.unregister(this));

        reloadConfig();
    }

    public Set<Addon> getAddonSet() {
        return this.addons;
    }
}
