package domain.interpreters.operators;

import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.OperatorType;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SuggestionBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EqualInterpreter implements Interpreter {
    static final Pattern EQUAL_PATTERN = Pattern.compile("^[\\w-]+|^=");
    private final Keywords keywords;

    public EqualInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(EQUAL_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(EQUAL_PATTERN);
                queryBuilder.withOperator(OperatorType.EQUAL);
                return true;
            }
        }
        return false;
    }

    @Override
    public void suggest(SuggestionBuilder suggestionBuilder) {
        suggestionBuilder.withQueryMatching(keywords).withAllowed(keywords);
    }
}
