package hu.Pdani.FMCCitizens;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import net.citizensnpcs.Citizens;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class FMCCitizensPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        if(!hasSkript()){
            getLogger().log(Level.SEVERE,"Skript plugin not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if(!hasCitizens()){
            getLogger().log(Level.SEVERE,"Citizens plugin not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        SkriptAddon addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("hu.Pdani.FMCCitizens", "registry");
            addon.loadClasses("hu.Pdani.FMCCitizens", "manager");
        } catch (Exception e) {
            e.printStackTrace();
        }
        getLogger().log(Level.INFO,"The addon has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO,"The addon has been disabled!");
    }

    private boolean hasSkript() {
        Plugin pl = getServer().getPluginManager().getPlugin("Skript");
        if (pl == null) {
            return false;
        }
        return (pl instanceof Skript);
    }

    private boolean hasCitizens() {
        Plugin pl = getServer().getPluginManager().getPlugin("Citizens");
        if (pl == null) {
            return false;
        }
        return (pl instanceof Citizens);
    }

}
