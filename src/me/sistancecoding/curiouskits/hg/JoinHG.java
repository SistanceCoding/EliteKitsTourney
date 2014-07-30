package me.sistancecoding.curiouskits.hg;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class JoinHG
{
  public static SettingsManager s = SettingsManager.getInstance();
  public static ArrayList<Player> hg = new ArrayList<Player>();

  public static void join(Player p) {
    hg.add(p);
    p.sendMessage(ChatColor.GREEN + "You have joined the Tournement!");
    clearinventory clear = new clearinventory();
    clear.clearInv(p);
    World w = Bukkit.getServer().getWorld(s.getData().getString("hg.pregame.world"));
    double x = s.getData().getDouble("hg.pregame.x");
    double y = s.getData().getDouble("hg.pregame.y");
    double z = s.getData().getDouble("hg.pregame.z");
    p.teleport(new Location(w, x, y, z));
  }
}