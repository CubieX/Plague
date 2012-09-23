package com.github.CubieX.Plague;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlagueEntityListener implements Listener
{
    private final Plague pInst;
    private final Logger log;
    private final PlagueMessageHandler msgHandler;
    
    //Constructor
    public PlagueEntityListener(Plague pInst, Logger log, PlagueMessageHandler msgHandler)
    {
        this.pInst = pInst;
        this.log = log;
        this.msgHandler = msgHandler;
        
        pInst.getServer().getPluginManager().registerEvents(this, pInst);
    }
  /*
   Event Priorities

There are six priorities in Bukkit

    EventPriority.HIGHEST
    EventPriority.HIGH
    EventPriority.NORMAL
    EventPriority.LOW
    EventPriority.LOWEST
    EventPriority.MONITOR 

They are called in the following order

    EventPriority.LOWEST 
    EventPriority.LOW
    EventPriority.NORMAL
    EventPriority.HIGH
    EventPriority.HIGHEST
    EventPriority.MONITOR 
    
    All Events can be cancelled. Plugins with a high prio for the event can cancel or uncancel earlier issued lower prio plugin actions.
    MONITOR level should only be used, if the outcome of an event is NOT altered from this plugin and if you want to have the final state of the event.
    If the outcome gets changed (i.e. event gets cancelled, uncancelled or actions taken that can lead to it), a prio from LOWEST to HIGHEST must be used!
    
    The option "ignoreCancelled" if set to "true" says, that the plugin will not get this event if it has been cancelled beforehand from another plugin.
  */
    
  //================================================================================================
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true) // event has MONITOR priority and will be skipped if it has been cancelled before
    public void onPlayerJoin(PlayerJoinEvent event)
    {
      // duration is measured in server ticks, amp says, how often the effect is applied during the duration
      // Conversion (if server does not lag): duration / 20 = duration in seconds
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,2000,4)); 
    }
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true) // event has MONITOR priority and will be skipped if it has been cancelled before
    public void onBlockBreak(BlockBreakEvent event)
    {        
        Object[] msgArgs = {
                new Integer(5),
                msgHandler.GetMsg("yourSing"),                
                new Integer(22),
                msgHandler.GetMsg("days")
            };
        
      event.getPlayer().sendMessage(msgHandler.GetMsg("msg",msgArgs));
    }
    
}
