package be.dcharmonie.dartstournament.brackets.draw;

import java.util.List;

import be.dcharmonie.dartstournament.brackets.draw.layout.Shape;

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
