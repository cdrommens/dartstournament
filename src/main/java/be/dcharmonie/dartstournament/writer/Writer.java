package be.dcharmonie.dartstournament.writer;

public interface Writer<T> {
    void write(T render, String filename);
}
