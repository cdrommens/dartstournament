package be.dcharmonie.dartstournament;

import be.dcharmonie.dartstournament.core.Player;
import be.dcharmonie.dartstournament.core.Poule;
import be.dcharmonie.dartstournament.core.Tournament;
import be.dcharmonie.dartstournament.writer.PdfWriter;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FullTest {

    @Test
    void generateAll() {

        Path pouleSheets = Paths.get("/Users/u04880/personal/output/pouleSheet.pdf");
        Path pouleGameNotes = Paths.get("/Users/u04880/personal/output/pouleGameNotes.pdf");
        Path attendanceList = Paths.get("/Users/u04880/personal/output/attendanceList.pdf");
        Path knockOutGameNotes = Paths.get("/Users/u04880/personal/output/knockOutGameNotes.pdf");
        Path knockOutSchemaSheet = Paths.get("/Users/u04880/personal/output/knockOutSchemaSheet.pdf");

        Tournament tournament = new Tournament(64, 16, 32);

        Poule pouleA = new Poule("A", 5);
        pouleA.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleA.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleA.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleA.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleB = new Poule("B", 5);
        pouleB.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleB.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleB.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleB.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleC = new Poule("C", 5);
        pouleC.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleC.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleC.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleC.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleD = new Poule("D", 5);
        pouleD.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleD.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleD.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleD.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleE = new Poule("E", 5);
        pouleE.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleE.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleE.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleE.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleF = new Poule("F", 5);
        pouleF.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleF.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleF.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleF.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleG = new Poule("G", 5);
        pouleG.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleG.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleG.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleG.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleH = new Poule("H", 5);
        pouleH.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleH.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleH.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleH.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleI = new Poule("I", 5);
        pouleI.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleI.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleI.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleI.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleJ = new Poule("J", 5);
        pouleJ.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleJ.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleJ.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleJ.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleK = new Poule("K", 5);
        pouleK.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleK.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleK.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleK.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleL = new Poule("L", 5);
        pouleL.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleL.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleL.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleL.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleM = new Poule("M", 5);
        pouleM.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleM.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleM.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleM.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleN = new Poule("N", 5);
        pouleN.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleN.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleN.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleN.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleO = new Poule("O", 5);
        pouleO.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleO.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleO.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleO.addPlayer(new Player("Voornaam", "Achternaam"));

        Poule pouleP = new Poule("P", 5);
        pouleP.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleP.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleP.addPlayer(new Player("Voornaam", "Achternaam"));
        pouleP.addPlayer(new Player("Voornaam", "Achternaam"));

        tournament.addPoule(pouleA);
        tournament.addPoule(pouleB);
        tournament.addPoule(pouleC);
        tournament.addPoule(pouleD);
        tournament.addPoule(pouleE);
        tournament.addPoule(pouleF);
        tournament.addPoule(pouleG);
        tournament.addPoule(pouleH);
        tournament.addPoule(pouleI);
        tournament.addPoule(pouleJ);
        tournament.addPoule(pouleK);
        tournament.addPoule(pouleL);
        tournament.addPoule(pouleM);
        tournament.addPoule(pouleN);
        tournament.addPoule(pouleO);
        tournament.addPoule(pouleP);

        TournamentOutputGenerator generator = new TournamentOutputGenerator(tournament, new PdfWriter());
        generator.generatePouleSheets(pouleSheets.toFile().getPath());
        generator.generatePouleGameNotes(pouleGameNotes.toFile().getPath());
        generator.generateAttendanceList(attendanceList.toFile().getPath());
        generator.generateKnockOutGameNotes(knockOutGameNotes.toFile().getPath());
        generator.generateKnockOutSchemaSheet(knockOutSchemaSheet.toFile().getPath());
    }
}
