package be.dcharmonie.dartstournament.core;

import java.awt.Graphics2D;
import java.util.Optional;

/**
 *
 */
public class RoundNode implements BracketNode, Drawable {

    private final Round round;
    private final int matchNumber;
    private BracketNode nextBracketNode;
    private BracketNode previousFirstBracketNode;
    private BracketNode previousSecondBracketNode;

    private int x;
    private int y;
    private boolean isLeft;

    public RoundNode(Round round, int matchNumber) {
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
        this.previousFirstBracketNode = previousFirstBracketNode;
    }

    @Override
    public void setPreviousSecondBracketNode(BracketNode previousSecondBracketNode) {
        this.previousSecondBracketNode = previousSecondBracketNode;
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
        return Optional.ofNullable(previousFirstBracketNode);
    }

    @Override
    public Optional<BracketNode> getPreviousSecondBracketNode() {
        return Optional.ofNullable(previousSecondBracketNode);
    }

    @Override
    public void drawImage(Graphics2D graphics, int x, int y) {
        if (getPreviousFirstBracketNode().isEmpty() || getPreviousSecondBracketNode().isEmpty()) {
            throw new IllegalStateException("Trying to draw an incomplete node.");
        }
        Drawable previousFirstNode = (Drawable) getPreviousFirstBracketNode().get();
        Drawable previousSecondNode = (Drawable) getPreviousSecondBracketNode().get();
        if (previousFirstNode.isLeftBracket()) {
            calculateImageLeft(previousFirstNode, previousSecondNode);
        } else {
            calculateImageRight(previousFirstNode, previousSecondNode);
        }
        graphics.drawLine(this.x - (WIDTH / 2), this.y, this.x - (WIDTH / 2) - WIDTH_LINE, this.y);
        graphics.drawLine(this.x + (WIDTH / 2), this.y, this.x + (WIDTH / 2) + WIDTH_LINE, this.y);
        drawConnectionLine(graphics, previousFirstNode, previousSecondNode);
        drawBox(graphics, this.x, this.y);
    }

    @Override
    public boolean isLeftBracket() {
        return this.isLeft;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    private void calculateImageLeft(Drawable previousFirstNode, Drawable previousSecondNode) {
        this.x = previousFirstNode.getX() + getWidth();
        this.y = ((previousSecondNode.getY() - previousFirstNode.getY()) / 2) + previousFirstNode.getY();
        this.isLeft = true;
    }

    private void calculateImageRight(Drawable previousFirstNode, Drawable previousSecondNode) {
        this.x = previousFirstNode.getX() - getWidth();
        this.y = ((previousSecondNode.getY() - previousFirstNode.getY()) / 2) + previousFirstNode.getY();
        this.isLeft = false;
    }

    private void drawConnectionLine(Graphics2D graphics, Drawable previousFirstNode, Drawable previousSecondNode) {
        if (previousSecondNode.isLeftBracket()) {
            graphics.drawLine(
                    this.x - (WIDTH / 2) - WIDTH_LINE, previousFirstNode.getY(),
                    this.x - (WIDTH / 2) - WIDTH_LINE, previousSecondNode.getY());
        } else {
            graphics.drawLine(
                    this.x + (WIDTH / 2) + WIDTH_LINE, previousFirstNode.getY(),
                    this.x + (WIDTH / 2) + WIDTH_LINE, previousSecondNode.getY());
        }
    }
}
