package be.dcharmonie.dartstournament.core;

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
                        "HALF_FINAL - Game 1",
                        "HALF_FINAL - Game 2"
                );
        assertThat(tournament.getRoundNodesGroupedByRound()).containsOnlyKeys(Round.FINAL, Round.HALF_FINAL);
        assertThat(tournament.getRoundNodesGroupedByRound().get(Round.FINAL)).hasSize(1);
        assertThat(tournament.getRoundNodesGroupedByRound().get(Round.HALF_FINAL)).hasSize(2);
        assertThat(tournament.getAllBracketNodes().map(BracketNode::getDescription).collect(Collectors.toList())).containsExactlyInAnyOrder(
                "HALF_FINAL - Game 1",
                "HALF_FINAL - Game 2",
                "This is the final!"
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
                        "FINAL_32 - Game 1",
                        "FINAL_32 - Game 2",
                        "FINAL_32 - Game 3",
                        "FINAL_32 - Game 4",
                        "FINAL_32 - Game 5",
                        "FINAL_32 - Game 6",
                        "FINAL_32 - Game 7",
                        "FINAL_32 - Game 8",
                        "FINAL_32 - Game 9",
                        "FINAL_32 - Game 10",
                        "FINAL_32 - Game 11",
                        "FINAL_32 - Game 12",
                        "FINAL_32 - Game 13",
                        "FINAL_32 - Game 14",
                        "FINAL_32 - Game 15",
                        "FINAL_32 - Game 16",
                        "FINAL_32 - Game 17",
                        "FINAL_32 - Game 18",
                        "FINAL_32 - Game 19",
                        "FINAL_32 - Game 20",
                        "FINAL_32 - Game 21",
                        "FINAL_32 - Game 22",
                        "FINAL_32 - Game 23",
                        "FINAL_32 - Game 24",
                        "FINAL_32 - Game 25",
                        "FINAL_32 - Game 26",
                        "FINAL_32 - Game 27",
                        "FINAL_32 - Game 28",
                        "FINAL_32 - Game 29",
                        "FINAL_32 - Game 30",
                        "FINAL_32 - Game 31",
                        "FINAL_32 - Game 32"
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
                "FINAL_32 - Game 1",
                "FINAL_32 - Game 2",
                "FINAL_32 - Game 3",
                "FINAL_32 - Game 4",
                "FINAL_32 - Game 5",
                "FINAL_32 - Game 6",
                "FINAL_32 - Game 7",
                "FINAL_32 - Game 8",
                "FINAL_32 - Game 9",
                "FINAL_32 - Game 10",
                "FINAL_32 - Game 11",
                "FINAL_32 - Game 12",
                "FINAL_32 - Game 13",
                "FINAL_32 - Game 14",
                "FINAL_32 - Game 15",
                "FINAL_32 - Game 16",
                "FINAL_32 - Game 17",
                "FINAL_32 - Game 18",
                "FINAL_32 - Game 19",
                "FINAL_32 - Game 20",
                "FINAL_32 - Game 21",
                "FINAL_32 - Game 22",
                "FINAL_32 - Game 23",
                "FINAL_32 - Game 24",
                "FINAL_32 - Game 25",
                "FINAL_32 - Game 26",
                "FINAL_32 - Game 27",
                "FINAL_32 - Game 28",
                "FINAL_32 - Game 29",
                "FINAL_32 - Game 30",
                "FINAL_32 - Game 31",
                "FINAL_32 - Game 32",
                "FINAL_16 - Game 1",
                "FINAL_16 - Game 2",
                "FINAL_16 - Game 3",
                "FINAL_16 - Game 4",
                "FINAL_16 - Game 5",
                "FINAL_16 - Game 6",
                "FINAL_16 - Game 7",
                "FINAL_16 - Game 8",
                "FINAL_16 - Game 9",
                "FINAL_16 - Game 10",
                "FINAL_16 - Game 11",
                "FINAL_16 - Game 12",
                "FINAL_16 - Game 13",
                "FINAL_16 - Game 14",
                "FINAL_16 - Game 15",
                "FINAL_16 - Game 16",
                "FINAL_8 - Game 1",
                "FINAL_8 - Game 2",
                "FINAL_8 - Game 3",
                "FINAL_8 - Game 4",
                "FINAL_8 - Game 5",
                "FINAL_8 - Game 6",
                "FINAL_8 - Game 7",
                "FINAL_8 - Game 8",
                "QUARTER_FINAL - Game 1",
                "QUARTER_FINAL - Game 2",
                "QUARTER_FINAL - Game 3",
                "QUARTER_FINAL - Game 4",
                "HALF_FINAL - Game 1",
                "HALF_FINAL - Game 2",
                "This is the final!"
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
