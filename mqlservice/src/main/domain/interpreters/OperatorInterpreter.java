package domain.interpreters;

import domain.interpreters.operators.*;
import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.keyword.KeywordsResolver;

public class OperatorInterpreter implements Interpreter {
    private final Interpreter interpreters;

    public OperatorInterpreter(Interpreter interpreters) {
        this.interpreters = interpreters;
    }

    public OperatorInterpreter(KeywordsResolver keywordsResolver) {
        this(new CompositeInterpreter(
                new EqualInterpreter(keywordsResolver.resolveEqualOperators()),
                new GreaterInterpreter(keywordsResolver.resolveGreaterOperators()),
                new LessInterpreter(keywordsResolver.resolveLessOperators()),
                new BetweenInterpreter(keywordsResolver.resolveBetweenOperators()),
                new OtherInterpreter(keywordsResolver.resolveOtherOperators())
        ));
    }

    // TODO: 15/02/17 Change tests
    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        return interpreters.interpret(query, queryBuilder);
    }
}
