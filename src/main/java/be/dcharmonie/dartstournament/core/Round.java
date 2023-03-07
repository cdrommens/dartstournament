package be.dcharmonie.dartstournament.core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public enum Round {
    FINAL_32(6),
    FINAL_16(5),
    FINAL_8(4),
    QUARTER_FINAL(3),
    HALF_FINAL(2),
    FINAL(1);

    private final int roundNumber;
    private static final Map<Integer,Round> map;
    static {
        map = new HashMap<>();
        for (Round v : Round.values()) {
            map.put(v.roundNumber, v);
        }
    }

    Round(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public static Round findByRoundNumber(int number) {
        return map.get(number);
    }
}
