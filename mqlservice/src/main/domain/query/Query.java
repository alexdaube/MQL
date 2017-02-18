package domain.query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Query {

    void strip();

    Matcher findMatches(Pattern pattern);

    void removeFirstMatch(Pattern pattern);

    boolean isEqualTo(String other);

    boolean isEmpty();
}
