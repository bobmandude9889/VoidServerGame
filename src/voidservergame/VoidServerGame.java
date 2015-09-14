package voidservergame;

import commands.VCommand;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import world.WorldGenerator;

public class VoidServerGame extends JavaPlugin{

    public static VoidServerGame instance;
    
    @Override
    public void onEnable(){
        instance = this;
        Vars.init();
        WorldGenerator.startThreads();
    }
    
    @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		System.out.println(cmd.getName());
		for (VCommand vC : Vars.commands) {
			List<String> aliases = new ArrayList<String>();
			aliases.add(vC.getName());
			if (vC.getAliases() != null) {
				for (String alias : vC.getAliases()) {
					aliases.add(alias);
				}
			}
			if (containsIgnoreCase(aliases, cmd.getName())) {
				if (vC.hasPermission() && !sender.hasPermission(vC.getPermission())) {
					sender.sendMessage(ChatColor.RED + "You do not have permission for that command.");
					return true;
				}
				if (vC.onlyPlayers() && !(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + vC.getName() + " is only for players!");
					return false;
				}
				try {
					vC.onCommand(sender, cmd, label, args);
				} catch (ArrayIndexOutOfBoundsException e) {
					sender.sendMessage(ChatColor.RED + "Invalid arguments!");
				}
				return true;
			}
		}
		sender.sendMessage(ChatColor.RED + "Command not found");
		return false;
	}
    
        public boolean containsIgnoreCase(List<String> list, String s) {
		for (String string : list) {
			if (string.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}
        
}