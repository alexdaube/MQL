package domain.interpreters;

import domain.interpreters.junctions.AndInterpreter;
import domain.interpreters.junctions.OrInterpreter;
import domain.interpreters.operators.*;
import domain.interpreters.values.DateInterpreter;
import domain.interpreters.values.DecimalInterpreter;
import domain.interpreters.values.IntegerInterpreter;
import domain.interpreters.values.VarcharInterpreter;
import domain.keywords.Keywords;
import domain.keywords.KeywordsResolver;

public class InterpreterFactory {
    public static Interpreter allOperators(KeywordsResolver keywordsResolver) {
        return new CompositeInterpreter(
                new EqualInterpreter(keywordsResolver.resolveType(Keywords.Type.EQUALS)),
                new GreaterInterpreter(keywordsResolver.resolveType(Keywords.Type.GREATER)),
                new LessInterpreter(keywordsResolver.resolveType(Keywords.Type.LESS)),
                new BetweenInterpreter(keywordsResolver.resolveType(Keywords.Type.BETWEEN)),
                new OtherInterpreter(keywordsResolver.resolveType(Keywords.Type.OTHER))
        );
    }

    public static Interpreter allValues() {
        return new CompositeInterpreter(
                new VarcharInterpreter(), new DateInterpreter(), new DecimalInterpreter(), new IntegerInterpreter()
        );
    }

    public static Interpreter allJunctions(KeywordsResolver keywordsResolver) {
        return new CompositeInterpreter(
                new OrInterpreter(keywordsResolver.resolveType(Keywords.Type.OR)),
                new AndInterpreter(keywordsResolver.resolveType(Keywords.Type.AND))
        );
    }

    public static Interpreter basicOperators(KeywordsResolver keywordsResolver) {
        return new CompositeInterpreter(
                new EqualInterpreter(keywordsResolver.resolveType(Keywords.Type.EQUALS)),
                new GreaterInterpreter(keywordsResolver.resolveType(Keywords.Type.GREATER)),
                new LessInterpreter(keywordsResolver.resolveType(Keywords.Type.LESS)),
                new BetweenInterpreter(keywordsResolver.resolveType(Keywords.Type.BETWEEN))
        );
    }
}
