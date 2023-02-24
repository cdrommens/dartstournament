package be.dcharmonie.dartstournament.core;

import java.util.Optional;

/**
 *
 */
public class FirstRoundNode implements BracketNode {

    private final Round round;
    private final int matchNumber;
    private BracketNode nextBracketNode;

    public FirstRoundNode(Round round, int matchNumber) {
        this.round = round;
        this.matchNumber = matchNumber;
    }

    @Override
    public Round getRound() {
        return round;
    }

    @Override
    public int getMatchNumber() {
        return matchNumber;
    }

    @Override
    public String getDescription() {
        return round.toString() + " - Game " + matchNumber;
    }

    @Override
    public void setPreviousFirstBracketNode(BracketNode previousFirstBracketNode) {
        throw new UnsupportedOperationException("A first round node can't have previous rounds.");
    }

    @Override
    public void setPreviousSecondBracketNode(BracketNode previousSecondBracketNode) {
        throw new UnsupportedOperationException("A first round node can't have previous rounds.");
    }

    @Override
    public void setNextBracketNode(BracketNode nextBracketNode) {
        this.nextBracketNode = nextBracketNode;
    }

    @Override
    public Optional<BracketNode> getNextBracketNode() {
        return Optional.ofNullable(nextBracketNode);
    }

    @Override
    public Optional<BracketNode> getPreviousFirstBracketNode() {
        return Optional.empty();
    }

    @Override
    public Optional<BracketNode> getPreviousSecondBracketNode() {
        return Optional.empty();
    }
}
