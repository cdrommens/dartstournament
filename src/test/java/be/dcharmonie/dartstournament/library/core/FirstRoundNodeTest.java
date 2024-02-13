package be.dcharmonie.dartstournament.library.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class FirstRoundNodeTest {

    @Test
    void testFirstRoundNodeEquals () {
        FirstRoundNode node1 = new FirstRoundNode(Round.FINAL_8, 1);
        assertThat(node1).isEqualTo(node1);
    }

    @Test
    void testFirstRoundNodeDifferentObjectsEquals () {
        FirstRoundNode node1 = new FirstRoundNode(Round.FINAL_8, 1);
        FirstRoundNode node2 = new FirstRoundNode(Round.FINAL_8, 1);
        assertThat(node1).isEqualTo(node2);
    }
    @Test
    void testFirstRoundNodeDifferentClassEquals () {
        FinalNode node1 = new FinalNode();
        FirstRoundNode node2 = new FirstRoundNode(Round.FINAL_8, 1);
        assertThat(node2.equals(node1)).isFalse();
    }

    @Test
    void testFirstRoundNodeNotEquals () {
        FirstRoundNode node1 = new FirstRoundNode(Round.FINAL_8, 1);
        FirstRoundNode node2 = new FirstRoundNode(Round.FINAL_8, 2);
        assertThat(node1).isNotEqualTo(node2);
        node1 = new FirstRoundNode(Round.FINAL_16, 2);
        node2 = new FirstRoundNode(Round.FINAL_8, 2);
        assertThat(node1).isNotEqualTo(node2);
    }

    @Test
    void testFirstRoundNodeHasCode () {
        FirstRoundNode node1 = new FirstRoundNode(Round.FINAL_8, 1);
        FirstRoundNode node2 = new FirstRoundNode(Round.FINAL_8, 1);
        assertThat(node1).hasSameHashCodeAs(node2);
    }

    @Test
    void testUnsupportedOperation() {
        FirstRoundNode node1 = new FirstRoundNode(Round.FINAL_8, 3);
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->node1.setPreviousFirstBracketNode(null));
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->node1.setPreviousSecondBracketNode(null));
    }

}
