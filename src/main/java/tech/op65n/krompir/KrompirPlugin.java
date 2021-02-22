package tech.op65n.krompir;

import me.mattstudios.mf.base.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import tech.op65n.krompir.command.ModuleCommand;
import tech.op65n.krompir.module.ModuleStatus;

import java.io.File;
import java.util.Arrays;

public final class KrompirPlugin extends JavaPlugin {

    private final ModuleStatus moduleStatus = new ModuleStatus(this);
    private CommandManager manager;

    // Fucking disgusting
    private static KrompirPlugin instance;
    public static KrompirPlugin getInstance() { return instance; }
    //

    @Override
    public void onEnable() {
        // Fucking disgusting
        instance = this;
        //

        saveDefaultConfig();

        saveResources(
                "menu/module-menu.yml",
                "module/enabled-modules.yml"
        );

        this.manager = new CommandManager(this);
        manager.register(
                new ModuleCommand(this)
        );

        moduleStatus.loadModules();
    }

    @Override
    public void onDisable() {
        reloadConfig();

        moduleStatus.saveModules();
    }

    private void saveResources(final String... paths) {
        Arrays.stream(paths).forEach(path -> {
            if (!new File(getDataFolder(), path).exists()) {
                saveResource(path, false);
            }
        });
    }

    public ModuleStatus getModuleStatus() {
        return this.moduleStatus;
    }

    public CommandManager getManager() {
        return this.manager;
    }
}
