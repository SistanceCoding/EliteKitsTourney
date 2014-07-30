package me.sistancecoding.curiouskits.hg;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HGCommands
  implements CommandExecutor
{
  SettingsManager settings = SettingsManager.getInstance();

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "Console can't use CuriousKits commands!");
      return true;
    }
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("hg")) {
      if (args.length == 0) {
        p.sendMessage(ChatColor.RED + "Please use '/hg help' for a list of hg commands.");
        return true;
      }
      if (args[0].equalsIgnoreCase("join")) {
          if ((this.settings.getData().getBoolean("hg.isingame")) || (!this.settings.getData().getBoolean("hg.canjoin"))) {
              p.sendMessage(ChatColor.RED + "You can't join HG right now.");
              return true;
            }
          if(JoinHG.hg.contains(p)) {
        	  p.sendMessage(ChatColor.RED + ChatColor.ITALIC.toString() + "You are already in hg....");
          }
          JoinHG.join(p);
          return true;
      }
      if (args[0].equalsIgnoreCase("start")) {
        if (!p.hasPermission("hgkits.hg.start")) {
          p.sendMessage(ChatColor.RED + "You can't start that!");
          return true;
        }
        if ((this.settings.getData().getBoolean("hg.isingame")) || (this.settings.getData().getBoolean("hg.canjoin"))) {
          p.sendMessage(ChatColor.RED + "You can't start a hg game right now.");
          return true;
        }
        StartHG.start();
        p.sendMessage(ChatColor.GREEN + "Successfully started hg.");
        return true;
      }
      if (args[0].equalsIgnoreCase("setend")) {
        if (!p.hasPermission("hgkits.hg.set")) return true;
        this.settings.getData().set("hg.end.world", p.getLocation().getWorld().getName());
        this.settings.getData().set("hg.end.x", Double.valueOf(p.getLocation().getX()));
        this.settings.getData().set("hg.end.y", Double.valueOf(p.getLocation().getY()));
        this.settings.getData().set("hg.end.z", Double.valueOf(p.getLocation().getZ()));
        this.settings.saveData();
        p.sendMessage(ChatColor.GREEN + "Set end");
        return true;
      }
      if (args[0].equalsIgnoreCase("setspawn")) {
        if (!p.hasPermission("hgkits.hg.set")) return true;
        this.settings.getData().set("hg.spawn.world", p.getLocation().getWorld().getName());
        this.settings.getData().set("hg.spawn.x", Double.valueOf(p.getLocation().getX()));
        this.settings.getData().set("hg.spawn.y", Double.valueOf(p.getLocation().getY()));
        this.settings.getData().set("hg.spawn.z", Double.valueOf(p.getLocation().getZ()));
        this.settings.saveData();
        p.sendMessage(ChatColor.GREEN + "Set spawn");
        return true;
      }
      if (args[0].equalsIgnoreCase("setpregame")) {
        if (!p.hasPermission("hgkits.hg.set")) return true;
        this.settings.getData().set("hg.pregame.world", p.getLocation().getWorld().getName());
        this.settings.getData().set("hg.pregame.x", Double.valueOf(p.getLocation().getX()));
        this.settings.getData().set("hg.pregame.y", Double.valueOf(p.getLocation().getY()));
        this.settings.getData().set("hg.pregame.z", Double.valueOf(p.getLocation().getZ()));
        this.settings.saveData();
        p.sendMessage(ChatColor.GREEN + "Set pregame");
        return true;
      }
      if (args[0].equalsIgnoreCase("help")) {
        p.sendMessage(ChatColor.YELLOW + "/hg start - Starts hg.");
        p.sendMessage(ChatColor.YELLOW + "/hg join - Joins hg.");
        p.sendMessage(ChatColor.YELLOW + "/hg setspawn - Sets hg's spawn.");
        p.sendMessage(ChatColor.YELLOW + "/hg setpregame - Sets hg's pregame point.");
        p.sendMessage(ChatColor.YELLOW + "/hg setend - Sets hg's endpoint.");
        return true;
      }
      return true;
    }
    return true;
  }
}