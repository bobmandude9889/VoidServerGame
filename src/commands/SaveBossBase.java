package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class SaveBossBase implements VCommand{

    @Override
    public String getName() {
        return "savebossbase";
    }

    @Override
    public String[] getAliases() {
        return null;
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;
        player.performCommand("schematic save boss_base");
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
