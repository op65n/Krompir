package tech.op65n.krompir.addon.implementations;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import tech.op65n.krompir.KrompirPlugin;
import tech.op65n.krompir.addon.Addon;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@tech.op65n.krompir.addon.annotation.Addon(
        addonName = "Bouncy Sheep",
        version = "1.0.0",
        description = "Make sheep bounce the player when jumped on",
        author = "Frcsty"
)
public final class BounceAddon implements Addon {

    private final PlayerDamageListener damageListener = new PlayerDamageListener();

    private BukkitTask sheepBounceTask;
    private double velocityMultiplier;

    private boolean activity;

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
        this.activity = true;
    }

    @Override
    public void unregister(final KrompirPlugin plugin) {
        if (sheepBounceTask == null) {
            return;
        }

        sheepBounceTask.cancel();
        this.activity = false;
    }

    @Override
    public boolean getActivityStatus() {
        return this.activity;
    }

    private final class PlayerDamageListener implements Listener {
        private final Set<UUID> fallDamage = new HashSet<>();

        @EventHandler
        public void onFallDamage(final EntityDamageEvent event) {
            final EntityDamageEvent.DamageCause cause = event.getCause();
            if (cause != EntityDamageEvent.DamageCause.FALL) {
                return;
            }

            final Entity entity = event.getEntity();
            if (!(entity instanceof Player)) {
                return;
            }

            final Player player = (Player) entity;
            if (fallDamage.contains(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }

        void addPlayer(final UUID uuid) {
            this.fallDamage.add(uuid);
        }

        void removePlayer(final UUID uuid) {
            this.fallDamage.remove(uuid);
        }
    }

}
