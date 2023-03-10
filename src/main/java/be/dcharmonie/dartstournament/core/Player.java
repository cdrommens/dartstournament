package be.dcharmonie.dartstournament.core;

/**
 *
 */
public record Player(String firstName, String lastName) {

    public String getFullName() {
        return String.format("%s %s", lastName, firstName);
    }
}
