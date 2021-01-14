package tech.op65n.krompir.command;

import me.clip.placeholderapi.libs.JSONMessage;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.op65n.krompir.KrompirPlugin;
import tech.op65n.krompir.addon.annotation.Addon;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Command("addons")
public final class AddonCommand extends CommandBase {

    private final KrompirPlugin plugin;
    private final List<AddonHolder> addons;

    public AddonCommand(final KrompirPlugin plugin) {
        this.plugin = plugin;

        this.addons = getAddons();
    }

    @Default
    @Permission("krompir.command.addons")
    public void onCommand(final Player player) {
        getAddons().forEach(holder -> {
            final tech.op65n.krompir.addon.Addon addon = holder.addon;
            final JSONMessage message = JSONMessage.create(String.format(
                    "> Addon: %s, Version: %s, Status: %s",
                    holder.addonName, holder.version, addon.getActivityStatus()
            ));

            message.tooltip("click to toggle activity");
            message.runCommand(addon.getActivityStatus() ? "/addons disable " + holder.addonName : "/addons enable " + holder.addonName);
            message.send(player);
        });
    }

    @SubCommand("disable")
    @Permission("krompir.command.addons")
    public void onDisable(final CommandSender sender, final String[] addonName) {
        final Optional<AddonHolder> addon = getByName(String.join(" ", addonName));

        if (!addon.isPresent()) {
            return;
        }

        addon.get().addon.unregister(plugin);
    }

    @SubCommand("enable")
    @Permission("krompir.command.addons")
    public void onEnable(final CommandSender sender, final String[] addonName) {
        final Optional<AddonHolder> addon = getByName(String.join(" ", addonName));

        if (!addon.isPresent()) {
            return;
        }

        addon.get().addon.register(plugin);
    }

    private Optional<AddonHolder> getByName(final String addonName) {
        return addons.stream()
                .filter(holder -> holder.addonName.equalsIgnoreCase(addonName))
                .findAny();
    }

    private List<AddonHolder> getAddons() {
        return plugin.getAddonSet().stream()
                .filter(clazz -> clazz.getClass().isAnnotationPresent(tech.op65n.krompir.addon.annotation.Addon.class))
                .map(clazz -> {
                    final Addon addon = clazz.getClass().getAnnotation(Addon.class);

                    return new AddonHolder(
                            addon.addonName(),
                            addon.version(),
                            addon.description(),
                            addon.author(),
                            clazz
                    );
                })
                .collect(Collectors.toList());
    }

    private final class AddonHolder {

        private final String addonName;
        private final String version;
        private final String description;
        private final String author;

        private final tech.op65n.krompir.addon.Addon addon;

        AddonHolder(final String addonName, final String version, final String description, final String author, final tech.op65n.krompir.addon.Addon addon) {
            this.addonName = addonName;
            this.version = version;
            this.description = description;
            this.author = author;

            this.addon = addon;
        }

    }

}
