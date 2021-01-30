package tech.op65n.krompir.module.addon;

import org.bukkit.plugin.java.JavaPlugin;
import tech.op65n.krompir.KrompirPlugin;

public abstract class AddonImplementation {

    private final KrompirPlugin plugin = JavaPlugin.getPlugin(KrompirPlugin.class);
    private boolean status = false;

    public abstract void enable();

    public abstract void disable();

    public KrompirPlugin getPlugin() {
        return this.plugin;
    }

    public void setStatus(final boolean status) {
        this.status = status;

        if (status) enable(); else disable();
    }

    public boolean getStatus() {
        return this.status;
    }

}

