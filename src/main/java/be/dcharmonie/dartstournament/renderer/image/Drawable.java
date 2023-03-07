package be.dcharmonie.dartstournament.renderer.image;

import java.util.List;

import be.dcharmonie.dartstournament.renderer.image.layout.Shape;

/**
 *
 */
public interface Drawable {
    void assembleImage(int x, int y);
    boolean isLeftBracket();

    int getX();
    int getY();

    List<Shape> getShapes();
}
