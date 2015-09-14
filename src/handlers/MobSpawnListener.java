package handlers;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import voidservergame.ConfigHandler;

public class MobSpawnListener implements Listener{
    
    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e){
        EntityType[] mobs = ConfigHandler.getMobs();
        if(e.getLocation().getWorld().getName().equals(ConfigHandler.getWorldName()) && e.getEntityType().equals(EntityType.ENDERMAN)){
            e.setCancelled(true);
            Location location = e.getLocation();
            Random generator = new Random();
            LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, mobs[generator.nextInt(mobs.length)]);
            EntityEquipment equipment = entity.getEquipment();
            if(generator.nextInt(100) <= ConfigHandler.getArmorChance())
                equipment.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            
            if(generator.nextInt(100) <= ConfigHandler.getArmorChance())
                equipment.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            
            if(generator.nextInt(100) <= ConfigHandler.getArmorChance())
                equipment.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            
            if(generator.nextInt(100) <= ConfigHandler.getArmorChance())
                equipment.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            
            if(generator.nextInt(100) <= ConfigHandler.getSwordChance())
                equipment.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
        }
    }
    
}
