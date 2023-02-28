package be.dcharmonie.dartstournament.core;

/**
 *
 */
public enum PaperFormat {
    A3(4961, 3508),
    A4(3508, 2480);

    private final int width;
    private final int height;

    PaperFormat(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean fitOnPaper(int width, int height) {
        return width <= this.width && height <= this.height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
