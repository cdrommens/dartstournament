package be.dcharmonie.dartstournament.core;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 */
public interface Drawable {

    int WIDTH = 300;
    int HEIGHT = 60;
    int SCORE_WIDTH = 40;
    int WIDTH_LINE = 40;
    int VERTICAL_LENGTH = 120;

    void drawImage(Graphics2D graphics, int x, int y);
    default void drawImage(Graphics2D graphics) {
        drawImage(graphics, 0, 0);
    }
    boolean isLeftBracket();

    int getX();
    int getY();

    default int getWidth() {
        return WIDTH_LINE + WIDTH + WIDTH_LINE;
    }

    default int getHeight() {
        return VERTICAL_LENGTH;
    }

    default void drawBox(Graphics2D graphics, int x, int y) {
        graphics.setColor(Color.BLACK);
        //main bracket
        graphics.drawRect(x-(WIDTH/2), y-(HEIGHT/2), WIDTH, HEIGHT);
        //Division line
        graphics.drawLine(x-(WIDTH/2), y, x + (WIDTH/2), y);
        //Score box
        graphics.drawLine(x+((WIDTH/2)-SCORE_WIDTH), y-(HEIGHT/2), x+((WIDTH/2)-SCORE_WIDTH), y+(HEIGHT/2));
    }

}
