package be.dcharmonie.dartstournament;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.thymeleaf.context.Context;

import be.dcharmonie.dartstournament.core.Tournament;
import be.dcharmonie.dartstournament.renderer.html.HtmlRenderer;
import be.dcharmonie.dartstournament.renderer.image.BracketSchemaImageRenderer;
import be.dcharmonie.dartstournament.renderer.image.layout.BracketSchemaPaperPrinter;
import be.dcharmonie.dartstournament.writer.PdfWriter;
import be.dcharmonie.dartstournament.writer.Writer;

//TODO : alfabetiche aanwezigheidslijst genereren met vermelding in welke poule ze zitten
public class TournamentOutputGenerator {

    private final Tournament tournament;
    private final Writer<String> pdfWriter;
    private final HtmlRenderer htmlRenderer;

    public TournamentOutputGenerator(Tournament tournament, Writer<String> pdfWriter) {
        this.tournament = tournament;
        this.pdfWriter = pdfWriter;
        this.htmlRenderer = new HtmlRenderer();
    }

    public void generatePouleSheets(String filename) {
        Context context = new Context();
        context.setVariable("alphabet", "ABCDEF");
        context.setVariable("poules", tournament.getPoules());
        pdfWriter.write(htmlRenderer.render("poule_sheet", context), filename);
    }

    public void generatePouleGameNotes(String filename) {
        Context context = new Context();
        context.setVariable("poules", tournament.getPoules());
        pdfWriter.write(htmlRenderer.render( "poule_game_notes", context), filename);
    }

    public void generateKnockOutGameNotes(String filename) {
        Context context = new Context();
        context.setVariable("games", tournament.getAllBracketNodes().sorted().toList());
        pdfWriter.write(htmlRenderer.render( "knockout_game_notes", context), filename);
    }

    public void generateKnockOutSchemaSheet(String filename) {
        try {
            BracketSchemaPaperPrinter printer = new BracketSchemaPaperPrinter(tournament.getNumberOfPlayersKnockOutPhase(),
                    tournament.getFirstRoundType().getRoundNumber());
            BracketSchemaImageRenderer drawer = new BracketSchemaImageRenderer(tournament);
            drawer.createSchema(printer);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(printer.getImage(), "png", byteArrayOutputStream);
            ((PdfWriter)pdfWriter).write(byteArrayOutputStream, printer.getPaperFormat(), filename);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to save bytearray", e);
        }
    }
}
