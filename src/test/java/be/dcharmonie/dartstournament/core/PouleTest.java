package be.dcharmonie.dartstournament.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class PouleTest {

    @Test
    void testMinimalPoule() {
        Poule poule = new Poule("name", 5);
        assertThat(poule.getName()).isEqualTo("name");
        assertThat(poule.getBestOf()).isEqualTo(5);
    }

    @Test
    void testPouleOf3() {
        Poule poule = new Poule("name", 5);
        poule.addPlayer(new Player("player", "A"));
        poule.addPlayer(new Player("player", "B"));
        poule.addPlayer(new Player("player", "C"));

        assertThat(poule.getPlayers()).containsExactly(
                new Player("player", "A"),
                new Player("player", "B"),
                new Player("player", "C")
        );

        assertThat(poule.getGames()).hasSize(3);
        assertThat(poule.getGames()).containsExactly(
                new PouleGame(new Player("player", "A"), new Player("player", "B"), new Player("player", "C")),
                new PouleGame(new Player("player", "B"), new Player("player", "C"), new Player("player", "A")),
                new PouleGame(new Player("player", "C"), new Player("player", "A"), new Player("player", "B"))
        );
    }

    @Test
    void testPouleOf4() {
        Poule poule = new Poule("name", 5);
        poule.addPlayer(new Player("player", "A"));
        poule.addPlayer(new Player("player", "B"));
        poule.addPlayer(new Player("player", "C"));
        poule.addPlayer(new Player("player", "D"));

        assertThat(poule.getPlayers()).containsExactly(
                new Player("player", "A"),
                new Player("player", "B"),
                new Player("player", "C"),
                new Player("player", "D")
        );

        assertThat(poule.getGames()).hasSize(6);
        assertThat(poule.getGames()).containsExactly(
                new PouleGame(new Player("player", "A"), new Player("player", "B"), new Player("player", "C")),
                new PouleGame(new Player("player", "C"), new Player("player", "D"), new Player("player", "A")),
                new PouleGame(new Player("player", "B"), new Player("player", "C"), new Player("player", "D")),
                new PouleGame(new Player("player", "D"), new Player("player", "A"), new Player("player", "B")),
                new PouleGame(new Player("player", "B"), new Player("player", "D"), new Player("player", "C")),
                new PouleGame(new Player("player", "A"), new Player("player", "C"), new Player("player", "D"))
        );
    }

    @Test
    void testPouleOf5() {
        Poule poule = new Poule("name", 5);
        poule.addPlayer(new Player("player", "A"));
        poule.addPlayer(new Player("player", "B"));
        poule.addPlayer(new Player("player", "C"));
        poule.addPlayer(new Player("player", "D"));
        poule.addPlayer(new Player("player", "E"));

        assertThat(poule.getPlayers()).containsExactly(
                new Player("player", "A"),
                new Player("player", "B"),
                new Player("player", "C"),
                new Player("player", "D"),
                new Player("player", "E")
        );

        assertThat(poule.getGames()).hasSize(10);
        assertThat(poule.getGames()).containsExactly(
                new PouleGame(new Player("player", "A"), new Player("player", "B"), new Player("player", "E")),
                new PouleGame(new Player("player", "D"), new Player("player", "E"), new Player("player", "A")),
                new PouleGame(new Player("player", "B"), new Player("player", "C"), new Player("player", "D")),
                new PouleGame(new Player("player", "D"), new Player("player", "A"), new Player("player", "C")),
                new PouleGame(new Player("player", "C"), new Player("player", "E"), new Player("player", "B")),
                new PouleGame(new Player("player", "B"), new Player("player", "D"), new Player("player", "E")),
                new PouleGame(new Player("player", "A"), new Player("player", "C"), new Player("player", "D")),
                new PouleGame(new Player("player", "E"), new Player("player", "B"), new Player("player", "A")),
                new PouleGame(new Player("player", "C"), new Player("player", "D"), new Player("player", "B")),
                new PouleGame(new Player("player", "E"), new Player("player", "A"), new Player("player", "C"))
        );
    }

    @Test
    void testPouleOf6() {
        Poule poule = new Poule("name", 5);
        poule.addPlayer(new Player("player", "A"));
        poule.addPlayer(new Player("player", "B"));
        poule.addPlayer(new Player("player", "C"));
        poule.addPlayer(new Player("player", "D"));
        poule.addPlayer(new Player("player", "E"));
        poule.addPlayer(new Player("player", "F"));

        assertThat(poule.getPlayers()).containsExactly(
                new Player("player", "A"),
                new Player("player", "B"),
                new Player("player", "C"),
                new Player("player", "D"),
                new Player("player", "E"),
                new Player("player", "F")
        );

        assertThat(poule.getGames()).hasSize(15);
        assertThat(poule.getGames()).containsExactly(
                new PouleGame(new Player("player", "A"), new Player("player", "B"), new Player("player", "D")),
                new PouleGame(new Player("player", "F"), new Player("player", "C"), new Player("player", "E")),
                new PouleGame(new Player("player", "D"), new Player("player", "E"), new Player("player", "A")),
                new PouleGame(new Player("player", "C"), new Player("player", "A"), new Player("player", "B")),
                new PouleGame(new Player("player", "B"), new Player("player", "D"), new Player("player", "F")),
                new PouleGame(new Player("player", "E"), new Player("player", "F"), new Player("player", "C")),
                new PouleGame(new Player("player", "C"), new Player("player", "B"), new Player("player", "D")),
                new PouleGame(new Player("player", "A"), new Player("player", "E"), new Player("player", "B")),
                new PouleGame(new Player("player", "F"), new Player("player", "D"), new Player("player", "A")),
                new PouleGame(new Player("player", "E"), new Player("player", "C"), new Player("player", "F")),
                new PouleGame(new Player("player", "D"), new Player("player", "A"), new Player("player", "E")),
                new PouleGame(new Player("player", "B"), new Player("player", "F"), new Player("player", "C")),
                new PouleGame(new Player("player", "C"), new Player("player", "D"), new Player("player", "A")),
                new PouleGame(new Player("player", "E"), new Player("player", "B"), new Player("player", "D")),
                new PouleGame(new Player("player", "F"), new Player("player", "A"), new Player("player", "B"))
        );
    }

    @Test
    void testPouleOfMoreThen6() {
        Poule poule = new Poule("name", 5);
        poule.addPlayer(new Player("player", "A"));
        poule.addPlayer(new Player("player", "B"));
        poule.addPlayer(new Player("player", "C"));
        poule.addPlayer(new Player("player", "D"));
        poule.addPlayer(new Player("player", "E"));
        poule.addPlayer(new Player("player", "F"));
        assertThatIllegalArgumentException().isThrownBy(() -> poule.addPlayer(new Player("player", "G")));
    }
}
