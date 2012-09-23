package com.github.CubieX.Plague;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class PlagueConfigHandler
{
    
    private final Plague pInst;
    private final Logger log;
    private FileConfiguration config;

    //Constructor
    public PlagueConfigHandler(Plague pInst, Logger log)
    {
        this.pInst = pInst;
        this.log = log;       
        
        initConfig();        
    }
    
    private void initConfig()
    {
        pInst.saveDefaultConfig(); //creates a copy of the provided config.yml in the plugins data folder, if it does not exist
        config = pInst.getConfig(); //re-reads config out of memory. (Reads the config from file only, when invoked the first time!)
    }
    
    private void saveConfig() //saves the config to disc (needed when entries have been altered via the plugin in-game)
    {
        // get and set values here!
        pInst.saveConfig();   
    }
    
    //reloads the config from disc (used if user made manual changes to the config.yml file)
    public void reloadConfig(CommandSender sender)
    {
        pInst.reloadConfig();
        config = pInst.getConfig(); // new assignment neccessary when returned value is assigned to a variable or static field(!)
        pInst.ReloadLocale();
        
        sender.sendMessage("[" + ChatColor.GREEN + "Info" + ChatColor.WHITE + "] " + ChatColor.GREEN + pInst.getDescription().getName() + " " + pInst.getDescription().getVersion() + " reloaded!");       
    }
}
