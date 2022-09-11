package tech.op65n.krompir.module.addon.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.annotation.ModuleInformation;

@ModuleInformation(
        module = "BedrockYeeter",
        version = "1.0.0",
        author = "Frcsty",
        description = "Yeets players if they hold a bedrock\n without the &fpotato.bedrockyeeter.bypass &7permission"
)
public final class BedrockYeeterImplementation extends AddonImplementation implements Listener {

    @Override
    public void enable() {
        getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
    }

    @EventHandler
    public void onItemHold(final PlayerItemHeldEvent event) {
        final Player player = event.getPlayer();

        if (player.isOp()) return;
        if (player.hasPermission("potato.bedrockyeeter.bypass")) return;

        final ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null) return;
        if (item.getType() != Material.BEDROCK) return;

        player.banPlayer(player.getName() + " ni v sudoers datoteki. Ta incident bo prijavljen.");
    }

    @Override
    public void disable() { }
}
