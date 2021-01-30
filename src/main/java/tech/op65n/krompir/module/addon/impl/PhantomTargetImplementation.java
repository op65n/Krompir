package tech.op65n.krompir.module.addon.impl;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.annotation.ModuleInformation;

@ModuleInformation(
        module = "PhantomTarget",
        version = "1.0.0",
        author = "Frcsty",
        description = "Disables phantom targeting\n &7if the user has the &fpotato.user.phantom &7permission"
)
public final class PhantomTargetImplementation extends AddonImplementation {

    private BukkitTask phantomTask;

    @Override
    public void enable() {
        this.phantomTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                    if (player.hasPermission("potato.user.phantom")) {
                        player.setStatistic(Statistic.TIME_SINCE_REST, 0);
                    }
                });
            }
        }.runTaskTimer(getPlugin(), 20L, 20L * 60);
    }

    @Override
    public void disable() {
        if (phantomTask == null) {
            return;
        }

        phantomTask.cancel();
    }

}
