package tech.op65n.krompir.module.addon.impl.gpteleport.handler;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tech.op65n.krompir.KrompirPlugin;
import tech.op65n.krompir.module.addon.impl.gpteleport.config.Config;
import tech.op65n.krompir.module.addon.impl.gpteleport.config.Messages;

import java.util.HashMap;

public class Warmup {

    private static HashMap<String, Long> warmup = new HashMap<>();

    public void addWarmup(Player player, Location loc) {
        if(!warmup.containsKey(player.getUniqueId().toString())) {
            warmup.put(player.getUniqueId().toString(), (System.currentTimeMillis() / 1000));

            new BukkitRunnable() {
                @Override
                public void run() {
                    if(!warmup.containsKey(player.getUniqueId().toString())) {
                        this.cancel();
                        return;
                    }

                    if(getTime(player) <= 0) {
                        MessageHandler.sendTitle(player, "", "", 0, 0, 0);
                        warmup.remove(player.getUniqueId().toString());
                        player.teleport(loc);
                        this.cancel();
                        return;
                    }

                    MessageHandler.sendTitle(player,
                            MessageHandler.placeholders(Messages.WARMUP_TITLE, null, player.getDisplayName(), null, null, null, getTime(player).toString()),
                            MessageHandler.placeholders(Messages.WARMUP_SUBTITLE, null, player.getDisplayName(), null, null, null, getTime(player).toString()),
                            0, 1, 0);
                }

            }.runTaskTimer(KrompirPlugin.getInstance(), 0L, 2L);
        }
    }

    public void moved(Player player) {
        warmup.remove(player.getUniqueId().toString());
    }

    public boolean isWarmup(Player player) {
        if(warmup.containsKey(player.getUniqueId().toString())) { return true; }

        return false;
    }

    private Long getTime(Player player) {
        if(warmup.containsKey(player.getUniqueId().toString())) {
            final long timeSeconds = Config.WARMUP_TIME - ((System.currentTimeMillis() / 1000) - warmup.get(player.getUniqueId().toString()));
            return timeSeconds;
        }

        return 0L;
    }
}
