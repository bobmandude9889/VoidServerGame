package voidservergame;

import commands.VCommand;
import handlers.MobSpawnListener;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class Vars {
    
    public static List<VCommand> commands;
    public static List<Listener> listeners;
    
    public static void init(){
        commands = new ArrayList<VCommand>();
        listeners = new ArrayList<Listener>();
        
        listeners.add(new MobSpawnListener());
        
        
        for(Listener lis : listeners){
            Bukkit.getPluginManager().registerEvents(lis, VoidServerGame.instance);
        }
    }
    
}
