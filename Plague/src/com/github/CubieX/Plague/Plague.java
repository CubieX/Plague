package com.github.CubieX.Plague;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.CubieX.Plague.PlagueConfigHandler;

public class Plague extends JavaPlugin
{
    private Plague pInst = null;
    private static final Logger log = Logger.getLogger("Minecraft");
    
    private PlagueConfigHandler configHandler = null;
    private PlagueCommandHandler comHandler = null;    
    private PlagueEntityListener eListener = null;
    private PlagueMessageHandler msgHandler = null;
    private PlagueSchedulerHandler schedHandler = null;

    @Override
    public void onEnable()
    {               
        log.info(getDescription().getName() + " version " + getDescription().getVersion() + " is enabled!");
        
        this.pInst = this;
        configHandler = new PlagueConfigHandler(this, log);                
        msgHandler = new PlagueMessageHandler(this, log);
        eListener = new PlagueEntityListener(this, log, msgHandler);
        comHandler = new PlagueCommandHandler(this, log, configHandler);
        getCommand("plague").setExecutor(comHandler);
        schedHandler = new PlagueSchedulerHandler(this);
        
        schedHandler.timerTest();
    }

    @Override
    public void onDisable()
    {        
        comHandler = null;
        configHandler = null;
        eListener = null;
        msgHandler = null;
        log.info(getDescription().getName() + " version " + getDescription().getVersion() + " is disabled!");       
    }    
    
    public void ReloadLocale()
    {
        msgHandler.ReloadLocale();
    }

}
