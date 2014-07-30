package me.sistancecoding.curiouskits.hg;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class clearinventory {
	public void clearInv(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		for (PotionEffect effect : p.getActivePotionEffects())
			p.removePotionEffect(effect.getType());
	}
}