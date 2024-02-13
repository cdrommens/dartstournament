package be.dcharmonie.dartstournament.library.renderer.image.layout;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 */
public class BracketBoxShape implements Shape {

    private final int x;
    private final int y;

    public BracketBoxShape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void drawShape(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        //main bracket
        graphics.drawRect(x-(WIDTH/2), y-(HEIGHT/2), WIDTH, HEIGHT);
        //Division line
        graphics.drawLine(x-(WIDTH/2), y, x + (WIDTH/2), y);
        //Score box
        graphics.drawLine(x+((WIDTH/2)-SCORE_WIDTH), y-(HEIGHT/2), x+((WIDTH/2)-SCORE_WIDTH), y+(HEIGHT/2));
    }
}
