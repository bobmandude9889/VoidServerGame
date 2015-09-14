package voidservergame;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Util {
    
    public static final int MILISECOND = 0;
    public static final int SECOND = 1;
    public static final int MINUTE = 2;
    public static final int HOUR = 3;
    
    private static int[] conversions = {1000,60,60};
    
    public static Double convertTime(int from, int to,Double time){
        if(from > to){
            Double converted = time;
            while(from > to){
                converted *= conversions[from - 1];
                from--;
            }
            return converted;
        } else if(from < to){
            Double converted = time;
            while(from < to){
                converted /= conversions[from];
                from++;
            }
            return converted;
        } else {
            return time;
        }
    }
    
    public static boolean containsIgnoreCase(String message, String regex) {
	return message.toLowerCase().contains(regex.toLowerCase());
    }
    
    public static boolean contains(char[] array, Character character){
		for(Character c : array){
			if(c == character) return true;
		}
		return false;
	}
    
    public static String parseColors(String message) {
		String chars = "abcdefrlomn1234567890";

		for(int i = 0; i < message.length() - 1; i++){
			Character currentChar = message.charAt(i);
			Character nextChar = message.charAt(i+1);
			if(currentChar == '&' && contains(chars.toCharArray(),nextChar) && (i == 0 || message.charAt(i-1) != '&')){
				message = message.substring(0,i) + ChatColor.getByChar(nextChar) + (message.length() > i+2 ? message.substring(i+2):"");
			}
		}
		message.replace("&&", "&");
		return message;
	}
    
    public static ItemStack newItemMeta(Material material, String name, String lore, int amount) {
		name = parseColors(name);
		ItemStack is = new ItemStack(material, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		if (lore != null) {
			String[] loreA = lore.split(",");
			List<String> loreL = new ArrayList<String>();
			for (String l : loreA) {
				l = parseColors(l);
				loreL.add(l);
			}
			im.setLore(loreL);
		}
		is.setItemMeta(im);
		return is;
	}

    public static ItemStack dyeArmor(ItemStack armor, Color color){
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) armor.getItemMeta();
        armorMeta.setColor(color);
        armor.setItemMeta(armorMeta);
        return armor;
    }
    
    public static ItemStack newItemMeta(Material material, String name, String lore, int amount, short durability) {
	ItemStack is = newItemMeta(material, name, lore, amount);
    	is.setDurability(durability);
    	return is;
    }
    
    public static void registerCraftingRecipes(){
        ItemStack helmet = newItemMeta(Material.LEATHER_HELMET, "&bEmerald Helmet", null, 1);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        dyeArmor(helmet,ConfigHandler.getEmeraldArmorColor());
        ShapedRecipe helmetRecipe = new ShapedRecipe(helmet).shape("xxx","x x","   ").setIngredient('x', Material.EMERALD).setIngredient(' ', Material.AIR);
        Bukkit.getServer().addRecipe(helmetRecipe);
        
        ItemStack chest = newItemMeta(Material.LEATHER_CHESTPLATE, "&bEmerald Chestplate", null, 1);
        chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        dyeArmor(chest,ConfigHandler.getEmeraldArmorColor());
        ShapedRecipe chestRecipe = new ShapedRecipe(chest).shape("x x","xxx","xxx").setIngredient('x', Material.EMERALD).setIngredient(' ', Material.AIR);
        Bukkit.getServer().addRecipe(chestRecipe);
        
        ItemStack legs = newItemMeta(Material.LEATHER_LEGGINGS, "&bEmerald Leggings", null, 1);
        legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        dyeArmor(legs,ConfigHandler.getEmeraldArmorColor());
        ShapedRecipe legsRecipe = new ShapedRecipe(legs).shape("xxx","x x","x x").setIngredient('x', Material.EMERALD).setIngredient(' ', Material.AIR);
        Bukkit.getServer().addRecipe(legsRecipe);
        
        ItemStack boots = newItemMeta(Material.LEATHER_BOOTS, "&bEmerald Boots", null, 1);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        dyeArmor(boots,ConfigHandler.getEmeraldArmorColor());
        ShapedRecipe bootsRecipe = new ShapedRecipe(boots).shape("   ","x x","x x").setIngredient('x', Material.EMERALD).setIngredient(' ', Material.AIR);
        Bukkit.getServer().addRecipe(bootsRecipe);
    }
    
}
