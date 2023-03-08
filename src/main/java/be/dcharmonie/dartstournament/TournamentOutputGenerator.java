package be.dcharmonie.dartstournament;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.thymeleaf.context.Context;

import be.dcharmonie.dartstournament.core.Tournament;
import be.dcharmonie.dartstournament.renderer.html.HtmlRenderer;
import be.dcharmonie.dartstournament.renderer.image.BracketSchemaImageRenderer;
import be.dcharmonie.dartstournament.renderer.image.layout.BracketSchemaPaperPrinter;
import be.dcharmonie.dartstournament.renderer.pdf.PdfRenderer;

/**
 *
 */
public class TournamentOutputGenerator {

    private final Tournament tournament;
    private final PdfRenderer pdfWriter;
    private final HtmlRenderer htmlRenderer;

    public TournamentOutputGenerator(Tournament tournament, PdfRenderer pdfWriter) {
        this.tournament = tournament;
        this.pdfWriter = pdfWriter;
        this.htmlRenderer = new HtmlRenderer();
    }

    public void generatePouleSheets(String filename) {
        Context context = new Context();
        context.setVariable("poules", tournament.getPoules());
        pdfWriter.createPdf(htmlRenderer.render("poule", context), filename);
    }

    public void generatePouleGameNotes(String filename) {
        Context context = new Context();
        context.setVariable("poules", tournament.getPoules());
        pdfWriter.createPdf(htmlRenderer.render( "poule_briefjes", context), filename);
    }

    public void generateKnockOutGameNotes(String filename) {
        //TODO: to implement
        Context context = new Context();
        pdfWriter.createPdf(htmlRenderer.render( "knockout_briefjes", context), filename);
    }

    public void generateKnockOutSchemaSheet(String filename) {
        try {
            //TODO : is paper size ok? Need to scale?
            BracketSchemaPaperPrinter printer = new BracketSchemaPaperPrinter(tournament.getNumberOfPlayersKnockOutPhase(),
                    tournament.getFirstRoundType().getRoundNumber());
            BracketSchemaImageRenderer drawer = new BracketSchemaImageRenderer(tournament);
            drawer.createSchema(printer);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(printer.getImage(), "png", byteArrayOutputStream);
            String base64Image = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

            Context context = new Context();
            String image = "data:image/png;base64, " + base64Image;
            context.setVariable("image", image);
            context.setVariable("width", printer.getImage().getWidth());
            context.setVariable("height", printer.getImage().getHeight());
            pdfWriter.createPdf(htmlRenderer.render( "knockout_schema", context), filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
