package me.zwrumpy.chocohills;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.zwrumpy.chocohills.machine.listener.AdvanceAnvilInventory;
import me.zwrumpy.chocohills.register.*;
import me.zwrumpy.chocohills.tools.commands.ToolCommand;
import me.zwrumpy.chocohills.tools.listener.PickaxeListener;
import me.zwrumpy.chocohills.tools.listener.TorchInteract;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ChocoHills extends JavaPlugin implements SlimefunAddon {
    private static ChocoHills instance;

    public void onEnable() {
        this.saveDefaultConfig();

        instance = this;

        /*
        if (getConfig().getBoolean("auto-update") && getDescription().getVersion().startsWith("Build")) {
            new GuizhanBuildsUpdater(this, getFile(), "SlimefunGuguProject", "Chocohills", "main", false, "zh-CN").start();
        }
        */

        new ToolSetup(this);
        new TransporterSetup(this);
        new MaterialGeneratorSetup(this);
        new MachineSetup(this);
        new ResourceSetup(this);

        registerCommands();
        registerListeners();
    }

    void registerCommands(){
        Objects.requireNonNull(this.getCommand("chtool")).setExecutor(new ToolCommand());
    }

    void registerListeners(){
        getServer().getPluginManager().registerEvents(new PickaxeListener(this), this);
        getServer().getPluginManager().registerEvents(new TorchInteract(this), this);
        getServer().getPluginManager().registerEvents(new AdvanceAnvilInventory(), this);


    }

    public static ChocoHills getInstance() {return instance;}
    public String getBugTrackerURL() {return null;}
    @Nonnull
    public JavaPlugin getJavaPlugin() {return this;}
}