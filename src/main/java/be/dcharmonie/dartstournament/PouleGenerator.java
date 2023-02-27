package be.dcharmonie.dartstournament;

import be.dcharmonie.dartstournament.core.FirstRoundNode;
import be.dcharmonie.dartstournament.core.Tournament;
import com.lowagie.text.DocumentException;
import java.awt.Color;
import java.awt.Graphics2D;
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
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D graphics = img.createGraphics();
            graphics.setBackground(Color.WHITE);
            graphics.fillRect(0, 0, width, height);

            /*    (0,0)
             *    ---------------------> X
             *    |
             *    |
             *    Y
             */
            graphics.setColor(Color.BLACK);
            //graphics.drawRect(5, 5, 200, 60);
            //graphics.drawLine(5, 35, 205, 35);

            /*
            FinalNode finalNode = new FinalNode();
            FirstRoundNode match1 = new FirstRoundNode(Round.FINAL_16, 1);
            FirstRoundNode match2 = new FirstRoundNode(Round.FINAL_16, 2);
            FirstRoundNode match10 = new FirstRoundNode(Round.FINAL_16, 10);
            FirstRoundNode match11 = new FirstRoundNode(Round.FINAL_16, 11);

            finalNode.drawImage(graphics, 0, width/2, height/2);
            match1.drawImage(graphics, 0, width/2, height/2);
            match2.drawImage(graphics, 0, width/2, height/2);
            match10.drawImage(graphics, 0, width/2, height/2);
            match11.drawImage(graphics, 0, width/2, height/2);
            */

            Tournament tournament = new Tournament(32);
            tournament.getFinal().drawBox(graphics, 0, width/2, height/2);
            tournament.getTournamentMap().values().stream()
                            .filter(node -> node instanceof FirstRoundNode)
                                    .forEach(node -> ((FirstRoundNode) node).drawImage(graphics, 0, width/2, height/2));
            graphics.dispose();

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