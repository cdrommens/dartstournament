package be.dcharmonie.dartstournament.library.writer;

public interface Writer<T> {
    void write(T render, String filename);
}
