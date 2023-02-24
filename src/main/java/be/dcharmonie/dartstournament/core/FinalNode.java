package be.dcharmonie.dartstournament.core;

import java.util.Optional;

/**
 *
 */
public class FinalNode implements BracketNode {

    private static final Round ROUND = Round.FINAL;
    private BracketNode previousFirstBracketNode;
    private BracketNode previousSecondBracketNode;

    @Override
    public Round getRound() {
        return ROUND;
    }

    @Override
    public int getMatchNumber() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "This is the final!";
    }

    @Override
    public void setPreviousFirstBracketNode(BracketNode previousFirstBracketNode) {
        this.previousFirstBracketNode = previousFirstBracketNode;
    }

    @Override
    public void setPreviousSecondBracketNode(BracketNode previousSecondBracketNode) {
        this.previousSecondBracketNode = previousSecondBracketNode;
    }

    @Override
    public void setNextBracketNode(BracketNode nextBracketNode) {
        throw new UnsupportedOperationException("The final node doesn't have a next game");
    }

    @Override
    public Optional<BracketNode> getNextBracketNode() {
        return Optional.empty();
    }

    @Override
    public Optional<BracketNode> getPreviousFirstBracketNode() {
        return Optional.ofNullable(previousFirstBracketNode);
    }

    @Override
    public Optional<BracketNode> getPreviousSecondBracketNode() {
        return Optional.ofNullable(previousSecondBracketNode);
    }
}
