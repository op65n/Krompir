package tech.op65n.krompir.module.addon.impl.gpteleport;

import org.bukkit.Bukkit;
import tech.op65n.krompir.KrompirPlugin;
import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.addon.impl.gpteleport.command.GPTPListCommand;
import tech.op65n.krompir.module.addon.impl.gpteleport.command.GPTeleportCommand;
import tech.op65n.krompir.module.addon.impl.gpteleport.command.SetClaimName;
import tech.op65n.krompir.module.addon.impl.gpteleport.command.SetClaimSpawn;
import tech.op65n.krompir.module.addon.impl.gpteleport.config.Config;
import tech.op65n.krompir.module.addon.impl.gpteleport.config.Messages;
import tech.op65n.krompir.module.addon.impl.gpteleport.handler.MessageHandler;
import tech.op65n.krompir.module.addon.impl.gpteleport.listener.PlayerListener;
import tech.op65n.krompir.module.addon.impl.gpteleport.runnable.Schedule;
import tech.op65n.krompir.module.addon.impl.gpteleport.storage.DataStorage;
import tech.op65n.krompir.module.annotation.ModuleInformation;

@ModuleInformation(
        module = "GPTeleport",
        version = "1.0.0",
        author = "Frcsty",
        description = "Grants users a interactive menu\n &7for claim teleportation"
)
public final class GPTeleportImplementation extends AddonImplementation {

    @Override
    public void enable() {
        final KrompirPlugin plugin = getPlugin();

        plugin.getCommand("gpteleportlist").setExecutor(new GPTPListCommand());
        plugin.getCommand("gpteleport").setExecutor(new GPTeleportCommand());
        plugin.getCommand("setclaimspawn").setExecutor(new SetClaimSpawn());
        plugin.getCommand("setclaimname").setExecutor(new SetClaimName());
        plugin.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);
        new DataStorage();
        Messages.initialize();
        Config.initialize();
        new Schedule().runTaskTimer(plugin, 0L, (3600 * 20L));

        if (plugin.getServer().getPluginManager().getPlugin("GriefPrevention") != null) {
            MessageHandler.sendConsole("[" + plugin.getDescription().getPrefix() + "] &7Successfully hooked into GriefPrevention");
            MessageHandler.sendConsole("");
        } else {
            MessageHandler.sendConsole("[" + plugin.getDescription().getPrefix() + "] &cGriefPrevention was not found! Please add GriefPrevention.");
            MessageHandler.sendConsole("");
            Bukkit.getPluginManager().disablePlugin(plugin);

        }
    }

    @Override
    public void disable() {
        final DataStorage data = DataStorage.getConfig();
        data.save();
    }
}
