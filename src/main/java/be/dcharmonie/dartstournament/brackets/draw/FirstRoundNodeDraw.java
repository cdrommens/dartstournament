package be.dcharmonie.dartstournament.brackets.draw;

import java.awt.Graphics2D;

import be.dcharmonie.dartstournament.brackets.core.BracketNode;

/**
 *
 */
public class FirstRoundNodeDraw implements Drawable {

    private final BracketNode parent;
    private int x;
    private int y;
    private boolean isLeft;

    public FirstRoundNodeDraw(BracketNode parent) {
        this.parent = parent;
    }

    @Override
    public void drawImage(Graphics2D graphics, int x, int y) {
        int numberOfPlayersInRound = (int)Math.pow(2, parent.getRound().getRoundNumber());
        int numberOfMatchesInRound = numberOfPlayersInRound/2;
        int numberOfMatchesInHalfRound = numberOfMatchesInRound/2;
        int numberOfMatchesInQuarterRound = numberOfMatchesInHalfRound/2;

        if (parent.getMatchNumber() <= numberOfMatchesInHalfRound) {
            drawImageLeft(graphics, numberOfMatchesInQuarterRound, x, y);
        } else {
            drawImageRight(graphics, numberOfMatchesInHalfRound + numberOfMatchesInQuarterRound, x, y);
        }
    }

    @Override
    public boolean isLeftBracket() {
        return this.isLeft;
    }

    private void drawImageLeft(Graphics2D graphics, int medio, int x, int y) {
        this.x = x - getWidth() - ((parent.getRound().getRoundNumber() - 2) * getWidth());
        if (parent.getMatchNumber() <= medio) {
            this.y = y - (getHeight() / 2) - ((medio - parent.getMatchNumber()) * getHeight());
        } else {
            this.y = y + (getHeight() / 2) + ((parent.getMatchNumber() - medio - 1) * getHeight());
        }
        this.isLeft = true;
        graphics.drawLine(this.x + (WIDTH / 2), this.y, this.x + (WIDTH / 2) + WIDTH_LINE, this.y);
        drawBox(graphics, this.x, this.y);
    }

    private void drawImageRight(Graphics2D graphics, int medio, int x, int y) {
        this.x = x + getWidth() + ((parent.getRound().getRoundNumber() - 2) * getWidth());
        if (parent.getMatchNumber() <= medio) {
            this.y = y - (getHeight() / 2) - ((medio - parent.getMatchNumber()) * getHeight());
        } else {
            this.y = y + (getHeight() / 2) + ((parent.getMatchNumber() - medio - 1) * getHeight());
        }
        this.isLeft = false;
        graphics.drawLine(this.x - (WIDTH / 2), this.y, this.x - (WIDTH / 2) - WIDTH_LINE, this.y);
        drawBox(graphics, this.x, this.y);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
