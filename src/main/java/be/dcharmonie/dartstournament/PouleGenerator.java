package be.dcharmonie.dartstournament;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

import com.lowagie.text.DocumentException;

import be.dcharmonie.dartstournament.core.Tournament;
import be.dcharmonie.dartstournament.core.TournamentDrawer;

/**
 *
 */
@Service
public class PouleGenerator {

    public void generatePoules(List<Poule> poules, String filename) {
        try {
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode(TemplateMode.HTML);

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            Context context = new Context();
            context.setVariable("poules", poules);

            //String result = templateEngine.process("poule", context);
            String result = templateEngine.process("poule", context);

            String outputFolder = System.getProperty("user.home") + File.separator + filename + ".pdf";
            OutputStream outputStream = new FileOutputStream(outputFolder);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(result);
            renderer.layout();
            renderer.createPDF(outputStream);

            outputStream.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateBriefjes(List<Poule> poules, String filename) {
        try {
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode(TemplateMode.HTML);

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            Context context = new Context();
            context.setVariable("poules", poules);

            //String result = templateEngine.process("poule", context);
            String result = templateEngine.process("poule_briefjes", context);

            String outputFolder = System.getProperty("user.home") + File.separator + filename + ".pdf";
            OutputStream outputStream = new FileOutputStream(outputFolder);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(result);
            renderer.layout();
            renderer.createPDF(outputStream);

            outputStream.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateKnockOut(String filename) {
        try {
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode(TemplateMode.HTML);

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            Context context = new Context();
            String result = templateEngine.process("knockout_briefjes", context);

            String outputFolder = System.getProperty("user.home") + File.separator + filename + ".pdf";
            OutputStream outputStream = new FileOutputStream(outputFolder);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(result);
            renderer.layout();
            renderer.createPDF(outputStream);

            outputStream.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    //https://devtut.github.io/java/creating-images-programmatically.html#how-to-scale-a-bufferedimage
    public void generateBrackets(String filename) {
        try {
            int width = 4961;
            int height = 3508;

            Tournament tournament = new Tournament(32);
            TournamentDrawer drawer = new TournamentDrawer(tournament);
            BufferedImage img = drawer.drawImage();


            FSImageWriter imageWriter = new FSImageWriter();
            String outputFolderImg = System.getProperty("user.home") + File.separator + filename + ".png";
            imageWriter.write(img, outputFolderImg);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        /*
        try (Document document = new Document()) {
            PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("parseTable.pdf")));
            document.open();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */
}

record Poule (String name, String playerA, String playerB, String playerC, String playerD, String playerE) {}