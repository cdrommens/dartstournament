package be.dcharmonie.dartstournament.library.renderer.image.layout;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BracketSchemaPaperPrinterTest {

    @Test
    void testPaperSize() {
        BracketSchemaPaperPrinter printer = new BracketSchemaPaperPrinter(4, 2);
        assertThat(printer.getPaperFormat()).isEqualTo(PaperFormat.A4);
        printer = new BracketSchemaPaperPrinter(32, 5);
        assertThat(printer.getPaperFormat()).isEqualTo(PaperFormat.A4);
        printer = new BracketSchemaPaperPrinter(64, 6);
        assertThat(printer.getPaperFormat()).isEqualTo(PaperFormat.A3);
    }
}
