package tk.thesenate.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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
                e.setJoinMessage(ChatColor.GOLD + e.getPlayer().getName().toString() + ChatColor.GREEN + " joined! You are the only one here.");
            } else {
                e.setJoinMessage(ChatColor.GOLD + e.getPlayer().getName().toString() + ChatColor.GREEN + " joined! There are now " + ChatColor.GOLD + numPlayers + ChatColor.GREEN + " players online.");
            }
        }

    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        //e.getEntity().getKiller()
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getClickedBlock().getType().equals(Material.OAK_TRAPDOOR)) {
            e.setCancelled(true);
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
