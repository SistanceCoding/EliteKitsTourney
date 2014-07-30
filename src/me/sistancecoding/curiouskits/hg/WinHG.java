package me.sistancecoding.curiouskits.hg;

import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WinHG
{
  public static SettingsManager s = SettingsManager.getInstance();
  public static String hgt = ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "HG" + ChatColor.DARK_AQUA + "] ";

  public static void win() {
    for (Player p : JoinHG.hg) {
    	Bukkit.broadcastMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + p.getName() + " has won the HG Tournament");
    	//
    	EconomyResponse r = Main.econ.depositPlayer(p, 3000);
    	if (r.transactionSuccess()){
    		p.sendMessage(ChatColor.GREEN + "Recieved 3000 credits for winning!");
    	}
    	//
        clearinventory clear = new clearinventory();
        clear.clearInv(p);
      s.getData().set("hg.isingame", Boolean.valueOf(false));
      s.getData().set("hg.canjoin", Boolean.valueOf(false));
      s.saveData();
      World w = Bukkit.getServer().getWorld(s.getData().getString("hg.end.world"));
      double x = s.getData().getDouble("hg.end.x");
      double y = s.getData().getDouble("hg.end.y");
      double z = s.getData().getDouble("hg.end.z");
      p.teleport(new Location(w, x, y, z));
      clear.clearInv(p);
      JoinHG.hg.remove(p);
    }
  }
}