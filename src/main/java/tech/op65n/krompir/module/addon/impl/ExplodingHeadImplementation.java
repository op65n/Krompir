package tech.op65n.krompir.module.addon.impl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.annotation.ModuleInformation;

import java.util.HashSet;
import java.util.Set;

@ModuleInformation(
        module = "ExplodingHead",
        version = "1.0.0",
        author = "Frcsty",
        description = "Makes the player wear an exploding\n &7TNT on their head"
)
public final class ExplodingHeadImplementation extends AddonImplementation implements CommandExecutor {

    private final Set<Player> users = new HashSet<>();
    private BukkitTask explodingHeadTask;

    @Override
    public void enable() {
        this.explodingHeadTask = new BukkitRunnable() {
            @Override
            public void run() {
                users.forEach(user ->
                        user.getWorld().createExplosion(user.getLocation().add(0, 1.5, 0), 2, false)
                );
            }
        }.runTaskTimer(getPlugin(), 0, 100);

        getPlugin().getCommand("exploding-head").setExecutor(this);
    }

    @Override
    public void disable() {
        if (explodingHeadTask == null) {
            return;
        }

        explodingHeadTask.cancel();
        getPlugin().getCommand("exploding-head").setExecutor(null);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final Player player = (Player) sender;
        final Entity tnt = player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);

        player.addPassenger(tnt);
        users.add(player);
        return true;
    }
}
