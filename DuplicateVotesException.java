/**
 * A class representing a duplicate vote exception.
 */
public class DuplicateVotesException extends Exception {
    private String name;

    DuplicateVotesException(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}