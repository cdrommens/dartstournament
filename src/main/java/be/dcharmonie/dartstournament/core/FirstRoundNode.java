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
    private boolean isLeft;

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
    public void drawImage(Graphics2D graphics, int x, int y) {
        int numberOfPlayersInRound = (int)Math.pow(2,getRound().getRoundNumber());
        int numberOfMatchesInRound = numberOfPlayersInRound/2;
        int numberOfMatchesInHalfRound = numberOfMatchesInRound/2;
        int numberOfMatchesInQuarterRound = numberOfMatchesInHalfRound/2;

        if (getMatchNumber() <= numberOfMatchesInHalfRound) {
            drawImageLeft(graphics, numberOfMatchesInQuarterRound, x, y);
        } else {
            drawImageRight(graphics, numberOfMatchesInHalfRound + numberOfMatchesInQuarterRound, x, y);
        }
    }

    @Override
    public boolean isLeftBracket() {
        return this.isLeft;
    }

    private void drawImageLeft(Graphics2D graphics, int medio, int x, int y) {
        this.x = x - getWidth() - ((getRound().getRoundNumber() - 2) * getWidth());
        if (getMatchNumber() <= medio) {
            this.y = y - (getHeight() / 2) - ((medio - getMatchNumber()) * getHeight());
        } else {
            this.y = y + (getHeight() / 2) + ((getMatchNumber() - medio - 1) * getHeight());
        }
        this.isLeft = true;
        graphics.drawLine(this.x + (WIDTH / 2), this.y, this.x + (WIDTH / 2) + WIDTH_LINE, this.y);
        drawBox(graphics, this.x, this.y);
    }

    private void drawImageRight(Graphics2D graphics, int medio, int x, int y) {
        this.x = x + getWidth() + ((getRound().getRoundNumber() - 2) * getWidth());
        if (getMatchNumber() <= medio) {
            this.y = y - (getHeight() / 2) - ((medio - getMatchNumber()) * getHeight());
        } else {
            this.y = y + (getHeight() / 2) + ((getMatchNumber() - medio - 1) * getHeight());
        }
        this.isLeft = false;
        graphics.drawLine(this.x - (WIDTH / 2), this.y, this.x - (WIDTH / 2) - WIDTH_LINE, this.y);
        drawBox(graphics, this.x, this.y);
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
