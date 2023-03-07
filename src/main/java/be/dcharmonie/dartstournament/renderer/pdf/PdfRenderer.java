package be.dcharmonie.dartstournament.renderer.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

/**
 *
 */
public class PdfRenderer {

    public void createPdf(String render, String filename) {
        try {
            OutputStream outputStream = new FileOutputStream(filename);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(render);
            renderer.layout();
            renderer.createPDF(outputStream);

            outputStream.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
