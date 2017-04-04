package domain.interpreters.operators;

import domain.interpreters.BaseInterpreter;
import domain.interpreters.Interpreter;
import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.OperatorType;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SuggestionBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class LessInterpreter extends BaseInterpreter implements Interpreter {
    static final Pattern LESS_PATTERN = Pattern.compile("^[\\w-]+|^<");

    public LessInterpreter(Keywords keywords) {
        super(keywords);
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(LESS_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            if (keywords.contains(match)) {
                query.removeFirstMatch(LESS_PATTERN);
                queryBuilder.withOperator(OperatorType.LESS);
                return true;
            }
        }
        return false;
    }
}
