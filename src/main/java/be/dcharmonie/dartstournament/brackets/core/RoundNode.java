package be.dcharmonie.dartstournament.brackets.core;

import java.util.Optional;

import be.dcharmonie.dartstournament.brackets.draw.Drawable;
import be.dcharmonie.dartstournament.brackets.draw.RoundNodeDraw;

/**
 *
 */
public class RoundNode implements BracketNode {

    private final Round round;
    private final int matchNumber;
    private BracketNode nextBracketNode;
    private BracketNode previousFirstBracketNode;
    private BracketNode previousSecondBracketNode;

    private final Drawable drawable = new RoundNodeDraw(this);

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

    public Drawable getDrawable() {
        return drawable;
    }
}
