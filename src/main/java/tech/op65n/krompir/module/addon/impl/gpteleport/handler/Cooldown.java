package tech.op65n.krompir.module.addon.impl.gpteleport.handler;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tech.op65n.krompir.KrompirPlugin;
import tech.op65n.krompir.module.addon.impl.gpteleport.config.Config;
import tech.op65n.krompir.module.addon.impl.gpteleport.config.Messages;

import java.util.HashMap;

public class Cooldown {
    private static HashMap<String, Long> cooldown = new HashMap<>();

    public boolean addCooldown(Player player) {
        if (!cooldown.containsKey(player.getUniqueId().toString())) {
            cooldown.put(player.getUniqueId().toString(), (System.currentTimeMillis() / 1000));

            new BukkitRunnable() {
                @Override
                public void run() {
                    cooldown.remove(player.getUniqueId().toString());
                }
            }.runTaskLater(KrompirPlugin.getInstance(), (20L * Config.COOLDOWN_TIME));

            return true;
        }

        MessageHandler.sendMessage(player, MessageHandler.placeholders(Messages.COOLDOWN_COOLDOWN, null, player.getDisplayName(), null, null, null, getTime(player)));

        return false;
    }

    private String getTime(Player player) {
        final long timeSeconds = Config.COOLDOWN_TIME - ((System.currentTimeMillis() / 1000) - cooldown.get(player.getUniqueId().toString()));

        return Long.toString(timeSeconds);
    }

    public void remove(Player player) {
        if (cooldown.containsKey(player.getUniqueId().toString())) {
            cooldown.remove(player.getUniqueId().toString());
        }
    }

}
