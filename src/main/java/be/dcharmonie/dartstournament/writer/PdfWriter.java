package be.dcharmonie.dartstournament.writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

/**
 *
 */
public class PdfWriter implements Writer<String> {

    @Override
    public void write(String render, String filename) {
        try {
            OutputStream outputStream = new FileOutputStream(filename);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(render);
            renderer.layout();
            renderer.createPDF(outputStream);

            outputStream.close();
        } catch (DocumentException | IOException e) {
            throw new IllegalStateException("Unable to save pdf to file " + filename, e);
        }
    }

}
