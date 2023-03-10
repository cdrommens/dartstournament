package be.dcharmonie.dartstournament.writer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.xhtmlrenderer.util.FSImageWriter;

public class PngWriter implements Writer<BufferedImage> {

    @Override
    public void write(BufferedImage render, String filename) {
        FSImageWriter imageWriter = new FSImageWriter();
        Path resourceDirectory = Paths.get(filename);
        try {
            imageWriter.write(render, resourceDirectory.toFile().getPath());
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save image to file " + filename, e);
        }
    }
}
