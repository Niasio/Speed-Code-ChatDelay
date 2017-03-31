package nl.ItsCodex.ChatDelay;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Cooldowns{
	
	
    private static HashMap<UUID, Integer> COOLDOWN = new HashMap<UUID, Integer>();

    public static int get(Player PLAYER){
        if(COOLDOWN.get(PLAYER.getUniqueId()) == null){
            COOLDOWN.put(PLAYER.getUniqueId(), 0);
            return 0;
        }
        return COOLDOWN.get(PLAYER.getUniqueId());
    }

    public static boolean has(Player PLAYER){
        if(get(PLAYER) <= 0){
            return false;
        }
        PLAYER.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("Message").replaceAll("%cooldown%", "" + get(PLAYER))));
        return true;
    }
    
    public static void remove(final Player PLAYER){
    	COOLDOWN.put(PLAYER.getUniqueId(), 0);
    	
    }
    public static void add(final Player PLAYER, int VALUE){
        if(!has(PLAYER)){
            COOLDOWN.put(PLAYER.getUniqueId(), VALUE);
            new BukkitRunnable(){
                @Override
                public void run() {
                    if(get(PLAYER.getPlayer()) > 0){
                        COOLDOWN.put(PLAYER.getUniqueId(), get(PLAYER) - 1);
                    }else{
                        COOLDOWN.put(PLAYER.getUniqueId(), 0);
                        this.cancel();
                    }
                }
            }.runTaskTimer(Main.getInstance(), 20, 20);
        }
    }
}