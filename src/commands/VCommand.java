package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public interface VCommand {
    public String getName();
	public String[] getAliases();
	public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args);
	public boolean onlyPlayers();
	public boolean hasPermission();
	public Permission getPermission();
}
