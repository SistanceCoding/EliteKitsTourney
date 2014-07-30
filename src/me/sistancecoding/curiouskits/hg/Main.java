package me.sistancecoding.curiouskits.hg;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	public static Plugin plugin;
	SettingsManager settings = SettingsManager.getInstance();
	public void onEnable() {
		plugin = this;
		this.settings.setup(this);
		Bukkit.getServer().getPluginManager().registerEvents(new HGKill(), this);
		HGCommands hgcmds = new HGCommands();
		getCommand("hg").setExecutor(hgcmds);
		this.settings.getData().set("hg.isingame", Boolean.valueOf(false));
		this.settings.getData().set("hg.canjoin", Boolean.valueOf(false));
		this.settings.saveData();
	    if (!setupEconomy()) {
  	      getLogger().severe(String.format("VAULT NOT ENABLED", new Object[0]));
  	      getServer().getPluginManager().disablePlugin(this);
  	      return;
  	    }
	}
	
	public static boolean setupEconomy(){
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
          return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
          return false;
        }
        econ = (Economy)rsp.getProvider();
        return econ != null;
      }
	
    public static Economy econ = null;
	
}
