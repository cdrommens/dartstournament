package be.dcharmonie.dartstournament.core;

import java.util.HashMap;
import java.util.Map;

//See: https://en.wikipedia.org/wiki/Single-elimination_tournament
public enum Round {
    FINAL_32(6, "Laatste 64"),
    FINAL_16(5, "16de Finale"),
    FINAL_8(4, "8ste Finale"),
    QUARTER_FINAL(3, "Kwartfinale"),
    HALF_FINAL(2, "Halve Finale"),
    FINAL(1, "Finale");

    private final int roundNumber;
    private final String description;
    private static final Map<Integer,Round> map;
    static {
        map = new HashMap<>();
        for (Round v : Round.values()) {
            map.put(v.roundNumber, v);
        }
    }

    Round(int roundNumber, String description) {
        this.roundNumber = roundNumber;
        this.description = description;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getDescription() {
        return description;
    }

    public static Round findByRoundNumber(int number) {
        return map.get(number);
    }
}
