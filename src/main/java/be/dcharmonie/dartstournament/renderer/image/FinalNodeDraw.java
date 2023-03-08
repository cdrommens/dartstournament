package be.dcharmonie.dartstournament.renderer.image;

import java.util.ArrayList;
import java.util.List;

import be.dcharmonie.dartstournament.renderer.image.layout.BracketBoxShape;
import be.dcharmonie.dartstournament.renderer.image.layout.LineShape;
import be.dcharmonie.dartstournament.renderer.image.layout.Shape;

/**
 *
 */
public class FinalNodeDraw implements Drawable {
    private final List<Shape> shapes = new ArrayList<>();
    private int x;
    private int y;

    @Override
    public void assembleImage(int x, int y) {
        this.x = x;
        this.y = y;
        shapes.add(new BracketBoxShape(this.x, this.y));
        shapes.add(new LineShape(this.x - (Shape.WIDTH / 2), this.y, this.x - (Shape.WIDTH / 2) - Shape.WIDTH_LINE, this.y));
        shapes.add(new LineShape(this.x + (Shape.WIDTH / 2), this.y, this.x + (Shape.WIDTH / 2) + Shape.WIDTH_LINE, this.y));
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

    @Override
    public List<Shape> getShapes() {
        return shapes;
    }

}
