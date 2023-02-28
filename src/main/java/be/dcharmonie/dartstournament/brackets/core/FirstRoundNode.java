package be.dcharmonie.dartstournament.brackets.core;

import java.util.Optional;

import be.dcharmonie.dartstournament.brackets.draw.Drawable;
import be.dcharmonie.dartstournament.brackets.draw.FirstRoundNodeDraw;

/**
 *
 */
public class FirstRoundNode implements BracketNode {

    private final Round round;
    private final int matchNumber;
    private BracketNode nextBracketNode;

    private final Drawable drawable = new FirstRoundNodeDraw(this);

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

    public Drawable getDrawable() {
        return drawable;
    }
}
