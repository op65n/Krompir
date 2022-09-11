package tech.op65n.krompir.module.addon.impl;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.annotation.ModuleInformation;
import tech.op65n.krompir.util.Color;

import java.util.Arrays;
import java.util.stream.Collectors;

@ModuleInformation(
        module = "LimitedItem",
        version = "1.0.0",
        author = "Frcsty",
        description = "Gives the receiver an item\n &7with a predefined amount of uses"
)
public final class LimitedItemImplementation extends AddonImplementation implements CommandExecutor, Listener {

    private final NamespacedKey key = new NamespacedKey(getPlugin(), "limited-uses");

    @Override
    public void enable() {
        getPlugin().getCommand("limited-item").setExecutor(this);
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }

    @Override
    public void disable() {

    }

    // /limited-item give Frcsty <material> <name> <lore> <uses>
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args[0].equalsIgnoreCase("give")) {
            final ItemStack item = new ItemStack(Material.valueOf(args[1].toUpperCase()));
            final ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(Color.translate(args[2]));
            meta.setLore(Arrays.stream(args[3].split(";")).map(Color::translate).collect(Collectors.toList()));

            meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, Integer.parseInt(args[4]));

            item.setItemMeta(meta);
            return true;
        }

        return false;
    }

    @EventHandler
    public void onUse(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final ItemStack item = player.getActiveItem();
        final ItemMeta meta = item.getItemMeta();

        if (meta == null) return;
        final Integer uses = meta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
        if (uses == null) return;
        if (uses <= 0) {
            player.getInventory().getItemInMainHand().setType(Material.AIR);
            return;
        }

        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, uses - 1);
        item.setItemMeta(meta);
    }

}
