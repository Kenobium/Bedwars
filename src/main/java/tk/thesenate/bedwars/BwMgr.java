package tk.thesenate.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class BwMgr {

    private Bedwars bwPlugin;

    Boolean enabled = true;
    int mode = 1;

    final Random rand = new Random();

    final HashSet<Player> red = new HashSet<>();
    final HashSet<Player> green = new HashSet<>();
    final HashSet<Player> blue = new HashSet<>();
    final HashSet<Player> orange = new HashSet<>();
    final HashSet<Player> purple = new HashSet<>();
    final HashSet<Player> yellow = new HashSet<>();
    final HashSet<Player> black = new HashSet<>();
    final HashSet<Player> white = new HashSet<>();

    BwMgr(Bedwars bwPlugin){
        this.bwPlugin = bwPlugin;
    }


    public void assignTeams() {

        switch (mode) {
            case 1:

            case 2:
            case 3:
            case 4:
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            
        }
    }

}
