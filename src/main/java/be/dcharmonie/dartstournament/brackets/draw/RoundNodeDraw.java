package be.dcharmonie.dartstournament.brackets.draw;

import java.awt.Graphics2D;

import be.dcharmonie.dartstournament.brackets.core.BracketNode;

/**
 *
 */
public class RoundNodeDraw implements Drawable {

    private final BracketNode parent;
    private int x;
    private int y;
    private boolean isLeft;

    public RoundNodeDraw(BracketNode parent) {
        this.parent = parent;
    }

    @Override
    public void drawImage(Graphics2D graphics, int x, int y) {
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
        graphics.drawLine(this.x - (WIDTH / 2), this.y, this.x - (WIDTH / 2) - WIDTH_LINE, this.y);
        graphics.drawLine(this.x + (WIDTH / 2), this.y, this.x + (WIDTH / 2) + WIDTH_LINE, this.y);
        drawConnectionLine(graphics, previousFirstNode, previousSecondNode);
        drawBox(graphics, this.x, this.y);
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
        this.x = previousFirstNode.getX() + getWidth();
        this.y = ((previousSecondNode.getY() - previousFirstNode.getY()) / 2) + previousFirstNode.getY();
        this.isLeft = true;
    }

    private void calculateImageRight(Drawable previousFirstNode, Drawable previousSecondNode) {
        this.x = previousFirstNode.getX() - getWidth();
        this.y = ((previousSecondNode.getY() - previousFirstNode.getY()) / 2) + previousFirstNode.getY();
        this.isLeft = false;
    }

    private void drawConnectionLine(Graphics2D graphics, Drawable previousFirstNode, Drawable previousSecondNode) {
        if (previousSecondNode.isLeftBracket()) {
            graphics.drawLine(
                    this.x - (WIDTH / 2) - WIDTH_LINE, previousFirstNode.getY(),
                    this.x - (WIDTH / 2) - WIDTH_LINE, previousSecondNode.getY());
        } else {
            graphics.drawLine(
                    this.x + (WIDTH / 2) + WIDTH_LINE, previousFirstNode.getY(),
                    this.x + (WIDTH / 2) + WIDTH_LINE, previousSecondNode.getY());
        }
    }
}
