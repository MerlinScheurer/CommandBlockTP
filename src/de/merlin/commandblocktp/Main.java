package de.merlin.commandblocktp;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;;

/**
 * Created by Merlin on 08.02.2017.
 */
public class Main extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable(){
        getLogger().info("Starting CommandBlockTp!");
        commandKit kit = new commandKit();
        this.getCommand("cmdtp").setExecutor(kit);
        this.getCommand("cmdtpset").setExecutor(kit);
    }

    @Override
    public void onDisable(){
        getLogger().info("Stopping CommandBlockTp!");
    }
}

