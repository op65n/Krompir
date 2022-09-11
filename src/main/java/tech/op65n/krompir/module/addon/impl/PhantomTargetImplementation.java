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

    private BukkitTask task;

    @Override
    public void enable() {
        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(it -> {
                    if (!it.hasPermission("potato.user.phantom")) {
                        return;
                    }

                    it.setStatistic(Statistic.TIME_SINCE_REST, 0);
                });
            }
        }.runTaskTimer(getPlugin(), 200L, 200L);
    }

    @Override
    public void disable() {
        if (this.task == null) {
            return;
        }

        this.task.cancel();
    }

}
