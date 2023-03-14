package be.dcharmonie.dartstournament;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import be.dcharmonie.dartstournament.core.Player;
import be.dcharmonie.dartstournament.core.Poule;
import be.dcharmonie.dartstournament.core.Tournament;
import be.dcharmonie.dartstournament.writer.PdfWriter;
import be.dcharmonie.dartstournament.writer.Writer;
import de.redsix.pdfcompare.CompareResult;
import de.redsix.pdfcompare.PdfComparator;

class TournamentOutputGeneratorTest {

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void testPouleSheets(int numberOfPlayers, @TempDir Path tempDir) throws IOException {
        Tournament tournament = new Tournament(numberOfPlayers, 2, 4);
        generatePoules(tournament, numberOfPlayers, 2);
        Path actual = tempDir.resolve(String.format("pouleSheet%s.html", numberOfPlayers*2));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new StringWriter());
        generator.generatePouleSheets(actual.toFile().getPath());

        Path resourceDirectory = Paths.get("src","test","resources", String.format("pouleSheet%s.html", numberOfPlayers));
        String expectedContent = Files.readString(resourceDirectory);
        String actualContent = Files.readString(actual);

        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void testPouleSheetsPdf(int numberOfPlayers, @TempDir Path tempDir) throws IOException {
        Tournament tournament = new Tournament(numberOfPlayers, 2, 4);
        generatePoules(tournament, numberOfPlayers, 2);
        Path actual = tempDir.resolve(String.format("pouleSheet%s.pdf", numberOfPlayers*2));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generatePouleSheets(actual.toFile().getPath());

        Path resourceDirectory = Paths.get("src","test","resources", String.format("pouleSheet%s.pdf", numberOfPlayers));

        final CompareResult result = new PdfComparator(resourceDirectory, actual).compare();
        assertThat(result.isEqual()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void testPouleGameNotes(int numberOfPlayers, @TempDir Path tempDir) throws IOException {
        Tournament tournament = new Tournament(numberOfPlayers, 2, 4);
        generatePoules(tournament, numberOfPlayers, 2);
        Path actual = tempDir.resolve(String.format("pouleGameNotes%s.html", numberOfPlayers*2));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new StringWriter());
        generator.generatePouleGameNotes(actual.toFile().getPath());

        Path resourceDirectory = Paths.get("src","test","resources", String.format("pouleGameNotes%s.html", numberOfPlayers));
        String expectedContent = Files.readString(resourceDirectory);
        String actualContent = Files.readString(actual);

        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void testPouleGameNotesPdf(int numberOfPlayers, @TempDir Path tempDir) throws IOException {
        Tournament tournament = new Tournament(numberOfPlayers, 2, 4);
        generatePoules(tournament, numberOfPlayers, 2);
        Path actual = tempDir.resolve(String.format("pouleGameNotes%s.pdf", numberOfPlayers*2));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generatePouleGameNotes(actual.toFile().getPath());

        Path resourceDirectory = Paths.get("src","test","resources", String.format("pouleGameNotes%s.pdf", numberOfPlayers));

        final CompareResult result = new PdfComparator(resourceDirectory, actual).compare();
        assertThat(result.isEqual()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void testAttendanceList(int numberOfPlayers, @TempDir Path tempDir) throws IOException {
        Tournament tournament = new Tournament(numberOfPlayers, 2, 4);
        generatePoules(tournament, numberOfPlayers, 2);
        Path actual = tempDir.resolve(String.format("attendanceList%s.html", numberOfPlayers*2));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new StringWriter());
        generator.generateAttendanceList(actual.toFile().getPath());

        Path resourceDirectory = Paths.get("src","test","resources", String.format("attendanceList%s.html", numberOfPlayers));
        String expectedContent = Files.readString(resourceDirectory);
        String actualContent = Files.readString(actual);

        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void testAttendanceListPdf(int numberOfPlayers, @TempDir Path tempDir) throws IOException {
        Tournament tournament = new Tournament(numberOfPlayers, 2, 4);
        generatePoules(tournament, numberOfPlayers, 2);
        Path actual = tempDir.resolve(String.format("attendanceList%s.pdf", numberOfPlayers*2));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generateAttendanceList(actual.toFile().getPath());

        Path resourceDirectory = Paths.get("src","test","resources", String.format("attendanceList%s.pdf", numberOfPlayers));

        final CompareResult result = new PdfComparator(resourceDirectory, actual).compare();
        assertThat(result.isEqual()).isTrue();
    }

    @Test
    void testAttendanceListWithPageBreakPdf(@TempDir Path tempDir) throws IOException {
        Tournament tournament = new Tournament(26, 2, 4);
        generatePoules(tournament, 5, 6);
        Path actual = tempDir.resolve("attendanceListPageBreak.pdf");
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generateAttendanceList(actual.toFile().getPath());

        Path resourceDirectory = Paths.get("src","test","resources", "attendanceListPageBreak.pdf");

        final CompareResult result = new PdfComparator(resourceDirectory, actual).compare();
        assertThat(result.isEqual()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 16, 32, 64})
    void testKnockOutGameNotes(int numberOfPlayersKnockOutPhase, @TempDir Path tempDir) throws IOException {
        Tournament tournament = new Tournament(4, 2, numberOfPlayersKnockOutPhase);
        Path actual = tempDir.resolve(String.format("knockOutGameNotes%s.html", numberOfPlayersKnockOutPhase));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new StringWriter());
        generator.generateKnockOutGameNotes(actual.toFile().getPath());

        Path resourceDirectory = Paths.get("src","test","resources", String.format("knockOutGameNotes%s.html", numberOfPlayersKnockOutPhase));
        String expectedContent = Files.readString(resourceDirectory);
        String actualContent = Files.readString(actual);

        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 16, 32, 64})
    void testKnockOutGameNotesPdf(int numberOfPlayersKnockOutPhase, @TempDir Path tempDir) throws IOException {
        Tournament tournament = new Tournament(4, 2, numberOfPlayersKnockOutPhase);
        Path actual = tempDir.resolve(String.format("knockOutGameNotes%s.pdf", numberOfPlayersKnockOutPhase));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generateKnockOutGameNotes(actual.toFile().getPath());

        Path resourceDirectory = Paths.get("src","test","resources", String.format("knockOutGameNotes%s.pdf", numberOfPlayersKnockOutPhase));

        final CompareResult result = new PdfComparator(resourceDirectory, actual).compare();
        assertThat(result.isEqual()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 16, 32, 64})
    void testKnockOutSchemaPdf(int numberOfPlayersKnockOutPhase, @TempDir Path tempDir) throws IOException {
        Tournament tournament = new Tournament(4, 2, numberOfPlayersKnockOutPhase);
        Path actual = tempDir.resolve(String.format("knockOutSchema%s.pdf", numberOfPlayersKnockOutPhase));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generateKnockOutSchemaSheet(actual.toFile().getPath());

        Path resourceDirectory = Paths.get("src","test","resources", String.format("knockOutSchema%s.pdf", numberOfPlayersKnockOutPhase));

        final CompareResult result = new PdfComparator(resourceDirectory, actual).compare();
        assertThat(result.isEqual()).isTrue();
    }

    private static void generatePoules(Tournament tournament, int numberOfPlayers, int numberOfPoules) {
        for (int p = 0; p < numberOfPoules; p++) {
            Poule poule = new Poule(String.valueOf(Character.toChars(65 + p)), 3);
            for (int i = 0; i < numberOfPlayers; i++) {
                final var lastName = String.valueOf(Character.toChars(65 + i));
                poule.addPlayer(new Player("player", lastName));
            }
            tournament.addPoule(poule);
        }
    }

    @Disabled("Method used for generating new expected sheets and game notes and attendance list")
    @ParameterizedTest
    @ValueSource(ints = {8, 10, 12})
    void generateNewPouleSheetExpected(int numberOfPlayers) {
        Tournament tournament = new Tournament(numberOfPlayers, 2, 4);
        generatePoules(tournament, numberOfPlayers, 2);
        Path resourcePouleSheetsDirectory = Paths.get("src","test","resources", String.format("pouleSheet%s.html", numberOfPlayers*2));
        Path resourcePouleGameNotesDirectory = Paths.get("src","test","resources", String.format("pouleGameNotes%s.html", numberOfPlayers*2));
        Path resourceAttendanceListDirectory = Paths.get("src","test","resources", String.format("attendanceList%s.html", numberOfPlayers*2));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new StringWriter());
        generator.generatePouleSheets(resourcePouleSheetsDirectory.toFile().getPath());
        generator.generatePouleGameNotes(resourcePouleGameNotesDirectory.toFile().getPath());
        generator.generateAttendanceList(resourceAttendanceListDirectory.toFile().getPath());
        Path resourcePouleSheetsPdfDirectory = Paths.get("src","test","resources", String.format("pouleSheet%s.pdf", numberOfPlayers*2));
        Path resourcePouleGameNotesPdfDirectory = Paths.get("src","test","resources", String.format("pouleGameNotes%s.pdf", numberOfPlayers*2));
        Path resourceAttendanceListPdfDirectory = Paths.get("src","test","resources", String.format("attendanceList%s.pdf", numberOfPlayers*2));
        generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generatePouleSheets(resourcePouleSheetsPdfDirectory.toFile().getPath());
        generator.generatePouleGameNotes(resourcePouleGameNotesPdfDirectory.toFile().getPath());
        generator.generateAttendanceList(resourceAttendanceListPdfDirectory.toFile().getPath());
        assertThat(resourcePouleSheetsPdfDirectory.toFile()).exists();
        assertThat(resourcePouleGameNotesPdfDirectory.toFile()).exists();
        assertThat(resourceAttendanceListPdfDirectory.toFile()).exists();
    }

    @Disabled("Method used for generating new knockout game notes")
    @ParameterizedTest
    @ValueSource(ints = {8, 16, 32, 64})
    void generateNewKnockOutGameNotesExpected(int numberOfPlayersKnockOutPhase) {
        Tournament tournament = new Tournament(4, 2, numberOfPlayersKnockOutPhase);
        Path resourceKnockOutGameNotesDirectory = Paths.get("src","test","resources", String.format("knockOutGameNotes%s.html", numberOfPlayersKnockOutPhase));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new StringWriter());
        generator.generateKnockOutGameNotes(resourceKnockOutGameNotesDirectory.toFile().getPath());
        Path resourceKnockOutGameNotesPdfDirectory = Paths.get("src","test","resources", String.format("knockOutGameNotes%s.pdf", numberOfPlayersKnockOutPhase));
        generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generateKnockOutGameNotes(resourceKnockOutGameNotesPdfDirectory.toFile().getPath());
        assertThat(resourceKnockOutGameNotesPdfDirectory.toFile()).exists();
    }

