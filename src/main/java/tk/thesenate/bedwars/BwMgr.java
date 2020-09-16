package tk.thesenate.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class BwMgr {

    private final HashSet<String> teamNames = new HashSet<>();
    private final ArrayList<BwTeam> teams = new ArrayList<>();
    private final Random rand = new Random();
    private final ArrayList<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

    Boolean enabled;
    int mode = 1;

    BwMgr(Bedwars bwPlugin){
        enabled = bwPlugin.getConfig().getBoolean("enabled");
        List<String> configList = bwPlugin.getConfig().getStringList("teams");
        configList.replaceAll(String::toLowerCase);
        teamNames.addAll(configList);
    }

    public void createTeams() {
        for (String teamName : teamNames) {
            teams.add(new BwTeam(teamName));
        }
    }

    public void assignTeams() {
        HashSet<Integer> teamCounter = new HashSet<>();
        switch (mode) {
            case 1:
                while (teamCounter.size() < Bukkit.getOnlinePlayers().size()) {
                    teamCounter.add(rand.nextInt(8));
                }
                ArrayList<Integer> teamCounterArray = new ArrayList<>(teamCounter);
                for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
                    BwTeam team = teams.get(teamCounterArray.get(i));
                    team.addPlayer(onlinePlayers.get(i));
                }
            case 2:
            case 3:
            case 4:
        }
        for (BwTeam team : teams) {
            Bukkit.broadcastMessage(team.getPlayers().toString());
        }

    }

}
