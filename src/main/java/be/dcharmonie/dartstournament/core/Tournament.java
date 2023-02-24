package be.dcharmonie.dartstournament.core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Tournament {

    private final int numberOfPlayers;
    private final Map<String, BracketNode> tournamentMap = new HashMap<>();

    public Tournament(int numberOfPlayers) {
        if (Round.findByRoundNumber(calculateNumberOfRounds(numberOfPlayers)) == null) {
            throw new IllegalArgumentException("Unsupported number of players");
        }
        this.numberOfPlayers = numberOfPlayers;
        generate();
        link();
    }

    public FinalNode getFinal() {
        return (FinalNode) tournamentMap.get(generateKey(Round.FINAL, 1));
    }

    private void generate() {
        //1. generate leaf : players /2
        //2. generate rest : players / 2 tot = 2
        //3. add final
        int numberOfFirstRoundGames = numberOfPlayers / 2;
        int numberOfRounds = calculateNumberOfRounds(numberOfPlayers);
        for (int i = 0; i < numberOfFirstRoundGames; i++) {
            FirstRoundNode firstRoundNode = new FirstRoundNode(Round.findByRoundNumber(numberOfRounds), i+1);
            tournamentMap.put(generateKey(firstRoundNode), firstRoundNode);
        }

        int restOfGames = numberOfFirstRoundGames / 2;
        int restOfPlayers = numberOfPlayers / 2;
        while ( restOfGames != 1) {
            int currentRound = calculateNumberOfRounds(restOfPlayers);
            for (int i = 0; i < restOfGames; i++) {
                RoundNode roundNode = new RoundNode(Round.findByRoundNumber(currentRound), i+1);
                tournamentMap.put(generateKey(roundNode), roundNode);
            }
            restOfGames = restOfGames / 2;
            restOfPlayers = restOfPlayers / 2;
        }
        FinalNode finalNode = new FinalNode();
        tournamentMap.put(generateKey(finalNode), finalNode);
    }

    private void link() {
        tournamentMap.values().stream()
                .filter(node -> node instanceof FirstRoundNode)
                .forEach(this::link);
    }

    private void link(BracketNode currentNode) {
        if (currentNode != null && !(currentNode instanceof FinalNode) && currentNode.getNextBracketNode().isEmpty()) {
            Round nextRound = Round.findByRoundNumber(currentNode.getRound().getRoundNumber() - 1);
            int nextMatchNumber = 0;
            if (currentNode.getMatchNumber() % 2 == 0) {
                nextMatchNumber = currentNode.getMatchNumber() / 2;
            } else {
                nextMatchNumber = (currentNode.getMatchNumber() + 1) / 2;
            }
            String keyNextNode = generateKey(nextRound, nextMatchNumber);
            BracketNode nextNode = tournamentMap.get(keyNextNode);
            currentNode.setNextBracketNode(nextNode);
            System.out.println("linked "+keyNextNode);
            link(nextNode);
        }
        if (currentNode != null && !(currentNode instanceof FirstRoundNode)
                && currentNode.getPreviousFirstBracketNode().isEmpty() && currentNode.getPreviousSecondBracketNode().isEmpty()) {
            Round previousRound = Round.findByRoundNumber(currentNode.getRound().getRoundNumber() + 1);
            int previousFirstMatchNumber = (currentNode.getMatchNumber() * 2) - 1;
            int previousSecondMatchNumber = currentNode.getMatchNumber() * 2;
            String keyPreviousFirstNode = generateKey(previousRound, previousFirstMatchNumber);
            String keyPreviousSecondNode = generateKey(previousRound, previousSecondMatchNumber);
            BracketNode previousFirstNode = tournamentMap.get(keyPreviousFirstNode);
            BracketNode previousSecondNode = tournamentMap.get(keyPreviousSecondNode);
            currentNode.setPreviousFirstBracketNode(previousFirstNode);
            currentNode.setPreviousSecondBracketNode(previousSecondNode);
            link(previousFirstNode);
            link(previousSecondNode);
        }
    }

    private int calculateNumberOfRounds(int numberOfPlayers) {
        int numberOfRounds = 1;
        int players = numberOfPlayers;
        while (players != 2) {
            numberOfRounds++;
            players = players / 2;
        }
        return numberOfRounds;
    }

    private String generateKey(BracketNode node) {
        return generateKey(node.getRound(),node.getMatchNumber());
    }

    private String generateKey(Round round, int matchNumber) {
        return round.name() + "_GAME" + matchNumber;
    }

}
