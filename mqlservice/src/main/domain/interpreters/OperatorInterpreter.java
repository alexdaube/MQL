package domain.interpreters;

import domain.Query;
import domain.interpreters.operators.*;
import domain.keywords.KeywordsResolver;
import domain.querybuilder.QueryBuilder;

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

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        return interpreters.interpret(query, queryBuilder);
    }
}
