package me.aurel2108.freeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class FreezeListener implements Listener {
	
	private final Freeze plugin;
	
	public FreezeListener(Freeze plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		if(plugin.isFrozen(event.getPlayer()))
			event.setTo(event.getFrom());
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.isCancelled() == false && event.getPlayer() != null && plugin.isFrozen(event.getPlayer()))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(event.isCancelled() == false && event.getPlayer() != null && plugin.isFrozen(event.getPlayer()))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent event)
	{
		if(event.isCancelled() == false && event.getPlayer() != null && plugin.isFrozen(event.getPlayer()))
			event.setCancelled(true);
	}
	
	@EventHandler	
	public void onBlockPlace(BlockPlaceEvent event)
	{
		if(event.isCancelled() == false && event.getPlayer() != null && plugin.isFrozen(event.getPlayer()))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event)
	{
		if(plugin.isFrozen(event.getPlayer()))
			event.setTo(event.getFrom());
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event)
	{
		if(event.isCancelled() == false && event.getPlayer() != null && event.getMessage().startsWith("/") == false && plugin.getConfig().getBoolean("speak") == false && plugin.isFrozen(event.getPlayer()))
			event.setCancelled(true);

	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
	{
		if(event.isCancelled() == false && event.getPlayer() != null && plugin.getConfig().getBoolean("commands") == false)
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event)
	{
		if(event.getEntity() != null && event.getEntity() instanceof Player && !event.isCancelled() && plugin.getConfig().getBoolean("damage"))
		{
			Player player = (Player)event.getEntity();
			if(plugin.isFrozen(player))
			{
				event.setDamage(0);
				event.setCancelled(true);
			}
		}
	}

}