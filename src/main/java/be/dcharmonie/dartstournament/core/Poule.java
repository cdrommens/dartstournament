package be.dcharmonie.dartstournament.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Poule {
    private final String name;
    private final int bestOf;
    private final List<Player> players = new ArrayList<>();
    private List<PouleGame> games = new ArrayList<>();

    private static final int MAX_NUMBER_OF_PLAYERS = 6;
    private static final Map<Integer, List<GameRule>> RULES = Map.of(
            3, List.of(
                    new GameRule(1,2,3),
                    new GameRule(2,3,1),
                    new GameRule(3,1,2)),
            4, List.of(
                    new GameRule(1,2,3),
                    new GameRule(3,4,1),
                    new GameRule(2,3,4),
                    new GameRule(4,1,2),
                    new GameRule(2,4,3),
                    new GameRule(1,3,4)),
            5, List.of(
                    new GameRule(1,2,5),
                    new GameRule(4,5,1),
                    new GameRule(2,3,4),
                    new GameRule(4,1,3),
                    new GameRule(3,5,2),
                    new GameRule(2,4,5),
                    new GameRule(1,3,4),
                    new GameRule(5,2,1),
                    new GameRule(3,4,2),
                    new GameRule(5,1,3)),
            6, List.of(
                    new GameRule(1,2,4),
                    new GameRule(6,3,5),
                    new GameRule(4,5,1),
                    new GameRule(3,1,2),
                    new GameRule(2,4,6),
                    new GameRule(5,6,3),
                    new GameRule(3,2,4),
                    new GameRule(1,5,2),
                    new GameRule(6,4,1),
                    new GameRule(5,3,6),
                    new GameRule(4,1,5),
                    new GameRule(2,6,3),
                    new GameRule(3,4,1),
                    new GameRule(5,2,4),
                    new GameRule(6,1,2))
    );

    public Poule(String name, int bestOf) {
        this.name = name;
        this.bestOf = bestOf;
    }

    public void addPlayer(Player player) {
        if (players.size()+1 > MAX_NUMBER_OF_PLAYERS) {
            throw new IllegalArgumentException("Max number of players exceeded.");
        }
        this.players.add(player);
    }

    public String getName() {
        return name;
    }

    public int getBestOf() {
        return bestOf;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<PouleGame> getGames() {
        if (games.isEmpty()) {
            RULES.get(players.size()).forEach(gameRule -> games.add(new PouleGame(
                    players.get(gameRule.player1()-1),
                    players.get(gameRule.player2()-1),
                    players.get(gameRule.writer()-1))));
        }
        return games;
    }

    private record GameRule(int player1, int player2, int writer) {}
}
