package world;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import voidservergame.VoidServerGame;

class WorldGenerator {
    
    public static boolean isGenerating = false;
    
    public static void generate(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv create "
                + VoidServerGame.instance.getConfig().getString("world_name")
                + " -g end");
    }
    
    public static void populateEmerald(World world, Location spawnLocation){
        isGenerating = true;
        VoidServerGame main = VoidServerGame.instance;
        int emeraldChance = main.getConfig().getInt("emerald_generate_chance");
        int replaceArea = 100;
        int replaceHeight = 50;
        int spawnX = spawnLocation.getBlockX();
        int spawnY = spawnLocation.getBlockY();
        int spawnZ = spawnLocation.getBlockZ();
        for(int x = -replaceArea; x < replaceArea; x++){
            for(int y = 0; y > -replaceHeight; y--){
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
        isGenerating = false;
    }
    
    public static void spawnBoss(World world){
        
    }
    
}