    @Disabled("Method used for generating new knockout schema")
    @ParameterizedTest
    @ValueSource(ints = {8, 16, 32, 64})
    void generateKnockOutSchema(int numberOfPlayersKnockOutPhase) {
        Tournament tournament = new Tournament(4, 2, numberOfPlayersKnockOutPhase);
        Path resourceKnockOutSchemaSheet = Paths.get("src","test","resources", String.format("knockOutSchema%s.pdf", numberOfPlayersKnockOutPhase));
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generateKnockOutSchemaSheet(resourceKnockOutSchemaSheet.toFile().getPath());
        assertThat(resourceKnockOutSchemaSheet.toFile()).exists();
    }

    @Disabled("Method used for generating new attendance list with page break")
    @Test
    void generateNewAttendanceListWithPageBreakPdf() {
        Tournament tournament = new Tournament(26, 2, 4);
        generatePoules(tournament, 5, 6);
        Path resourceAttendanceListWithPageBreak = Paths.get("src","test","resources", "attendanceListPageBreak.html");
        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generateAttendanceList(resourceAttendanceListWithPageBreak.toFile().getPath());
        assertThat(resourceAttendanceListWithPageBreak.toFile()).exists();
    }

    private static class StringWriter implements Writer<String> {

        @Override
        public void write(String render, String filename) {
            try {
                Files.writeString(Path.of(filename), render);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
