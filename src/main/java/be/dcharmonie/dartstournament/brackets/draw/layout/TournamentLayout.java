package be.dcharmonie.dartstournament.brackets.draw.layout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.xhtmlrenderer.util.FSImageWriter;

import be.dcharmonie.dartstournament.brackets.core.Tournament;
import be.dcharmonie.dartstournament.brackets.draw.Drawable;
import be.dcharmonie.dartstournament.brackets.draw.PaperFormat;

/**
 *
 */
public class TournamentLayout {
    protected final PaperFormat paperFormat;
    private BufferedImage bufferedImage;
    protected Graphics2D graphics;

    public TournamentLayout(Tournament tournament) {
        paperFormat = decidePaperFormat(tournament);
        setupImage();
    }

    public void saveToDisk(String filename) throws IOException {
        FSImageWriter imageWriter = new FSImageWriter();
        String outputFolderImg = System.getProperty("user.home") + File.separator + filename + ".png";
        imageWriter.write(bufferedImage, outputFolderImg);
    }

    private static PaperFormat decidePaperFormat(Tournament tournament) {
        int maxWidth = ((Drawable.WIDTH + Drawable.WIDTH_LINE)*2) +
                ((Drawable.WIDTH + Drawable.WIDTH_LINE + Drawable.WIDTH_LINE) * (tournament.getFirstRoundType().getRoundNumber()-2)*2);
        int maxHeight = (Drawable.HEIGHT/2 + Drawable.HEIGHT) * (tournament.getNumberOfPlayers()/2);
        return PaperFormat.selectPaperFormat(maxWidth, maxHeight);
    }

    private void setupImage() {
        bufferedImage = new BufferedImage(paperFormat.getWidth(), paperFormat.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graphics = bufferedImage.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.fillRect(0, 0, paperFormat.getWidth(), paperFormat.getHeight());
        graphics.setColor(Color.BLACK);
    }
}
