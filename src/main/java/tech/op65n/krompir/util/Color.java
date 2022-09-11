package tech.op65n.krompir.util;

import org.bukkit.ChatColor;

public final class Color {

    public static String translate(final String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
