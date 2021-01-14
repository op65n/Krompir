package tech.op65n.krompir.addon.implementations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import tech.op65n.krompir.KrompirPlugin;
import tech.op65n.krompir.addon.Addon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@tech.op65n.krompir.addon.annotation.Addon(
        addonName = "Stone Cutter Damage",
        version = "1.0.0",
        description = "Ensure Stone Cutter's deal exponential damage when stepped on",
        author = "Frcsty"
)
public final class StoneCutterAddon implements Addon {

    private final Map<UUID, Double> exponentialDamage = new HashMap<>();

    private double baseDamage;
    private double exponentialDamageModifier;

    private BukkitTask stoneCutterTask;

    private boolean activity;

    @Override
    public void register(final KrompirPlugin plugin) {
        final FileConfiguration configuration = plugin.getConfig();

        this.baseDamage = configuration.getDouble("stone-cutter.base-damage") / 4;
        this.exponentialDamageModifier = configuration.getDouble("stone-cutter.exponential-damage-gain-value");

        stoneCutterTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    final UUID uuid = player.getUniqueId();
                    final Location location = player.getLocation();

                    if (location.getBlock().getType() != Material.STONECUTTER) {
                        exponentialDamage.remove(uuid);
                        return;
                    }

                    final double damage = exponentialDamage.get(uuid) == null ? baseDamage : exponentialDamage.get(uuid) * exponentialDamageModifier;

                    player.damage(damage);
                    exponentialDamage.put(uuid, damage);
                });
            }
        }.runTaskTimer(plugin, 5, 5);

        this.activity = true;
    }

    @Override
    public void unregister(final KrompirPlugin plugin) {
        if (stoneCutterTask == null) {
            return;
        }

        stoneCutterTask.cancel();
        this.activity = false;
    }

    @Override
    public boolean getActivityStatus() {
        return this.activity;
    }
}
