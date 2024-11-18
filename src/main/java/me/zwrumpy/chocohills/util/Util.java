package me.zwrumpy.chocohills.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Util {
    @Nonnull
    public static Map<Enchantment, Integer> getEnchants(@Nonnull ConfigurationSection section) {
        Map<Enchantment, Integer> enchants = new HashMap<>();
        for (String path : section.getKeys(false)) {
            Enchantment e = enchantmentByPath(path);
            if (e != null) {
                int level = section.getInt(path);
                if (level > 0 && level <= Short.MAX_VALUE) {
                    enchants.put(e, level);
                } else if (level != 0) {
                    section.set(path, 0);
                }
            }
        }
        return enchants;
    }

    public static ItemStack getDisplayItem(@Nonnull ItemStack output) {
        ItemMeta meta = output.getItemMeta();
        List<String> lore;
        if (meta.hasLore()) {
            lore = meta.getLore();
        }
        else {
            lore = new ArrayList<>();
        }
        lore.add("");
        lore.add(ChatColor.GREEN + "-------------------");
        lore.add(ChatColor.GREEN + "\u21E8 Click to craft");
        lore.add(ChatColor.GREEN + "-------------------");
        output.setItemMeta(meta);
        return output;
    }

    @Nullable
    private static Enchantment enchantmentByPath(@Nonnull String path) {
        switch (path) {
            case "sharpness":
                return Enchantment.SHARPNESS;
            case "smite":
                return Enchantment.SMITE;
            case "bane-of-arthropods":
                return Enchantment.BANE_OF_ARTHROPODS;
            case "efficiency":
                return Enchantment.EFFICIENCY;
            case "protection":
                return Enchantment.PROTECTION;
            case "fire-aspect":
                return Enchantment.FIRE_ASPECT;
            case "fortune":
                return Enchantment.FORTUNE;
            case "looting":
                return Enchantment.LOOTING;
            case "silk-touch":
                return Enchantment.SILK_TOUCH;
            case "thorns":
                return Enchantment.THORNS;
            case "aqua-affinity":
                return Enchantment.AQUA_AFFINITY;
            case "power":
                return Enchantment.POWER;
            case "flame":
                return Enchantment.FLAME;
            case "infinity":
                return Enchantment.INFINITY;
            case "punch":
                return Enchantment.PUNCH;
            case "feather-falling":
                return Enchantment.FEATHER_FALLING;
            case "unbreaking":
                return Enchantment.UNBREAKING;
            default:
                return null;
        }
    }
}

