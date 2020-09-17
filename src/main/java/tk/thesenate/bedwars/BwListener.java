package tk.thesenate.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class BwListener implements Listener {

    private final BwMgr bwMgr;
    private final Bedwars bwPlugin;

    BwListener(BwMgr bwMgr, Bedwars bwPlugin) {
        this.bwMgr = bwMgr;
        this.bwPlugin = bwPlugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {

        if (bwMgr.enabled) {
            int numPlayers = Bukkit.getOnlinePlayers().size();
            if (numPlayers == 1) {
                e.setJoinMessage(ChatColor.GOLD + e.getPlayer().getName() + ChatColor.GREEN + " joined! You are the only one here.");
            } else {
                e.setJoinMessage(ChatColor.GOLD + e.getPlayer().getName() + ChatColor.GREEN + " joined! There are now " + ChatColor.GOLD + numPlayers + ChatColor.GREEN + " players online.");
            }
        }

    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        EntityDamageEvent.DamageCause damageCause = e.getEntity().getLastDamageCause().getCause();
        Entity killer = e.getEntity().getKiller();
        String playerName = e.getEntity().getPlayer().getName();
        switch (damageCause) {
            case FALL:
                if (killer == null) {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " fell to their death.");
                } else {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " was knocked off a cliff by " + ChatColor.GOLD + killer.getName() + ChatColor.GREEN + ".");
                }
                break;
            case LAVA:
                if (killer == null) {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " swam in lava.");
                } else {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " was knocked into lava by " + ChatColor.GOLD + killer.getName() + ChatColor.GREEN + ".");
                }
                break;
            case FIRE:
            case FIRE_TICK:
                if (killer == null) {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " was sauteed.");
                } else {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " was sauteed by " + ChatColor.GOLD + killer.getName() + ChatColor.GREEN + ".");
                }
                break;
            case DROWNING:
                if (killer == null) {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " drowned.");
                } else {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " was sent to Davey Jones's locker by " + ChatColor.GOLD + killer.getName() + ChatColor.GREEN + ".");
                }
                break;
            case VOID:
                if (killer == null) {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " fell into the void.");
                } else {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " was knocked into the void by " + ChatColor.GOLD + killer.getName() + ChatColor.GREEN + ".");
                }
                break;
            case MAGIC:
                e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " killed by a magical witch.");
                break;
            case POISON:
                e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " poisoned by a witch.");
                break;
            case ENTITY_ATTACK:
                if (killer == null) {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " was zombified.");
                } else {
                    e.setDeathMessage(ChatColor.GOLD + playerName + ChatColor.GREEN + " was beaned by " + ChatColor.GOLD + killer.getName() + ChatColor.GREEN + ".");
                }
                break;

        }

    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        Location teamSpawn = e.getPlayer().getWorld().getSpawnLocation();
        for (BwTeam t : bwMgr.getTeams()) {
            if (t.getPlayers().contains(e.getPlayer())) {
                teamSpawn = t.getSpawnPoint();
            }
        }
        e.setRespawnLocation(teamSpawn);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        ArrayList<Material> disallowedBlocks = new ArrayList<>(Arrays.asList(Material.OAK_TRAPDOOR, Material.RED_BED));
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && disallowedBlocks.contains(e.getClickedBlock().getType())) {
            e.setCancelled(true);
        }
        if (bwMgr.marking && !Marker.markedFlag && e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && bwMgr.markingTool.equals(e.getItem())) {
            bwMgr.getTeams().get(Marker.currentIndex - 1).setSpawnPoint(e.getClickedBlock().getLocation());
            Marker.markedFlag = true;
        }

    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        ArrayList<Material> protectedBlocks = new ArrayList<>(Arrays.asList(Material.IRON_DOOR, Material.RED_BED, Material.STONE_PRESSURE_PLATE, Material.BROWN_CARPET));
        if (protectedBlocks.contains(e.getBlock().getType())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {
        e.setCancelled(true);
    }


}
