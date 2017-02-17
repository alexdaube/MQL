package domain.interpreters;

import domain.interpreters.junctions.AndInterpreter;
import domain.interpreters.junctions.OrInterpreter;
import domain.interpreters.operators.*;
import domain.interpreters.values.DateInterpreter;
import domain.interpreters.values.DecimalInterpreter;
import domain.interpreters.values.IntegerInterpreter;
import domain.interpreters.values.VarcharInterpreter;
import domain.keywords.KeywordsResolver;

public class InterpreterFactory {
    public static Interpreter allOperators(KeywordsResolver keywordsResolver) {
        return new CompositeInterpreter(
                new EqualInterpreter(keywordsResolver.resolveEqualOperators()),
                new GreaterInterpreter(keywordsResolver.resolveGreaterOperators()),
                new LessInterpreter(keywordsResolver.resolveLessOperators()),
                new BetweenInterpreter(keywordsResolver.resolveBetweenOperators()),
                new OtherInterpreter(keywordsResolver.resolveOtherOperators())
        );
    }

    public static Interpreter allValues() {
        return new CompositeInterpreter(
                new VarcharInterpreter(), new DateInterpreter(), new DecimalInterpreter(), new IntegerInterpreter()
        );
    }

    public static Interpreter allJunctions(KeywordsResolver keywordsResolver) {
        return new CompositeInterpreter(
                new OrInterpreter(keywordsResolver.resolveOrJunctions()),
                new AndInterpreter(keywordsResolver.resolveAndJunctions())
        );
    }

    public static Interpreter basicOperators(KeywordsResolver keywordsResolver) {
        return new CompositeInterpreter(
                new EqualInterpreter(keywordsResolver.resolveEqualOperators()),
                new GreaterInterpreter(keywordsResolver.resolveGreaterOperators()),
                new LessInterpreter(keywordsResolver.resolveLessOperators()),
                new BetweenInterpreter(keywordsResolver.resolveBetweenOperators())
        );
    }
}
