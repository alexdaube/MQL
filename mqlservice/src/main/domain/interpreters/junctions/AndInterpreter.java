package domain.interpreters.junctions;

import domain.interpreters.BaseInterpreter;
import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SuggestionBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndInterpreter extends BaseInterpreter implements Interpreter {
    static final Pattern AND_PATTERN = Pattern.compile("^[\\w]+");

    public AndInterpreter(Keywords keywords) {
        super(keywords);
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(AND_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(AND_PATTERN);
                queryBuilder.and();
                return true;
            }
        }
        return false;
    }
}
