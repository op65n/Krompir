package tech.op65n.krompir.module.addon.impl.gpteleport.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import tech.op65n.krompir.module.addon.impl.gpteleport.config.Messages;
import tech.op65n.krompir.module.addon.impl.gpteleport.handler.Cooldown;
import tech.op65n.krompir.module.addon.impl.gpteleport.handler.MessageHandler;
import tech.op65n.krompir.module.addon.impl.gpteleport.handler.Warmup;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        if(e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) {
            final Warmup warmup = new Warmup();
            final Cooldown cooldown = new Cooldown();
            if (warmup.isWarmup(e.getPlayer())) {
                warmup.moved(e.getPlayer());
                cooldown.remove(e.getPlayer());
                MessageHandler.sendTitle(e.getPlayer(), Messages.WARMUP_CANCELLED, "", 0, 2, 1);
            }
        }
    }

}
