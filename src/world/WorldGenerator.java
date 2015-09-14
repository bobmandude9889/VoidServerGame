package world;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import voidservergame.VoidServerGame;

class WorldGenerator {
    
    public static boolean isGenerating = false;
    private static Thread generationThread = null;
    
    public static void generate(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv create "
                + VoidServerGame.instance.getConfig().getString("world_name")
                + " -g end");
    }
    
    public static void populateEmerald(final World world){
        isGenerating = true;
        generationThread = new Thread(new Runnable(){
        	@Override
        	public void run() {
        		VoidServerGame main = VoidServerGame.instance;
                int emeraldChance = main.getConfig().getInt("emerald_generate_chance");
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
                isGenerating = false;
                deleteThread();
        	}
        });
        generationThread.start();
    }
    
    @SuppressWarnings("deprecation")
	private static void deleteThread(){
    	generationThread.stop();
    }
    
    public static void spawnBoss(World world){
        
    }
    
}
