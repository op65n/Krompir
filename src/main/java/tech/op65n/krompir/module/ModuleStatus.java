package tech.op65n.krompir.module;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import tech.op65n.krompir.KrompirPlugin;
import tech.op65n.krompir.module.addon.AddonImplementation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class ModuleStatus {

    private final Map<ModuleType, AddonImplementation> modules = new HashMap<>();
    private final KrompirPlugin plugin;

    public ModuleStatus(final KrompirPlugin plugin) {
        this.plugin = plugin;
    }

    public void saveModules() {
        try {
            final File file = new File(plugin.getDataFolder(), "module/enabled-modules.yml");
            final FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            for (final ModuleType type : modules.keySet()) {
                final AddonImplementation implementation = modules.get(type);

                configuration.set("module." + type.name(), implementation.getStatus());
            }

            configuration.save(file);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadModules() {
        final File file = new File(plugin.getDataFolder(), "module/enabled-modules.yml");
        final FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        final ConfigurationSection section = configuration.getConfigurationSection("module");

        if (section == null) {
            return;
        }

        for (final String key : section.getKeys(false)) {
            final ModuleType type = ModuleType.getNullable(key);

            if (type == null)
                continue;

            final AddonImplementation implementation = type.getImplementation();
            implementation.setStatus(Boolean.parseBoolean(section.getString(key)));

            modules.put(type, implementation);
        }
    }

    public void updateModule(final ModuleType type, final AddonImplementation implementation) {
        this.modules.put(type, implementation);
    }

    public Map<ModuleType, AddonImplementation> getModules() {
        return this.modules;
    }
}
