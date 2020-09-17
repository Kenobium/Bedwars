package tk.thesenate.bedwars;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Marker extends BukkitRunnable {

    private final BwMgr bwMgr;
    private final Player player;
    //change these later
    static int currentIndex = 0;
    static boolean markedFlag = true;

    public Marker(BwMgr bwMgr, CommandSender player) {
        this.bwMgr = bwMgr;
        this.player = (Player) player;
    }

    @Override
    public void run() {
        if (bwMgr.marking && markedFlag) {
            if (currentIndex < bwMgr.getTeams().size()) {
                player.sendMessage(ChatColor.AQUA + "Please mark the spawn point for " + bwMgr.getTeams().get(currentIndex).getName() + " base.");
                currentIndex++;
                markedFlag = false;
            } else {
                player.sendMessage(ChatColor.GREEN + "Marking complete! Your bases are set up.");
                bwMgr.marking = false;
                player.getInventory().remove(bwMgr.markingTool);
                this.cancel();
            }

        }
    }

}
