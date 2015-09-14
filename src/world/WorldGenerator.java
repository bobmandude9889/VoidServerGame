package world;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import voidservergame.ConfigHandler;
import voidservergame.Util;
import voidservergame.VoidServerGame;

public class WorldGenerator {
    
    public static boolean isGenerating = false;
    private static Thread generationThread = null;
    private static Thread bossSpawnThread = null;
    public static boolean running = true;
    
    public static void startThreads(){
        generationThread = new Thread(new Runnable(){
            @Override
            public void run() {
                while(running){
                    try {
                        isGenerating = true;
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv create "
                                + ConfigHandler.getWorldName()
                                + " end");
                        Thread.sleep(Util.convertTime(Util.MINUTE, Util.MINUTE, 1d).longValue());
                        populateEmerald(Bukkit.getWorld(ConfigHandler.getWorldName()));
                        isGenerating = false;
                        Thread.sleep(Util.convertTime(Util.HOUR, Util.MILISECOND, ConfigHandler.getRegenerationInterval()).longValue());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        generationThread.start();
        
        bossSpawnThread = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    Thread.sleep(Util.convertTime(Util.HOUR, Util.MILISECOND, ConfigHandler.getBossSpawnInterval()).longValue());
                    spawnBoss(Bukkit.getWorld(ConfigHandler.getWorldName()));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        bossSpawnThread.start();
    }
    
    @SuppressWarnings("deprecation")
	private static void deleteThread(){
            running = false;
            generationThread.stop();
            bossSpawnThread.stop();
    }
    
    public static void populateEmerald(World world){
        		VoidServerGame main = VoidServerGame.instance;
                double emeraldChance = ConfigHandler.getEmeraldChance();
                int replaceArea = 100;
                int replaceHeight = 70;
                Location center = new Location(world,0,0,0);
                int spawnX = center.getBlockX();
                int spawnY = center.getBlockY();
                int spawnZ = center.getBlockZ();
                for(int x = -replaceArea; x < replaceArea; x++){
                    for(int y = 0; y > -replaceHeight || y > 0; y--){
                        for(int z = -replaceArea; z < replaceArea; z++){
                        	Location loc = new Location(world,spawnX + x,spawnY + y,spawnZ + z);
                            if(loc.getBlock().getType().equals(Material.ENDER_STONE)){
                                double i = Math.random() * 100;
                                if(i < emeraldChance){
                                    loc.getBlock().setType(Material.EMERALD_ORE);
                                }
                            }
                        }
                    }
                }
    }
    
    public static void spawnBoss(World world){
        // TODO needs a schematic reader
    }
    
}
