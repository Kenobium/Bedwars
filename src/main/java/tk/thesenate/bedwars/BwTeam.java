package tk.thesenate.bedwars;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class BwTeam {

    private final HashSet<Player> players = new HashSet<>();
    private Location generator;
    private Location spawnPoint;
    String name;

    public BwTeam(String name) {
        this.name = name;
    }

    HashSet<Player> getPlayers() {
        return players;
    }
    String getName() {
        return name;
    }
    Location getGenerator() {
        return generator;
    }
    Location getSpawnPoint() {
        return spawnPoint;
    }

    void addPlayer(Player player) {
        players.add(player);
    }
    void setGenerator(Location loc) {
        generator = loc;
    }
    void setSpawnPoint(Location loc) {
        spawnPoint = loc;
    }

}
