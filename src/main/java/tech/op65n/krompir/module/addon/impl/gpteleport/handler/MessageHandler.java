package tech.op65n.krompir.module.addon.impl.gpteleport.handler;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tech.op65n.krompir.KrompirPlugin;

public class MessageHandler {

    public static void sendAction(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(colorize(message)));
    }

    public static void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(colorize(title), colorize(subtitle), 20, 20, 10);
    }

    public static void sendTitle(Player player, String title, String subtitle, int in, int stay, int out) {
        player.sendTitle(colorize(title), colorize(subtitle), (in * 20), (stay * 20), (out * 20));
    }

    public static void sendMessage(Player player, String... messages) {
        for (final String message : messages) {
            player.sendMessage(colorize(message));
        }
    }

    public static void sendAnnounce(String... messages) {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            for (final String message : messages) {
                player.sendMessage(colorize(message));
            }
        }
    }

    public static void sendConsole(String... messages) {
        for (final String message : messages) {
            KrompirPlugin.getInstance().getServer().getConsoleSender().sendMessage(colorize(message));
        }
    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String placeholders(String message, String claimid, String player, String name, String loc, String world, String time) {
        return message.
                replace("%claimid%", claimid).
                replace("%player%", player).
                replace("%name%", name).
                replace("%world%", world).
                replace("%time%", time).
                replace("%coordinate%", loc);
    }

}
