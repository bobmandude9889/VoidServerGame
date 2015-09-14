package commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import voidservergame.ConfigHandler;

public class SetBossSpawn implements VCommand{

    @Override
    public String getName() {
        return "setbossspawn";
    }

    @Override
    public String[] getAliases() {
        return null;
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        if(location.getWorld().getName().equals(ConfigHandler.getWorldName())){
            ConfigHandler.setBossSpawnLocation(player.getLocation());
            player.sendMessage(ChatColor.GREEN + "Set boss spawn location");
        } else {
            player.sendMessage(ChatColor.RED + "You are not in the correct world");
        }
    }

    @Override
    public boolean onlyPlayers() {
        return true;
    }

    @Override
    public boolean hasPermission() {
        return true;
    }

    @Override
    public Permission getPermission() {
        return new Permission("voidgame.setbossspawn");
    }
    
}