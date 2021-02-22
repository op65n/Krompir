package tech.op65n.krompir.command.menu;

import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.mattstudios.mfgui.gui.guis.PaginatedGui;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tech.op65n.krompir.KrompirPlugin;
import tech.op65n.krompir.module.ModuleType;
import tech.op65n.krompir.module.addon.AddonImplementation;
import tech.op65n.krompir.module.annotation.ModuleInformation;
import tech.op65n.krompir.util.Color;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ConstantConditions")
public final class ModuleMenu {

    private final KrompirPlugin plugin;
    private final FileConfiguration configuration;
    private final PaginatedGui menu;

    public ModuleMenu(final KrompirPlugin plugin) {
        this.plugin = plugin;

        final File file = new File(plugin.getDataFolder(), "/menu/module-menu.yml");
        this.configuration = YamlConfiguration.loadConfiguration(file);

        this.menu = constructMenu();
    }

    private PaginatedGui constructMenu() {
        final PaginatedGui gui = new PaginatedGui(
                configuration.getInt("menu-size") / 9,
                configuration.getInt("page-size"),
                Color.translate(
                        configuration.getString("menu-title").replace("{active-modules}", String.valueOf(Arrays.stream(ModuleType.values()).map(ModuleType::getImplementation).filter(AddonImplementation::getStatus).count()))
                )
        );

        gui.setDefaultClickAction(event -> event.setCancelled(true));

        final ConfigurationSection itemsSection = configuration.getConfigurationSection("items");
        if (itemsSection == null) return null;

        for (final String key : itemsSection.getKeys(false)) {
            final ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);
            if (itemSection == null) continue;

            final Material itemMaterial = Material.valueOf(itemSection.getString("material"));
            final String display = itemSection.getString("display");
            final List<String> lore = itemSection.getStringList("lore");

            final GuiItem guiItem = new GuiItem(constructItem(
                    null,
                    itemMaterial,
                    display,
                    lore
            ), event -> {
                final Player player = (Player) event.getWhoClicked();
                final String action = itemSection.get("action") == null ? "NONE" : itemSection.getString("action", "NONE");

                switch (action.toUpperCase()) {
                    case "CLOSE":
                        gui.close(player);
                        break;
                    case "NEXT_PAGE":
                        gui.next();
                        break;
                    case "PREVIOUS_PAGE":
                        gui.previous();
                }
            });

            gui.setItem(itemSection.getIntegerList("slots"), guiItem);
        }

        final ConfigurationSection moduleSection = configuration.getConfigurationSection("module-item");
        if (moduleSection == null) return null;

        for (final ModuleType type : plugin.getModuleStatus().getModules().keySet()) {
            final AddonImplementation implementation = plugin.getModuleStatus().getModules().get(type);
            final ModuleInformation information = implementation.getClass().getAnnotation(ModuleInformation.class);

            final Material itemMaterial = Material.valueOf(moduleSection.getString("material"));
            final String display = moduleSection.getString("display")
                    .replace("{module-name}", information.module())
                    .replace("{module-version}", information.version())
                    .replace("{module-author}", information.author())
                    .replace("{module-description}", information.description())
                    .replace("{module-status}", implementation.getStatus() ? "Enabled" : "Disabled"
                    );
            final List<String> lore = moduleSection.getStringList("lore").stream().map(line -> line
                    .replace("{module-name}", information.module())
                    .replace("{module-version}", information.version())
                    .replace("{module-author}", information.author())
                    .replace("{module-description}", information.description())
                    .replace("{module-status}", implementation.getStatus() ? "Enabled" : "Disabled"))
                    .collect(Collectors.toList());

            final StringBuilder builder = new StringBuilder();
            for (final String line : moduleSection.getStringList("lore")) {
                builder.append(line
                        .replace("{module-name}", information.module())
                        .replace("{module-version}", information.version())
                        .replace("{module-author}", information.author())
                        .replace("{module-description}", information.description())
                        .replace("{module-status}", implementation.getStatus() ? "Enabled" : "Disabled")
                ).append("\n");
            }

            final GuiItem guiItem = new GuiItem(constructItem(
                    implementation,
                    itemMaterial,
                    display,
                    Arrays.asList(builder.toString().split("\n"))
            ), event -> {
                final Player player = (Player) event.getWhoClicked();
                implementation.setStatus(!implementation.getStatus());

                gui.close(player);
                plugin.getModuleStatus().updateModule(type, implementation);
            });

            gui.addItem(guiItem);
        }

        return gui;
    }

    private ItemStack constructItem(final AddonImplementation implementation, final Material material, final String display, final List<String> lore) {
        final ItemStack item = new ItemStack(material);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Color.translate(display));
        meta.setLore(lore.stream().map(Color::translate).collect(Collectors.toList()));

        if (implementation != null && implementation.getStatus()) {
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        item.setItemMeta(meta);
        return item;
    }

    public PaginatedGui getMenu() {
        return this.menu;
    }

}
