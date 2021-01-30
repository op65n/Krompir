package tech.op65n.krompir.command;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.base.CommandBase;
import me.mattstudios.mfgui.gui.guis.PaginatedGui;
import org.bukkit.entity.Player;
import tech.op65n.krompir.KrompirPlugin;
import tech.op65n.krompir.command.menu.ModuleMenu;

import java.util.logging.Level;

@Command("module")
public final class ModuleCommand extends CommandBase {

    private final KrompirPlugin plugin;

    public ModuleCommand(final KrompirPlugin plugin) {
        this.plugin = plugin;
    }

    @Default
    @Permission("krompir.command.module")
    public void onCommand(final Player player) {
        final PaginatedGui menu = new ModuleMenu(plugin).getMenu();

        if (menu == null) {
            plugin.getLogger().log(Level.WARNING,
                    String.format("Failed to Construct & Open for User %s", player.getName())
            );
            return;
        }

        menu.open(player);
    }

}
