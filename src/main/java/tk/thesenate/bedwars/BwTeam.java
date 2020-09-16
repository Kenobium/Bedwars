package tk.thesenate.bedwars;

import org.bukkit.entity.Player;

import java.util.HashSet;

public class BwTeam {

    private final HashSet<Player> players = new HashSet<>();
    String name;

    public BwTeam(String name) {
        this.name = name;
    }

    HashSet<Player> getPlayers() {
        return players;
    }
    String getName() {return name;}

    void addPlayer(Player player) {
        players.add(player);
    }


}
