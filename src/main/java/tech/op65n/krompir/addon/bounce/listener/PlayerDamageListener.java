package tech.op65n.krompir.addon.bounce.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class PlayerDamageListener implements Listener {

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

    public void addPlayer(final UUID uuid) {
        this.fallDamage.add(uuid);
    }

    public void removePlayer(final UUID uuid) {
        this.fallDamage.remove(uuid);
    }

}
