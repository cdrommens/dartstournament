package be.dcharmonie.dartstournament.core;

import java.util.Optional;

/**
 *
 */
public interface BracketNode {

    Round getRound();
    int getMatchNumber();
    String getDescription();

    void setPreviousFirstBracketNode(BracketNode previousFirstBracketNode);

    void setPreviousSecondBracketNode(BracketNode previousSecondBracketNode);

    void setNextBracketNode(BracketNode nextBracketNode);

    Optional<BracketNode> getNextBracketNode();

    Optional<BracketNode> getPreviousFirstBracketNode();

    Optional<BracketNode> getPreviousSecondBracketNode();
}
