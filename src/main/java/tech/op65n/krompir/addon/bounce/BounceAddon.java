package tech.op65n.krompir.addon.bounce;

import org.bukkit.Bukkit;
import org.bukkit.entity.Sheep;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import tech.op65n.krompir.Addon;
import tech.op65n.krompir.KrompirPlugin;
import tech.op65n.krompir.addon.bounce.listener.PlayerDamageListener;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public final class BounceAddon implements Addon {

    private final PlayerDamageListener damageListener = new PlayerDamageListener();

    private BukkitTask sheepBounceTask;
    private double velocityMultiplier;

    @Override
    public void register(final KrompirPlugin plugin) {
        this.velocityMultiplier = plugin.getConfig().getDouble("sheep-bounce.velocity-multiplier");

        Bukkit.getPluginManager().registerEvents(damageListener, plugin);

        sheepBounceTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    final UUID uuid = player.getUniqueId();
                    final Set<Sheep> nearbySheep = player.getNearbyEntities(0.5, 0.5, 0.5).stream()
                            .filter(entity -> entity instanceof Sheep)
                            .map(entity -> (Sheep) entity)
                            .collect(Collectors.toSet());

                    if (nearbySheep.isEmpty()) {
                        if (player.isOnGround())
                            damageListener.removePlayer(uuid);
                        return;
                    }

                    final Vector playerVelocity = player.getVelocity();
                    player.setVelocity(new Vector(playerVelocity.getX(), velocityMultiplier, playerVelocity.getZ()));
                    damageListener.addPlayer(uuid);
                });
            }
        }.runTaskTimer(plugin, 1, 1);
    }

    @Override
    public void unregister(final KrompirPlugin plugin) {
        if (sheepBounceTask == null) {
            return;
        }

        sheepBounceTask.cancel();
    }
}
