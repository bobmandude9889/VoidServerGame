package voidservergame;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class ConfigHandler {

	public static double getEmeraldChance(){
            return VoidServerGame.instance.getConfig().getDouble("emerald_generate_chance");
	}
	
	public static String getWorldName(){
            return VoidServerGame.instance.getConfig().getString("world_name");
	}
	
	public static boolean getRegenerateOnEnable(){
            return VoidServerGame.instance.getConfig().getBoolean("regenerate_on_enable");
	}
	
	public static Double getRegenerationInterval(){
            return VoidServerGame.instance.getConfig().getDouble("regeneration_interval");
        }
	
        public static Double getBossSpawnInterval(){
            return VoidServerGame.instance.getConfig().getDouble("boss_spawn_interval");
        }
        
        public static Location getBossSpawnLocation(){
            String serializedLocation = VoidServerGame.instance.getConfig().getString("boss_spawn_location");
            String[] stringValues = serializedLocation.split(",");
            Location location = null;
            try{
                double[] doubleValues = new double[3];
                for(int i = 0; i < 3; i ++){
                    doubleValues[i] = Double.parseDouble(stringValues[i]);
                }
                location = new Location(Bukkit.getWorld(getWorldName()),doubleValues[0],doubleValues[1],doubleValues[2]);
                return location;
            } catch(NumberFormatException e){
                System.out.println("Exception while reading location. Boss will be spawned at 0,70,0");
                return new Location(Bukkit.getWorld(getWorldName()),0,70,0);
            }
        }
        
        public static void setBossSpawnLocation(Location location){
            String serializedLocation = location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
            VoidServerGame.instance.getConfig().set("boss_spawn_location", serializedLocation);
        }
        
        public static Double getArmorChance(){
            return VoidServerGame.instance.getConfig().getDouble("armor_chance");
        }
        
        public static Double getSwordChance(){
            return VoidServerGame.instance.getConfig().getDouble("sword_chance");
        }
        
        public static EntityType[] getMobs(){
            List<String> mobNames = VoidServerGame.instance.getConfig().getStringList("mobs");
            EntityType[] mobs = new EntityType[mobNames.size()];
            for(int i = 0; i < mobNames.size(); i++){
                mobs[i] = EntityType.valueOf(mobNames.get(i));
            }
            return mobs;
        }
        
        public static Color getEmeraldArmorColor(){
            int r = VoidServerGame.instance.getConfig().getInt("emerald_armor_color.red");
            int g = VoidServerGame.instance.getConfig().getInt("emerald_armor_color.green");
            int b = VoidServerGame.instance.getConfig().getInt("emerald_armor_color.blue");
            return Color.fromRGB(r, g, b);
        }
        
}
