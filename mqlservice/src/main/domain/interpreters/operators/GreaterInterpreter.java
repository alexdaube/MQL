package domain.interpreters.operators;

import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.OperatorType;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SuggestionBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GreaterInterpreter implements Interpreter {
    static final Pattern GREATER_PATTERN = Pattern.compile("^[\\w-]+|^>");
    private final Keywords keywords;

    public GreaterInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(GREATER_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(GREATER_PATTERN);
                queryBuilder.withOperator(OperatorType.GREATER);
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
