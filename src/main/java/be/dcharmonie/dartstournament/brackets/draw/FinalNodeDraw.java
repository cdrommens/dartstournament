package be.dcharmonie.dartstournament.brackets.draw;

import java.awt.Graphics2D;

/**
 *
 */
public class FinalNodeDraw implements Drawable {

    private int x;
    private int y;

    @Override
    public void drawImage(Graphics2D graphics, int x, int y) {
        this.x = x;
        this.y = y;
        drawBox(graphics, x, y);
        graphics.drawLine(this.x - (WIDTH / 2), this.y, this.x - (WIDTH / 2) - WIDTH_LINE, this.y);
        graphics.drawLine(this.x + (WIDTH / 2), this.y, this.x + (WIDTH / 2) + WIDTH_LINE, this.y);
    }

    @Override
    public boolean isLeftBracket() {
        throw new UnsupportedOperationException("A final is always in the center");
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

}
