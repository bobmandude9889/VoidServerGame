package voidservergame;

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
	
	public static boolean get
	
}
