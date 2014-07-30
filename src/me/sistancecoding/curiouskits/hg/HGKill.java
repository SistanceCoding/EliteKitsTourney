package me.sistancecoding.curiouskits.hg;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class HGKill
implements Listener
{
	SettingsManager s = SettingsManager.getInstance();

	public void leave(Player p) {
		if (JoinHG.hg.contains(p)) {
			JoinHG.hg.remove(p);
			if (JoinHG.hg.size() < 2) {
				WinHG.win();
			}
			else
				for (Player pl : JoinHG.hg)
					pl.sendMessage(ChatColor.RED + p.getName() + " has died and there are now " + JoinHG.hg.size() + " players left.");
		}
	}


	@SuppressWarnings("unchecked")
	@EventHandler
	public void onDeath(final PlayerDeathEvent e) {
		final Player p = e.getEntity();
		Location deathspot = p.getLocation();
		if (JoinHG.hg.contains(p)) {
			@SuppressWarnings("rawtypes")
			final
			List<Entity> items = new ArrayList();
			e.getDrops().add(new ItemStack(Material.MUSHROOM_SOUP));
			e.getDrops().add(new ItemStack(Material.MUSHROOM_SOUP));
			e.getDrops().add(new ItemStack(Material.MUSHROOM_SOUP));
			e.getDrops().add(new ItemStack(Material.MUSHROOM_SOUP));
			e.getDrops().add(new ItemStack(Material.MUSHROOM_SOUP));
			e.getDrops().add(new ItemStack(Material.MUSHROOM_SOUP));
			e.getDrops().add(new ItemStack(Material.MUSHROOM_SOUP));
			e.getDrops().add(new ItemStack(Material.MUSHROOM_SOUP));
			for (ItemStack item : e.getDrops()) {
				Entity ent = Bukkit.getServer().getWorld(p.getLocation().getWorld().getName()).dropItem(deathspot, item);
				items.add(ent);
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				public void run() {
					e.getDrops().clear();
					leave(p);
					for (Entity it : items)
						it.remove();
				}
			}, 600L);
		}
	}


	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		leave(p);
	}
}