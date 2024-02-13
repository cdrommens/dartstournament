package be.dcharmonie.dartstournament.library.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class TournamentTest {

    @Test
    void testUnsupportedNumberOfPlayersKnockOut() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Tournament(1, 1, 1));
        assertThatIllegalArgumentException().isThrownBy(() -> new Tournament(1, 1, 3));
        assertThatIllegalArgumentException().isThrownBy(() -> new Tournament(1, 1, 128));
    }

    @Test
    void testUnsupportedOperationOnFinal() {
        Tournament tournament = new Tournament(1, 1, 2);
        FinalNode finalNode = tournament.getFinal();
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> finalNode.setNextBracketNode(null));
    }

    @Test
    void testKnockout2Players() {
        Tournament tournament = new Tournament(1, 1, 2);
        assertThat(tournament.getFinal().getRound()).isEqualTo(Round.FINAL);
        assertThat(tournament.getFirstRoundType()).isEqualTo(Round.FINAL);
        assertThat(tournament.getSortedStreamFirstRoundNodes().collect(Collectors.toList())).isEmpty();
    }

    @Test
    void testKnockout4Players() {
        Tournament tournament = new Tournament(1, 1, 4);
        assertThat(tournament.getFinal().getRound()).isEqualTo(Round.FINAL);
        assertThat(tournament.getFirstRoundType()).isEqualTo(Round.HALF_FINAL);
        assertThat(tournament.getSortedStreamFirstRoundNodes().collect(Collectors.toList())).hasSize(2);
        assertThat(tournament.getSortedStreamFirstRoundNodes().map(BracketNode::getDescription).collect(Collectors.toList()))
                .containsExactly(
                        "Halve Finale - Game 1",
                        "Halve Finale - Game 2"
                );
        assertThat(tournament.getRoundNodesGroupedByRound()).containsOnlyKeys(Round.FINAL, Round.HALF_FINAL);
        assertThat(tournament.getRoundNodesGroupedByRound().get(Round.FINAL)).hasSize(1);
        assertThat(tournament.getRoundNodesGroupedByRound().get(Round.HALF_FINAL)).hasSize(2);
        assertThat(tournament.getAllBracketNodes().map(BracketNode::getDescription).collect(Collectors.toList())).containsExactlyInAnyOrder(
                "Halve Finale - Game 1",
                "Halve Finale - Game 2",
                "Finale"
        );
        BracketNode node = tournament.getAllBracketNodes()
                .filter(n -> Round.HALF_FINAL == n.getRound())
                .filter(n -> n.getMatchNumber() == 2)
                .findFirst()
                .get();
        assertThat(node.getPreviousFirstBracketNode()).isEmpty();
        assertThat(node.getPreviousSecondBracketNode()).isEmpty();
        assertThat(node.getNextBracketNode()).isNotEmpty();
        assertThat(node.getNextBracketNode()).contains(tournament.getFinal());
    }

    @Test
    void testKnockout64Players() {
        Tournament tournament = new Tournament(1, 1, 64);
        assertThat(tournament.getFinal().getRound()).isEqualTo(Round.FINAL);
        assertThat(tournament.getFirstRoundType()).isEqualTo(Round.FINAL_32);
        assertThat(tournament.getSortedStreamFirstRoundNodes().collect(Collectors.toList())).hasSize(32);
        assertThat(tournament.getSortedStreamFirstRoundNodes().map(BracketNode::getDescription).collect(Collectors.toList()))
                .containsExactly(
                        "Laatste 64 - Game 1",
                        "Laatste 64 - Game 2",
                        "Laatste 64 - Game 3",
                        "Laatste 64 - Game 4",
                        "Laatste 64 - Game 5",
                        "Laatste 64 - Game 6",
                        "Laatste 64 - Game 7",
                        "Laatste 64 - Game 8",
                        "Laatste 64 - Game 9",
                        "Laatste 64 - Game 10",
                        "Laatste 64 - Game 11",
                        "Laatste 64 - Game 12",
                        "Laatste 64 - Game 13",
                        "Laatste 64 - Game 14",
                        "Laatste 64 - Game 15",
                        "Laatste 64 - Game 16",
                        "Laatste 64 - Game 17",
                        "Laatste 64 - Game 18",
                        "Laatste 64 - Game 19",
                        "Laatste 64 - Game 20",
                        "Laatste 64 - Game 21",
                        "Laatste 64 - Game 22",
                        "Laatste 64 - Game 23",
                        "Laatste 64 - Game 24",
                        "Laatste 64 - Game 25",
                        "Laatste 64 - Game 26",
                        "Laatste 64 - Game 27",
                        "Laatste 64 - Game 28",
                        "Laatste 64 - Game 29",
                        "Laatste 64 - Game 30",
                        "Laatste 64 - Game 31",
                        "Laatste 64 - Game 32"
                );
        assertThat(tournament.getRoundNodesGroupedByRound()).containsOnlyKeys(Round.FINAL, Round.HALF_FINAL, Round.QUARTER_FINAL,
                Round.FINAL_8, Round.FINAL_16, Round.FINAL_32);
        assertThat(tournament.getRoundNodesGroupedByRound().get(Round.FINAL)).hasSize(1);
        assertThat(tournament.getRoundNodesGroupedByRound().get(Round.HALF_FINAL)).hasSize(2);
        assertThat(tournament.getRoundNodesGroupedByRound().get(Round.QUARTER_FINAL)).hasSize(4);
        assertThat(tournament.getRoundNodesGroupedByRound().get(Round.FINAL_8)).hasSize(8);
        assertThat(tournament.getRoundNodesGroupedByRound().get(Round.FINAL_16)).hasSize(16);
        assertThat(tournament.getRoundNodesGroupedByRound().get(Round.FINAL_32)).hasSize(32);
        assertThat(tournament.getAllBracketNodes().map(BracketNode::getDescription).collect(Collectors.toList())).containsExactlyInAnyOrder(
                "Laatste 64 - Game 1",
                "Laatste 64 - Game 2",
                "Laatste 64 - Game 3",
                "Laatste 64 - Game 4",
                "Laatste 64 - Game 5",
                "Laatste 64 - Game 6",
                "Laatste 64 - Game 7",
                "Laatste 64 - Game 8",
                "Laatste 64 - Game 9",
                "Laatste 64 - Game 10",
                "Laatste 64 - Game 11",
                "Laatste 64 - Game 12",
                "Laatste 64 - Game 13",
                "Laatste 64 - Game 14",
                "Laatste 64 - Game 15",
                "Laatste 64 - Game 16",
                "Laatste 64 - Game 17",
                "Laatste 64 - Game 18",
                "Laatste 64 - Game 19",
                "Laatste 64 - Game 20",
                "Laatste 64 - Game 21",
                "Laatste 64 - Game 22",
                "Laatste 64 - Game 23",
                "Laatste 64 - Game 24",
                "Laatste 64 - Game 25",
                "Laatste 64 - Game 26",
                "Laatste 64 - Game 27",
                "Laatste 64 - Game 28",
                "Laatste 64 - Game 29",
                "Laatste 64 - Game 30",
                "Laatste 64 - Game 31",
                "Laatste 64 - Game 32",
                "16de Finale - Game 1",
                "16de Finale - Game 2",
                "16de Finale - Game 3",
                "16de Finale - Game 4",
                "16de Finale - Game 5",
                "16de Finale - Game 6",
                "16de Finale - Game 7",
                "16de Finale - Game 8",
                "16de Finale - Game 9",
                "16de Finale - Game 10",
                "16de Finale - Game 11",
                "16de Finale - Game 12",
                "16de Finale - Game 13",
                "16de Finale - Game 14",
                "16de Finale - Game 15",
                "16de Finale - Game 16",
                "8ste Finale - Game 1",
                "8ste Finale - Game 2",
                "8ste Finale - Game 3",
                "8ste Finale - Game 4",
                "8ste Finale - Game 5",
                "8ste Finale - Game 6",
                "8ste Finale - Game 7",
                "8ste Finale - Game 8",
                "Kwartfinale - Game 1",
                "Kwartfinale - Game 2",
                "Kwartfinale - Game 3",
                "Kwartfinale - Game 4",
                "Halve Finale - Game 1",
                "Halve Finale - Game 2",
                "Finale"
        );
        Optional<BracketNode> final32Node11 = tournament.getSortedStreamFirstRoundNodes().skip(10).findFirst();
        Optional<BracketNode> final32Node12 = tournament.getSortedStreamFirstRoundNodes().skip(11).findFirst();
        assertThat(final32Node11).isNotEmpty();
        assertThat(final32Node12).isNotEmpty();
        assertThat(final32Node12.get().getRound()).isEqualTo(Round.FINAL_32);
        assertThat(final32Node12.get().getMatchNumber()).isEqualTo(12);
        assertThat(final32Node12.get().getPreviousFirstBracketNode()).isEmpty();
        assertThat(final32Node12.get().getPreviousSecondBracketNode()).isEmpty();

        Optional<BracketNode> final16Node6 = final32Node12.get().getNextBracketNode();
        assertThat(final16Node6.get().getNextBracketNode()).isNotEmpty();
        assertThat(final16Node6.get().getRound()).isEqualTo(Round.FINAL_16);
        assertThat(final16Node6.get().getMatchNumber()).isEqualTo(6);
        assertThat(final16Node6.get().getPreviousFirstBracketNode()).contains(final32Node11.get());
        assertThat(final16Node6.get().getPreviousSecondBracketNode()).contains(final32Node12.get());

        Optional<BracketNode> final8Node3 = final16Node6.get().getNextBracketNode();
        assertThat(final8Node3.get().getNextBracketNode()).isNotEmpty();
        assertThat(final8Node3.get().getRound()).isEqualTo(Round.FINAL_8);
        assertThat(final8Node3.get().getMatchNumber()).isEqualTo(3);
        assertThat(final8Node3.get().getPreviousFirstBracketNode().map(BracketNode::getMatchNumber)).contains(5);
        assertThat(final8Node3.get().getPreviousSecondBracketNode()).contains(final16Node6.get());

        Optional<BracketNode> final4Node2 = final8Node3.get().getNextBracketNode();
        assertThat(final4Node2.get().getNextBracketNode()).isNotEmpty();
        assertThat(final4Node2.get().getRound()).isEqualTo(Round.QUARTER_FINAL);
        assertThat(final4Node2.get().getMatchNumber()).isEqualTo(2);
        assertThat(final4Node2.get().getPreviousFirstBracketNode()).contains(final8Node3.get());
        assertThat(final4Node2.get().getPreviousSecondBracketNode().map(BracketNode::getMatchNumber)).contains(4);

        Optional<BracketNode> final2Node1 = final4Node2.get().getNextBracketNode();
        assertThat(final2Node1.get().getRound()).isEqualTo(Round.HALF_FINAL);
        assertThat(final2Node1.get().getMatchNumber()).isEqualTo(1);
        assertThat(final2Node1.get().getPreviousFirstBracketNode().map(BracketNode::getMatchNumber)).contains(1);
        assertThat(final2Node1.get().getPreviousSecondBracketNode()).contains(final4Node2.get());

        assertThat(final2Node1.get().getNextBracketNode()).contains(tournament.getFinal());

        assertThat(tournament.getFinal().getNextBracketNode()).isEmpty();
    }

    @Test
    void testPoulesInTournament() {
        var tournament = new Tournament(1, 1, 64);
        var poule = new Poule("name", 5);
        tournament.addPoule(poule);
        assertThat(tournament.getPoules()).hasSize(1);
        assertThat(tournament.getPoules()).containsExactly(poule);
    }
}
