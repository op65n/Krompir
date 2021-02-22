package tech.op65n.krompir.module.addon.impl.stonecutter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.annotation.ModuleInformation;
import tech.op65n.krompir.module.addon.impl.stonecutter.util.StoneCutterEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ModuleInformation(
        module = "StoneCutter",
        version = "1.0.0",
        author = "Frcsty",
        description = "Stone Cutter's deal exponential damage\n &7when stepped on"
)
public final class StoneCutterImplementation extends AddonImplementation {

    private final Map<UUID, Double> exponentialDamage = new HashMap<>();
    private double baseDamage;
    private double exponentialDamageModifier;
    private BukkitTask stoneCutterTask;

    @Override
    public void enable() {
        final FileConfiguration configuration = getPlugin().getConfig();

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

                    player.damage(damage, new StoneCutterEntity());
                    exponentialDamage.put(uuid, damage);
                });
            }
        }.runTaskTimer(getPlugin(), 1, 1);
    }

    @Override
    public void disable() {
        if (stoneCutterTask == null) {
            return;
        }

        stoneCutterTask.cancel();
    }

}
