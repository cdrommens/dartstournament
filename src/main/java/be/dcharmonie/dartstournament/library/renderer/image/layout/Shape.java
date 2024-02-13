package be.dcharmonie.dartstournament.library.renderer.image.layout;

import java.awt.Graphics2D;

/**
 *
 */
public interface Shape {

    int WIDTH = 300;
    int HEIGHT = 60;
    int SCORE_WIDTH = 40;
    int WIDTH_LINE = 40;
    int VERTICAL_LENGTH = 120;
    int BOX_WIDTH = WIDTH_LINE + WIDTH + WIDTH_LINE;
    int BOX_HEIGHT = VERTICAL_LENGTH;

    void drawShape(Graphics2D graphics);

}
