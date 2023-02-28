package be.dcharmonie.dartstournament.brackets.draw;

import java.util.List;

import be.dcharmonie.dartstournament.brackets.core.BracketNode;
import be.dcharmonie.dartstournament.brackets.core.Round;
import be.dcharmonie.dartstournament.brackets.core.Tournament;
import be.dcharmonie.dartstournament.brackets.draw.layout.TournamentLayout;

/**
 *
 */
public class TournamentDrawer extends TournamentLayout {

    private final Tournament tournament;

    public TournamentDrawer(Tournament tournament) {
        super(tournament);
        this.tournament = tournament;
    }

    public void drawImage() {
        // Draw the final
        tournament.getFinal().getDrawable().drawImage(graphics, paperFormat.getWidth()/2, paperFormat.getHeight()/2);
        // Draw all First Rounds
        tournament.getSortedStreamFirstRoundNodes()
                .map(BracketNode::getDrawable)
                .forEach(drawable -> drawable.drawImage(graphics, paperFormat.getWidth()/2, paperFormat.getHeight()/2));
        // Draw all consecutive sorted rounds
        List<Round> sortedRounds = tournament.getRoundNodesGroupedByRound().keySet().stream()
                .filter(round -> round != tournament.getFirstRoundType())
                .filter(round -> round != Round.FINAL)
                .sorted()
                .toList();

        for (Round round : sortedRounds) {
            tournament.getRoundNodesGroupedByRound().get(round)
                    .forEach(node -> node.getDrawable().drawImage(graphics));
        }
        graphics.dispose();
    }




}
