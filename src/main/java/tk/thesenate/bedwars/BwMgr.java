package tk.thesenate.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class BwMgr {

    private final HashSet<String> teamNames = new HashSet<>();
    private final ArrayList<BwTeam> teams = new ArrayList<>();
    private final Random rand = new Random();

    boolean enabled;
    boolean gameOngoing = false;
    int mode = 1;

    BwMgr(Bedwars bwPlugin) {
        enabled = bwPlugin.getConfig().getBoolean("enabled");
        List<String> configList = bwPlugin.getConfig().getStringList("teams");
        configList.replaceAll(String::toLowerCase);
        teamNames.addAll(configList);
    }

    public ArrayList<BwTeam> getTeams() {
        return teams;
    }

    public void createTeams() {
        for (String teamName : teamNames) {
            teams.add(new BwTeam(teamName));
        }
    }

    public void randomlyAssignTeams(CommandSender sender) {
        HashSet<Integer> teamCounter = new HashSet<>();
        ArrayList<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        Collections.shuffle(onlinePlayers);
        switch (mode) {
            case 1:
                while (teamCounter.size() < onlinePlayers.size()) {
                    teamCounter.clear();
                    teamCounter.add(rand.nextInt(teams.size()));
                }
                ArrayList<Integer> teamCounterArray = new ArrayList<>(teamCounter);
                for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
                    BwTeam team = teams.get(teamCounterArray.get(i));
                    team.addPlayer(onlinePlayers.get(i));
                }
                break;
            case 2:
                if (onlinePlayers.size() % 2 != 0) {
                    sender.sendMessage(ChatColor.YELLOW + "There are an odd number of players; one person will be on their own.");
                }
                while (teamCounter.size() < (int) Math.ceil(onlinePlayers.size() / 2.0)) {
                    teamCounter.clear();
                    teamCounter.add(rand.nextInt(teams.size()));
                }
                teamCounterArray = new ArrayList<>(teamCounter);
                for (int i = 0; i < onlinePlayers.size(); i += 2) {
                    BwTeam team = teams.get(teamCounterArray.get(i));
                    team.addPlayer(onlinePlayers.get(i));
                    if (!(i == onlinePlayers.size() - 1)) {
                        team.addPlayer(onlinePlayers.get(i + 1));
                    }
                }
                break;
            case 3:
                if (onlinePlayers.size() % 3 != 0) {
                    sender.sendMessage(ChatColor.YELLOW + "Teams will be uneven.");
                }
                while (teamCounter.size() < (int) Math.ceil(onlinePlayers.size() / 3.0)) {
                    teamCounter.clear();
                    teamCounter.add(rand.nextInt(teams.size()));
                }
                teamCounterArray = new ArrayList<>(teamCounter);
                for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i += 3) {
                    BwTeam team = teams.get(teamCounterArray.get(i));
                    team.addPlayer(onlinePlayers.get(i));
                    if (!(i == onlinePlayers.size() - 1)) {
                        team.addPlayer(onlinePlayers.get(i + 1));
                    }
                    if (!(i == onlinePlayers.size() - 2)) {
                        team.addPlayer(onlinePlayers.get(i + 2));
                    }
                }
                break;
            case 4:
                if (onlinePlayers.size() % 4 != 0) {
                    sender.sendMessage(ChatColor.YELLOW + "Teams will be uneven.");
                }
                while (teamCounter.size() < (int) Math.ceil(onlinePlayers.size() / 4.0)) {
                    teamCounter.clear();
                    teamCounter.add(rand.nextInt(teams.size()));
                }
                teamCounterArray = new ArrayList<>(teamCounter);
                for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i += 4) {
                    BwTeam team = teams.get(teamCounterArray.get(i));
                    team.addPlayer(onlinePlayers.get(i));
                    if (!(i == onlinePlayers.size() - 1)) {
                        team.addPlayer(onlinePlayers.get(i + 1));
                    }
                    if (!(i == onlinePlayers.size() - 2)) {
                        team.addPlayer(onlinePlayers.get(i + 2));
                    }
                    if (!(i == onlinePlayers.size() - 3)) {
                        team.addPlayer(onlinePlayers.get(i + 3));
                    }
                }
                break;
        }
        for (BwTeam team : teams) {
            Bukkit.broadcastMessage(team.getPlayers().toString());
        }

    }

}
