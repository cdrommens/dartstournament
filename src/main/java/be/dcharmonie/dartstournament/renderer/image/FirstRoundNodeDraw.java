package be.dcharmonie.dartstournament.renderer.image;

import java.util.ArrayList;
import java.util.List;

import be.dcharmonie.dartstournament.core.BracketNode;
import be.dcharmonie.dartstournament.renderer.image.layout.BracketBoxShape;
import be.dcharmonie.dartstournament.renderer.image.layout.LineShape;
import be.dcharmonie.dartstournament.renderer.image.layout.Shape;

/**
 *
 */
public class FirstRoundNodeDraw implements Drawable {
    private static final List<Shape> SHAPES = new ArrayList<>();
    private final BracketNode parent;
    private int x;
    private int y;
    private boolean isLeft;

    public FirstRoundNodeDraw(BracketNode parent) {
        this.parent = parent;
    }

    @Override
    public void assembleImage(int x, int y) {
        int numberOfPlayersInRound = (int)Math.pow(2, parent.getRound().getRoundNumber());
        int numberOfMatchesInRound = numberOfPlayersInRound/2;
        int numberOfMatchesInHalfRound = numberOfMatchesInRound/2;
        int numberOfMatchesInQuarterRound = numberOfMatchesInHalfRound/2;

        if (parent.getMatchNumber() <= numberOfMatchesInHalfRound) {
            drawImageLeft(numberOfMatchesInQuarterRound, x, y);
        } else {
            drawImageRight(numberOfMatchesInHalfRound + numberOfMatchesInQuarterRound, x, y);
        }
    }

    @Override
    public boolean isLeftBracket() {
        return this.isLeft;
    }

    private void drawImageLeft(int medio, int x, int y) {
        this.x = x - Shape.BOX_WIDTH - ((parent.getRound().getRoundNumber() - 2) * Shape.BOX_WIDTH);
        if (parent.getMatchNumber() <= medio) {
            this.y = y - (Shape.BOX_HEIGHT / 2) - ((medio - parent.getMatchNumber()) * Shape.BOX_HEIGHT);
        } else {
            this.y = y + (Shape.BOX_HEIGHT / 2) + ((parent.getMatchNumber() - medio - 1) * Shape.BOX_HEIGHT);
        }
        this.isLeft = true;
        SHAPES.add(new LineShape(this.x + (Shape.WIDTH / 2), this.y, this.x + (Shape.WIDTH / 2) + Shape.WIDTH_LINE, this.y));
        SHAPES.add(new BracketBoxShape( this.x, this.y));
    }

    private void drawImageRight(int medio, int x, int y) {
        this.x = x + Shape.BOX_WIDTH + ((parent.getRound().getRoundNumber() - 2) * Shape.BOX_WIDTH);
        if (parent.getMatchNumber() <= medio) {
            this.y = y - (Shape.BOX_HEIGHT / 2) - ((medio - parent.getMatchNumber()) * Shape.BOX_HEIGHT);
        } else {
            this.y = y + (Shape.BOX_HEIGHT / 2) + ((parent.getMatchNumber() - medio - 1) * Shape.BOX_HEIGHT);
        }
        this.isLeft = false;
        SHAPES.add(new LineShape(this.x - (Shape.WIDTH / 2), this.y, this.x - (Shape.WIDTH / 2) - Shape.WIDTH_LINE, this.y));
        SHAPES.add(new BracketBoxShape( this.x, this.y));
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
        return SHAPES;
    }
}
