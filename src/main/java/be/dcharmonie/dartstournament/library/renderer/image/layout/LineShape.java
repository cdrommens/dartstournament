package be.dcharmonie.dartstournament.library.renderer.image.layout;

import java.awt.Graphics2D;

/**
 *
 */
public class LineShape implements Shape {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    public LineShape(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void drawShape(Graphics2D graphics) {
        graphics.drawLine(this.x1, this.y1, this.x2, this.y2);
    }

}
