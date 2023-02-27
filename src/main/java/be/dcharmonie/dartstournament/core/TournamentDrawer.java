package be.dcharmonie.dartstournament.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 */
public class TournamentDrawer {

    private final Tournament tournament;
    private final PaperFormat selectedPaperFormat;
    private BufferedImage bufferedImage;
    private Graphics2D graphics;

    public TournamentDrawer(Tournament tournament) {
        this.tournament = tournament;
        this.selectedPaperFormat = decidePaperFormat();
        setupImage();
    }

    public BufferedImage drawImage() {
        // Draw the final
        tournament.getFinal().drawImage(graphics, selectedPaperFormat.getWidth()/2, selectedPaperFormat.getHeight()/2);
        // Draw all First Rounds
        tournament.getSortedStreamFirstRoundNodes()
                .forEach(node -> ((FirstRoundNode) node).drawImage(graphics, selectedPaperFormat.getWidth()/2, selectedPaperFormat.getHeight()/2));
        // Draw all consecutive sorted rounds
        List<Round> sortedRounds = tournament.getRoundNodesGroupedByRound().keySet().stream()
                .filter(round -> round != tournament.getFirstRoundType())
                .filter(round -> round != Round.FINAL)
                .sorted()
                .toList();

        for (Round round : sortedRounds) {
            tournament.getRoundNodesGroupedByRound().get(round)
                    .forEach(node -> ((Drawable)node).drawImage(graphics));
        }
        graphics.dispose();
        return bufferedImage;
    }

    private void setupImage() {
        bufferedImage = new BufferedImage(selectedPaperFormat.getWidth(), selectedPaperFormat.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graphics = bufferedImage.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.fillRect(0, 0, selectedPaperFormat.getWidth(), selectedPaperFormat.getHeight());
        graphics.setColor(Color.BLACK);
    }

    private PaperFormat decidePaperFormat() {
        int maxWidth = ((Drawable.WIDTH + Drawable.WIDTH_LINE)*2) +
                ((Drawable.WIDTH + Drawable.WIDTH_LINE + Drawable.WIDTH_LINE) * (tournament.getFirstRoundType().getRoundNumber()-2)*2);
        int maxHeight = (Drawable.HEIGHT/2 + Drawable.HEIGHT) * (tournament.getNumberOfPlayers()/2);

        if (PaperFormat.A4.fitOnPaper(maxWidth, maxHeight)) {
            return PaperFormat.A4;
        } else if (PaperFormat.A3.fitOnPaper(maxWidth, maxHeight)) {
            return PaperFormat.A3;
        } else {
            throw new IllegalStateException("Size of tournament is too big to fit on paper.");
        }
    }
}
