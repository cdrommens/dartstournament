package be.dcharmonie.dartstournament.library.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BracketNodeTest {

    @Test
    void testBracketNodeBiggerThanSameRound() {
        RoundNode node1 = new RoundNode(Round.FINAL_8, 4);
        RoundNode node2 = new RoundNode(Round.FINAL_8, 3);
        assertThat(node1.compareTo(node2)).isEqualTo(1);
        assertThat(node1.compareTo(null)).isEqualTo(1);
    }

    @Test
    void testBracketNodeSmallerThanSameRound() {
        RoundNode node1 = new RoundNode(Round.FINAL_8, 3);
        RoundNode node2 = new RoundNode(Round.FINAL_8, 4);
        assertThat(node1.compareTo(node2)).isEqualTo(-1);
    }

    @Test
    void testBracketNodeBiggerThanDifferentRound() {
        RoundNode node1 = new RoundNode(Round.FINAL_16, 4);
        RoundNode node2 = new RoundNode(Round.FINAL_8, 3);
        assertThat(node1.compareTo(node2)).isEqualTo(1);
        assertThat(node1.compareTo(null)).isEqualTo(1);
    }

    @Test
    void testBracketNodeSmallerThanDifferentRound() {
        RoundNode node1 = new RoundNode(Round.FINAL_8, 3);
        RoundNode node2 = new RoundNode(Round.FINAL_16, 4);
        assertThat(node1.compareTo(node2)).isEqualTo(-1);
    }
}
