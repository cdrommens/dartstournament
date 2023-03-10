package be.dcharmonie.dartstournament.core;

import java.util.Objects;
import java.util.Optional;

import be.dcharmonie.dartstournament.renderer.image.Drawable;
import be.dcharmonie.dartstournament.renderer.image.FirstRoundNodeDraw;

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
        return round.getDescription() + " - Game " + matchNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FirstRoundNode that)) {
            return false;
        }
        return getMatchNumber() == that.getMatchNumber() && getRound() == that.getRound();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRound(), getMatchNumber());
    }
}
