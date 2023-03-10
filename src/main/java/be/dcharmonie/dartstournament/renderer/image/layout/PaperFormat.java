package be.dcharmonie.dartstournament.renderer.image.layout;

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

    private boolean fitOnPaper(int width, int height) {
        return width <= this.width && height <= this.height;
    }

    public static PaperFormat selectPaperFormat(int maxWidth, int maxHeight) {
        if (PaperFormat.A4.fitOnPaper(maxWidth, maxHeight)) {
            return PaperFormat.A4;
        } else if (PaperFormat.A3.fitOnPaper(maxWidth, maxHeight)) {
            return PaperFormat.A3;
        } else {
            throw new IllegalStateException("Size of tournament is too big to fit on paper.");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
