package be.dcharmonie.dartstournament.renderer.image.layout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import be.dcharmonie.dartstournament.renderer.image.Drawable;

/**
 *
 */
public class BracketSchemaPaperPrinter {
    protected final PaperFormat paperFormat;
    private BufferedImage bufferedImage;
    protected Graphics2D graphics;

    public BracketSchemaPaperPrinter(int numberOfPlayers, int numberOfRounds) {
        paperFormat = decidePaperFormat(numberOfPlayers, numberOfRounds);
        setupImage();
    }

    public void print(Drawable drawable) {
        drawable.getShapes().forEach(shape -> shape.drawShape(graphics));
    }

    public PaperFormat getPaperFormat() {
        return this.paperFormat;
    }

    public BufferedImage getImage() {
        graphics.dispose();
        return bufferedImage;
    }

    private static PaperFormat decidePaperFormat(int numberOfPlayers, int numberOfRounds) {
        int maxWidth = ((Shape.WIDTH + Shape.WIDTH_LINE)*2) +
                ((Shape.WIDTH + Shape.WIDTH_LINE + Shape.WIDTH_LINE) * (numberOfRounds-2)*2);
        int maxHeight = (Shape.HEIGHT/2 + Shape.HEIGHT) * (numberOfPlayers/2);
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
