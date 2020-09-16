package tk.thesenate.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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

    }

}
