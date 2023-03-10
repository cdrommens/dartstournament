package be.dcharmonie.dartstournament.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RoundNodeTest {

    @Test
    void testRoundNodeEquals () {
        RoundNode node1 = new RoundNode(Round.FINAL_8, 1);
        assertThat(node1).isEqualTo(node1);
    }

    @Test
    void testRoundNodeDifferentObjectsEquals () {
        RoundNode node1 = new RoundNode(Round.FINAL_8, 1);
        RoundNode node2 = new RoundNode(Round.FINAL_8, 1);
        assertThat(node1).isEqualTo(node2);
    }

    @Test
    void testRoundNodeDifferentClassEquals () {
        FinalNode node1 = new FinalNode();
        RoundNode node2 = new RoundNode(Round.FINAL_8, 1);
        assertThat(node2.equals(node1)).isFalse();
    }

    @Test
    void testRoundNodeNotEquals () {
        RoundNode node1 = new RoundNode(Round.FINAL_8, 1);
        RoundNode node2 = new RoundNode(Round.FINAL_8, 2);
        assertThat(node1).isNotEqualTo(node2);
        node1 = new RoundNode(Round.FINAL_16, 2);
        node2 = new RoundNode(Round.FINAL_8, 2);
        assertThat(node1).isNotEqualTo(node2);
    }

    @Test
    void testRoundNodeHasCode () {
        RoundNode node1 = new RoundNode(Round.FINAL_8, 1);
        RoundNode node2 = new RoundNode(Round.FINAL_8, 1);
        assertThat(node1).hasSameHashCodeAs(node2);
    }
}
