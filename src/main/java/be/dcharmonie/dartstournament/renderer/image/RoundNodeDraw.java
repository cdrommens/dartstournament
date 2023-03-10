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
public class RoundNodeDraw implements Drawable {
    private final List<Shape> shapes = new ArrayList<>();
    private final BracketNode parent;
    private int x;
    private int y;
    private boolean isLeft;

    public RoundNodeDraw(BracketNode parent) {
        this.parent = parent;
    }

    @Override
    public void assembleImage(int x, int y) {
        Drawable previousFirstNode = parent.getPreviousFirstBracketNode()
                .map(BracketNode::getDrawable)
                .orElseThrow(() -> new IllegalStateException("Trying to draw an incomplete previous first node."));
        Drawable previousSecondNode = parent.getPreviousSecondBracketNode()
                .map(BracketNode::getDrawable)
                .orElseThrow(() -> new IllegalStateException("Trying to draw an incomplete previous second node."));
        if (previousFirstNode.isLeftBracket()) {
            calculateImageLeft(previousFirstNode, previousSecondNode);
        } else {
            calculateImageRight(previousFirstNode, previousSecondNode);
        }
        assembleConnectionLine(previousFirstNode, previousSecondNode);
        shapes.add(new BracketBoxShape(this.x, this.y));
        shapes.add(new LineShape(this.x - (Shape.WIDTH / 2), this.y, this.x - (Shape.WIDTH / 2) - Shape.WIDTH_LINE, this.y));
        shapes.add(new LineShape(this.x + (Shape.WIDTH / 2), this.y, this.x + (Shape.WIDTH / 2) + Shape.WIDTH_LINE, this.y));

    }

    @Override
    public boolean isLeftBracket() {
        return this.isLeft;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    private void calculateImageLeft(Drawable previousFirstNode, Drawable previousSecondNode) {
        this.x = previousFirstNode.getX() + Shape.BOX_WIDTH;
        this.y = ((previousSecondNode.getY() - previousFirstNode.getY()) / 2) + previousFirstNode.getY();
        this.isLeft = true;
    }

    private void calculateImageRight(Drawable previousFirstNode, Drawable previousSecondNode) {
        this.x = previousFirstNode.getX() - Shape.BOX_WIDTH;
        this.y = ((previousSecondNode.getY() - previousFirstNode.getY()) / 2) + previousFirstNode.getY();
        this.isLeft = false;
    }

    private void assembleConnectionLine(Drawable previousFirstNode, Drawable previousSecondNode) {
        if (previousSecondNode.isLeftBracket()) {
            shapes.add(new LineShape(
                    this.x - (Shape.WIDTH / 2) - Shape.WIDTH_LINE, previousFirstNode.getY(),
                    this.x - (Shape.WIDTH / 2) - Shape.WIDTH_LINE, previousSecondNode.getY()));
        } else {
            shapes.add(new LineShape(
                    this.x + (Shape.WIDTH / 2) + Shape.WIDTH_LINE, previousFirstNode.getY(),
                    this.x + (Shape.WIDTH / 2) + Shape.WIDTH_LINE, previousSecondNode.getY()));
        }
    }

    @Override
    public List<Shape> getShapes() {
        return shapes;
    }
}
