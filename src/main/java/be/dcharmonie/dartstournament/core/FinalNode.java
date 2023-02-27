package be.dcharmonie.dartstournament.core;

import java.awt.Graphics2D;
import java.util.Optional;

/**
 *
 */
public class FinalNode implements BracketNode, Drawable {

    private static final Round ROUND = Round.FINAL;
    private BracketNode previousFirstBracketNode;
    private BracketNode previousSecondBracketNode;

    private int x;
    private int y;

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

    @Override
    public void drawImage(Graphics2D graphics, int numberOfRounds, int x, int y) {
        this.x = x;
        this.y = y;
        drawBox(graphics, numberOfRounds, x, y);
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
