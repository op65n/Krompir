package tech.op65n.krompir.module.addon.impl;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Sheep;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.annotation.ModuleInformation;

import java.util.List;
import java.util.stream.Collectors;

@ModuleInformation(
        module = "ExplodingSheep",
        version = "1.0.0",
        author = "Frcsty",
        description = "Makes sheep explode when\n &7a player jumps on it"
)
public final class ExplodingSheepImplementation extends AddonImplementation {

    private float explosionPower;
    private BukkitTask sheepTask;

    @Override
    public void enable() {
        final FileConfiguration configuration = getPlugin().getConfig();

        this.explosionPower = (float) configuration.getDouble("exploding-sheep.explosion-power");

        this.sheepTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    final List<Sheep> nearbySheep = player.getNearbyEntities(0.5, 0.5, 0.5).stream()
                            .filter(entity -> entity instanceof Sheep)
                            .map(entity -> (Sheep) entity)
                            .collect(Collectors.toList());

                    if (nearbySheep.isEmpty())
                        return;

                    if (player.isOnGround())
                        return;

                    final Sheep entity = nearbySheep.get(0);
                    final DyeColor color = entity.getColor();
                    entity.setColor(DyeColor.RED);
                    entity.getLocation().createExplosion(entity, explosionPower, false, false);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), () -> entity.setColor(color), 2L);
                });
            }
        }.runTaskTimer(getPlugin(), 20L, 20L);
    }

    @Override
    public void disable() {
        if (sheepTask == null) {
            return;
        }

        sheepTask.cancel();
    }

}
