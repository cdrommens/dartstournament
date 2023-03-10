package be.dcharmonie.dartstournament.writer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;

import be.dcharmonie.dartstournament.renderer.image.layout.PaperFormat;

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
        } catch (IOException | DocumentException e) {
            throw new IllegalStateException("Unable to save pdf to file " + filename, e);
        }
    }

    public void write(ByteArrayOutputStream render, PaperFormat paperFormat, String filename) {
        try {
            Document document = new Document(PageSize.getRectangle(paperFormat.name()).rotate(), 20, 20, 20, 20);
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            Image schema = Image.getInstance(render.toByteArray());
            schema.scaleToFit(document.getPageSize().getWidth()-20, document.getPageSize().getHeight()-20);
            document.add(schema);
            document.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
