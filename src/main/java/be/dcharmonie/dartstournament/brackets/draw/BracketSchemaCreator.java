package be.dcharmonie.dartstournament.brackets.draw;

import java.awt.image.BufferedImage;
import java.util.List;

import be.dcharmonie.dartstournament.brackets.core.BracketNode;
import be.dcharmonie.dartstournament.brackets.core.Round;
import be.dcharmonie.dartstournament.brackets.core.Tournament;
import be.dcharmonie.dartstournament.brackets.draw.layout.BracketSchemaPaperPrinter;

/**
 *
 */
public class BracketSchemaCreator {

    private final Tournament tournament;

    private BufferedImage image;

    public BracketSchemaCreator(Tournament tournament) {
        this.tournament = tournament;
    }

    public void createSchema(BracketSchemaPaperPrinter printer) {
        assembleTournament(printer);
        tournament.getAllBracketNodes()
                .map(BracketNode::getDrawable)
                .forEach(printer::print);
        this.image = printer.getImage();
    }

    public BufferedImage getImage() {
        return image;
    }

    private void assembleTournament(BracketSchemaPaperPrinter printer) {
        // Draw the final
        tournament.getFinal().getDrawable().assembleImage(printer.getPaperFormat().getWidth()/2, printer.getPaperFormat().getHeight()/2);
        // Draw all First Rounds
        tournament.getSortedStreamFirstRoundNodes()
                .map(BracketNode::getDrawable)
                .forEach(drawable -> drawable.assembleImage(printer.getPaperFormat().getWidth()/2, printer.getPaperFormat().getHeight()/2));
        // Draw all consecutive sorted rounds
        List<Round> sortedRounds = tournament.getRoundNodesGroupedByRound().keySet().stream()
                .filter(round -> round != tournament.getFirstRoundType())
                .filter(round -> round != Round.FINAL)
                .sorted()
                .toList();

        for (Round round : sortedRounds) {
            tournament.getRoundNodesGroupedByRound().get(round)
                    .forEach(node -> node.getDrawable().assembleImage(0,0));
        }
    }




}
