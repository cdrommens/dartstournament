package be.dcharmonie.dartstournament.core;

import java.util.Objects;
import java.util.Optional;

/**
 *
 */
public interface BracketNode extends Comparable<BracketNode> {

    Round getRound();
    int getMatchNumber();
    String getDescription();

    void setPreviousFirstBracketNode(BracketNode previousFirstBracketNode);

    void setPreviousSecondBracketNode(BracketNode previousSecondBracketNode);

    void setNextBracketNode(BracketNode nextBracketNode);

    Optional<BracketNode> getNextBracketNode();

    Optional<BracketNode> getPreviousFirstBracketNode();

    Optional<BracketNode> getPreviousSecondBracketNode();

    @Override
    default int compareTo(BracketNode o) {
        if (Objects.isNull(o)) {
            return 1;
        }
        if (getRound().getRoundNumber() > o.getRound().getRoundNumber()) {
            return 1;
        }
        if (getRound().getRoundNumber() < o.getRound().getRoundNumber()) {
            return -1;
        }
        return Integer.compare(getMatchNumber(), o.getMatchNumber());
    }
}
