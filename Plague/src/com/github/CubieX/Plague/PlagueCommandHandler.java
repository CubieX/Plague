package com.github.CubieX.Plague;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlagueCommandHandler implements CommandExecutor
{
    private final Plague pInst;
    private final PlagueConfigHandler configHandler;
    private final Logger log;
    
    //Constructor
    public PlagueCommandHandler(Plague pInst, Logger log, PlagueConfigHandler configHandler)
    {
        this.pInst = pInst;
        this.configHandler = configHandler;
        this.log = log;    
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player player = null;
        if (sender instanceof Player) 
        {
            player = (Player) sender;
        }
        
        if (cmd.getName().equalsIgnoreCase("plague"))
        { // If the player typed /plague then do the following... (can be run from console also)
            if (args.length == 0)
            { //no arguments, so help will be displayed
                return false;
            }
            if (args.length==1)
            {
                if (args[0].equalsIgnoreCase("version")) // argument 0 is given and correct
                {            
                    sender.sendMessage(ChatColor.GREEN + "This server is running " + pInst.getDescription().getName() + " " + pInst.getDescription().getVersion());                    
                    return true;
                }    
                if (args[0].equalsIgnoreCase("reload")) // argument 0 is given and correct
                {            
                    if(sender.hasPermission("plague.admin"))
                    {
                        configHandler.reloadConfig(sender);                        
                        return true;
                    }
                    else
                    {
                        sender.sendMessage(ChatColor.RED + "You du not have sufficient permission to reload " + pInst.getDescription().getName() + "!");
                    }
                }
            }
            else
            {
                sender.sendMessage(ChatColor.YELLOW + "Ungültige Anzahl Argumente.");
            }                

        }         
        return false; // if false is returned, the help for the command stated in the plugin.yml will be displayed to the player
    }
}
