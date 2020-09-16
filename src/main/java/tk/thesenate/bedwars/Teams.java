package tk.thesenate.bedwars;

import java.util.HashMap;
import java.util.Map;

public enum Teams {

    RED(0, "red"),
    GREEN(1, "green"),
    BLUE(2, "blue"),
    ORANGE(3, "orange"),
    PURPLE(4, "purple"),
    YELLOW(5, "yellow"),
    BLACK(6, "black"),
    WHITE(7, "yellow");

    private static final Map<Integer, Teams> idMap = new HashMap<>();
    private static final Map<String, Teams> teamMap = new HashMap<>();
    int id;
    String team;

    static {
        for (Teams team : Teams.values()) {
            idMap.put(team.getId(), team);
            teamMap.put(team.getTeam(), team);
        }
    }

    Teams(int id, String team) {
        this.id = id;
        this.team = team;
    }

    int getId() {return id;}
    String getTeam() {return team;}

    static Teams getTeamById(int id) {
        return idMap.get(id);
    }

    static Teams getTeamByName(String team) {
        return teamMap.get(team);
    }

}
