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
    public void drawImage(Graphics2D graphics, int numberOfRounds, int x, int y) {
        //drawBox(graphics, numberOfRounds, x, y);
        //predecessors line
        //graphics.drawLine(x-(WIDTH/2)-SCORE_WIDTH, y-(VERTICAL_LENGTH/4)-(numberOfRounds*getHeight()),
        //        x-(WIDTH/2)-SCORE_WIDTH, y+(VERTICAL_LENGTH/4)+(numberOfRounds*getHeight()));
        //debug
        //graphics.drawRect(x-(getWidth()/2), y-(getHeight()/2), getWidth(), getHeight());
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}
