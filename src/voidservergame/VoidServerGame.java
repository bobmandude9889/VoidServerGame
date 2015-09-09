package voidservergame;

import org.bukkit.plugin.java.JavaPlugin;

public class VoidServerGame extends JavaPlugin{

    public static VoidServerGame instance;
    
    @Override
    public void onEnable(){
        instance = this;
    }
    
    
    
}
