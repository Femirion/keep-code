package gateway.parser;

import java.util.List;

public interface Parser<T> {
    List<T> parse(String input);
    default void handleError(String input, Exception ex) {}
}
