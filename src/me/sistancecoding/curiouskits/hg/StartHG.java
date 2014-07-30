package me.sistancecoding.curiouskits.hg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StartHG
{
  public static SettingsManager s = SettingsManager.getInstance();
  public static int counter;
  public static int HGtask;
  static String p = ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "HG" + ChatColor.DARK_AQUA + "] ";

  public static void start()	 {
    counter = 60;
    s.getData().set("hg.canjoin", Boolean.valueOf(true));
    s.saveData();
    Bukkit.getServer().broadcastMessage(p + ChatColor.GOLD + "HG is starting in 1 minute! Type '/HG join' to join!");
    HGtask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable()
    {
      public void run() {
        if ((StartHG.counter == 45) || (StartHG.counter == 30) || (StartHG.counter == 15) || (StartHG.counter == 5) || 
          (StartHG.counter == 4) || (StartHG.counter == 3) || (StartHG.counter == 2) || 
          (StartHG.counter == 1)) {
          Bukkit.getServer().broadcastMessage(
            StartHG.p + ChatColor.GOLD + "HG is starting in " + StartHG.counter + 
            " second(s)! Type '/HG join' to join!");
        }
        if (StartHG.counter == 0) {
          for (Player p : JoinHG.hg) {
            StartHG.s.getData().set("hg.isingame", Boolean.valueOf(true));
            StartHG.s.getData().set("hg.canjoin", Boolean.valueOf(false));
            StartHG.s.saveData();
            World w = Bukkit.getServer().getWorld(StartHG.s.getData().getString("hg.spawn.world"));
            double x = StartHG.s.getData().getDouble("hg.spawn.x");
            double y = StartHG.s.getData().getDouble("hg.spawn.y");
            double z = StartHG.s.getData().getDouble("hg.spawn.z");
            p.teleport(new Location(w, x, y, z));
            p.setHealth(20.0D);
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
			if (JoinHG.hg.size() < 3) {
				JoinHG.hg.remove(p);
				p.sendMessage(ChatColor.RED + ChatColor.ITALIC.toString() + "HG failed to start because there were not enough players.");
				return;
			}
			
            p.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD));
			for (int i = 0; i < 35; i++)
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
            p.sendMessage(ChatColor.YELLOW + 
              "HG has begun. Your goal is to be the last one standing so go KILL!!!!");
            Bukkit.getServer().getScheduler().cancelTask(StartHG.HGtask);
          }
        }
        StartHG.counter -= 1;
      }
    }
    , 20L, 20L);
  }
}