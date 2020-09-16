package tk.thesenate.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BwCmd implements CommandExecutor {

    private final BwMgr bwMgr;
    private final Bedwars bwPlugin;

    BwCmd(BwMgr bwMgr, Bedwars bwPlugin) {
        this.bwMgr = bwMgr;
        this.bwPlugin = bwPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("bw")) {
            if (args.length == 0) {
                sendInvalid(sender);
            } else {
                if (args[0].equalsIgnoreCase("enable")) {
                    if (args.length > 1) {
                        sendInvalid(sender);
                    } else {
                        bwMgr.enabled = true;
                        sender.sendMessage(ChatColor.GREEN + "Bedwars plugin enabled.");
                    }
                } else if (args[0].equalsIgnoreCase("disable")) {
                    if (args.length > 1) {
                        sendInvalid(sender);
                    } else {
                        bwMgr.enabled = false;
                        sender.sendMessage(ChatColor.RED + "Bedwars plugin disabled.");
                    }

                } else if (args[0].equalsIgnoreCase("start")) {
                    if (bwMgr.enabled) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            countDown(player, 5);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Bedwars plugin is disabled!");
                    }
                    if (args.length == 2) {
                        if (args[1].equals("1") || args[1].equals("2") || args[1].equals("3") || args[1].equals("4")) {
                            bwMgr.mode = Integer.parseInt(args[1]);
                        } else {
                            sendInvalid(sender);
                        }
                    }
                    bwMgr.createTeams();
                    bwMgr.assignTeams();


                } else {
                    sendInvalid(sender);
                }
            }

        }
        return false;
    }

    public void sendInvalid(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Usage: ");
    }

    public void countDown(Player player, int length) {
        new BukkitRunnable() {

            int i = length;

            @Override
            public void run() {
                if (i != 0) {
                    player.sendTitle(ChatColor.GREEN + "Game starts in " + i, null, -1, -1, -1);
                    i--;
                } else {
                    player.resetTitle();
                    cancel();
                }

            }

        }.runTaskTimer(bwPlugin, 0, 20L);
    }
}
