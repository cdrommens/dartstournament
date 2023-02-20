package be.dcharmonie.dartstournament;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DartsTournamentApplication {

    @Autowired
    private PouleGenerator pouleGenerator;

    public static void main(String[] args) {
        SpringApplication.run(be.dcharmonie.dartstournament.DartsTournamentApplication.class, args);
    }

    @EventListener(classes = ApplicationReadyEvent.class)
    public void ready(){
        List<Poule> poules = List.of(
                new Poule("Poule A", "Olivier Van de Walle", "Christophe Camerlynck", "Bjorn Depaepe", "Douwe Van Breuseghem", "Jason Vanderbruggen"),
                new Poule("Poule B", "Manuel De Weerdt", "Olivier Leclercq", "Eddy Devillez", "Christophe Van Der Beken", "Ilias Ikadouran"),
                new Poule("Poule C", "Dimitri Gaublomme", "Joeri Delie", "Jelle Delie", "Sammy Dewitte", "Kevin Moeyaert"),
                new Poule("Poule D", "Jorben Provost", "Niels Sappin", "Mathias Lourette", "Jason Depreter", "Christian Grignard"),
                new Poule("Poule E", "Cederik Rommens", "Philippe D'hondt", "David Petito", "Michiel De Ruyck", "Amyrah Van de Walle"),
                new Poule("Poule F", "Jean Jacques Parent", "Jonathan De Roose", "Erik Deroose", "Christine Van Meerhaeghe", "Benoit Van den Driessche"),
                new Poule("Poule G", "Beatrice Moreau", "Julien De Meyer", "Hendrik Engels", "Sybren Decock", "Steven Van Caenegem"),
                new Poule("Poule H", "Günther Van Mieghem", "Cédric De Riemaecker", "Andy Couplet", "Joel Van Audenhove", "Dimitri De Tarernier"),
                new Poule("Poule I", "Quentin Vandepeute", "Hendrik Baert", "Louis Van Geldorp", "Geoffrey Van Weehaege", "Gino Van Dorpe"),
                new Poule("Poule J", "Chris Daelmans", "Frederic Provost", "Joran Deriemaeker", "Jeremy Bauchamp", "Jonas Delusinne"),
                new Poule("Poule K", "Stephanie Vandenbroeck", "Alain Crombez", "Shane Van Geldorp", "Dimitri Parent", "Geert Petrens"),
                new Poule("Poule L", "Erwin Versluys", "Thomas Leclercq", "Rudy Vandekerckhove", "Patrick Goebeert", "Dylan De Roose"),
                new Poule("Poule M", "Mike Vandenabeele", "Dimitri Penson", "Didier Loof", "Christophe Buyens", "Stani Vlerick"),
                new Poule("Poule N", "Brigitte Assel", "Maxim Van Weehaege", "Patrick Delfosse", "Xander Vandekerckhove", "Jessica Hanssens"),
                new Poule("Poule O", "Bradley Vermeersch", "Andy De Dapper", "Sven Tenteniez", "Stefan Dewitte", "Gregory Vanpoucke"),
                new Poule("Poule P", "Mathias Vanden Berghe", "Pieter Cardon", "Pascal Hantson", "Sebastien De Meyer", "Kristof Dalez")
        );
        pouleGenerator.generatePoules(poules, "poules");
        pouleGenerator.generateBriefjes(poules, "poule_briefjes");
        pouleGenerator.generateKnockOut("knockout_briefjes");
    }

}