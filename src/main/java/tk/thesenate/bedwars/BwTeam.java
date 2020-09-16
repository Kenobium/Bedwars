package tk.thesenate.bedwars;

import org.bukkit.entity.Player;

import java.util.HashSet;

public class BwTeam {

    final HashSet<Player> players = new HashSet<>();
    String name;

    public BwTeam(String name) {
        this.name = name;
    }

}
