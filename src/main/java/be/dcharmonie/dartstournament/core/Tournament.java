package be.dcharmonie.dartstournament.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class Tournament {

    private final int totalNumberOfPlayers;
    private final int numberOfPlayersKnockOutPhase;
    private int numberOfPoules;
    private final List<Poule> poules = new ArrayList<>(numberOfPoules);
    private final Map<String, BracketNode> knockOut = new HashMap<>();

    //TODO : best of per knockout round configuration
    public Tournament(int totalNumberOfPlayers, int numberOfPoules, int numberOfPlayersKnockOutPhase) {
        this.totalNumberOfPlayers = totalNumberOfPlayers;
        this.numberOfPoules = numberOfPoules;
        this.numberOfPlayersKnockOutPhase = validateNumberOfPlayersKnockOutPhase(numberOfPlayersKnockOutPhase);
        this.knockOut.clear();
        generate();
        link();
    }

    public FinalNode getFinal() {
        return (FinalNode) knockOut.get(generateKey(Round.FINAL, 1));
    }

    public int getNumberOfPlayersKnockOutPhase() {
        return numberOfPlayersKnockOutPhase;
    }

    public Round getFirstRoundType() {
        return getSortedStreamFirstRoundNodes().findFirst()
                .map(BracketNode::getRound)
                .orElse(Round.FINAL);
    }
    public Stream<BracketNode> getSortedStreamFirstRoundNodes() {
        return knockOut.values().stream()
                .filter(FirstRoundNode.class::isInstance)
                .sorted();
    }
    public Map<Round, List<BracketNode>> getRoundNodesGroupedByRound() {
        return knockOut.values().stream()
                .collect(Collectors.groupingBy(BracketNode::getRound));
    }

    public Stream<BracketNode> getAllBracketNodes() {
        return knockOut.values().stream();
    }

    public void addPoule(Poule poule) {
        this.poules.add(poule);
    }

    public List<Poule> getPoules() {
        return poules;
    }

    private static int validateNumberOfPlayersKnockOutPhase(int numberOfPlayersKnockOutPhase) {
        if (numberOfPlayersKnockOutPhase < 2) {
            throw new IllegalArgumentException("There must be at least 2 players in the knock out phase.");
        }
        if (numberOfPlayersKnockOutPhase % 2 != 0) {
            throw new IllegalArgumentException("Number of players in knock out phase must be even.");
        }
        if (Round.findByRoundNumber(calculateNumberOfRounds(numberOfPlayersKnockOutPhase)) == null) {
            throw new IllegalArgumentException("Unsupported number of players");
        }
        return numberOfPlayersKnockOutPhase;
    }

    private void generate() {
        //1. generate leaf : players /2
        //2. generate rest : players / 2 tot = 2
        //3. add final
        int numberOfFirstRoundGames = numberOfPlayersKnockOutPhase / 2;

        FinalNode finalNode = new FinalNode();
        knockOut.put(generateKey(finalNode), finalNode);
        if (numberOfFirstRoundGames == 1) {
            return;
        }

        int numberOfRounds = calculateNumberOfRounds(numberOfPlayersKnockOutPhase);
        for (int i = 0; i < numberOfFirstRoundGames; i++) {
            FirstRoundNode firstRoundNode = new FirstRoundNode(Round.findByRoundNumber(numberOfRounds), i+1);
            knockOut.put(generateKey(firstRoundNode), firstRoundNode);
        }

        int restOfGames = numberOfFirstRoundGames / 2;
        int restOfPlayers = numberOfPlayersKnockOutPhase / 2;
        while ( restOfGames != 1) {
            int currentRound = calculateNumberOfRounds(restOfPlayers);
            for (int i = 0; i < restOfGames; i++) {
                RoundNode roundNode = new RoundNode(Round.findByRoundNumber(currentRound), i+1);
                knockOut.put(generateKey(roundNode), roundNode);
            }
            restOfGames = restOfGames / 2;
            restOfPlayers = restOfPlayers / 2;
        }
    }

    private void link() {
        knockOut.values().stream()
                .filter(FirstRoundNode.class::isInstance)
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
            BracketNode nextNode = knockOut.get(keyNextNode);
            currentNode.setNextBracketNode(nextNode);
            link(nextNode);
        }
        if (currentNode != null && !(currentNode instanceof FirstRoundNode)
                && currentNode.getPreviousFirstBracketNode().isEmpty() && currentNode.getPreviousSecondBracketNode().isEmpty()) {
            Round previousRound = Round.findByRoundNumber(currentNode.getRound().getRoundNumber() + 1);
            int previousFirstMatchNumber = (currentNode.getMatchNumber() * 2) - 1;
            int previousSecondMatchNumber = currentNode.getMatchNumber() * 2;
            String keyPreviousFirstNode = generateKey(previousRound, previousFirstMatchNumber);
            String keyPreviousSecondNode = generateKey(previousRound, previousSecondMatchNumber);
            BracketNode previousFirstNode = knockOut.get(keyPreviousFirstNode);
            BracketNode previousSecondNode = knockOut.get(keyPreviousSecondNode);
            currentNode.setPreviousFirstBracketNode(previousFirstNode);
            currentNode.setPreviousSecondBracketNode(previousSecondNode);
            link(previousFirstNode);
            link(previousSecondNode);
        }
    }

    private static int calculateNumberOfRounds(int numberOfPlayers) {
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
