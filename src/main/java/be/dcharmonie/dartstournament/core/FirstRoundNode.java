package be.dcharmonie.dartstournament.core;

import java.awt.Graphics2D;
import java.util.Optional;

/**
 *
 */
public class FirstRoundNode implements BracketNode, Drawable {

    private final Round round;
    private final int matchNumber;
    private BracketNode nextBracketNode;

    private int x;
    private int y;

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

    @Override
    public void drawImage(Graphics2D graphics, int numberOfRounds, int x, int y) {
        this.x = x - (getWidth()/2) - ((getRound().getRoundNumber()-1)*getWidth());
        int numberOfPlayersInRound = (int)Math.pow(2,getRound().getRoundNumber());
        int numberOfMatchesInRound = numberOfPlayersInRound/2;
        int numberOfMatchesInHalfRound = numberOfMatchesInRound/2;
        this.y = getMatchNumber() <= numberOfMatchesInHalfRound
                ? y - (getHeight() / 2) - ((numberOfMatchesInHalfRound - getMatchNumber()) * getHeight())
                : y + (getHeight() / 2) + ((getMatchNumber() - numberOfMatchesInHalfRound) * getHeight());
        drawBox(graphics, numberOfRounds, this.x, this.y);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
