package domain.interpreters;

import domain.interpreters.junctions.AndInterpreter;
import domain.interpreters.junctions.OrInterpreter;
import domain.interpreters.operators.*;
import domain.interpreters.values.DateInterpreter;
import domain.interpreters.values.DecimalInterpreter;
import domain.interpreters.values.IntegerInterpreter;
import domain.interpreters.values.VarcharInterpreter;
import domain.keywords.Keyword;
import domain.keywords.KeywordsResolver;

public class InterpreterFactory {

    public static Interpreter allOperators(KeywordsResolver keywordsResolver) {
        return new CompositeInterpreter(
                new EqualInterpreter(keywordsResolver.resolveType(Keyword.Type.EQUAL)),
                new GreaterInterpreter(keywordsResolver.resolveType(Keyword.Type.GREATER)),
                new LessInterpreter(keywordsResolver.resolveType(Keyword.Type.LESS)),
                new BetweenInterpreter(keywordsResolver.resolveType(Keyword.Type.BETWEEN)),
                new OtherInterpreter(keywordsResolver.resolveType(Keyword.Type.OTHER)),
                new LikeInterpreter(keywordsResolver.resolveType(Keyword.Type.LIKE)),
                new NotInterpreter(keywordsResolver.resolveType(Keyword.Type.NOT))
        );
    }

    public static Interpreter allValues() {
        return new CompositeInterpreter(
                new VarcharInterpreter(), new DateInterpreter(), new DecimalInterpreter(), new IntegerInterpreter()
        );
    }

    public static Interpreter allJunctions(KeywordsResolver keywordsResolver) {
        return new CompositeInterpreter(
                new OrInterpreter(keywordsResolver.resolveType(Keyword.Type.OR)),
                new AndInterpreter(keywordsResolver.resolveType(Keyword.Type.AND))
        );
    }

    public static Interpreter basicOperators(KeywordsResolver keywordsResolver) {
        return new CompositeInterpreter(
                new EqualInterpreter(keywordsResolver.resolveType(Keyword.Type.EQUAL)),
                new GreaterInterpreter(keywordsResolver.resolveType(Keyword.Type.GREATER)),
                new LessInterpreter(keywordsResolver.resolveType(Keyword.Type.LESS)),
                new BetweenInterpreter(keywordsResolver.resolveType(Keyword.Type.BETWEEN)),
                new LikeInterpreter(keywordsResolver.resolveType(Keyword.Type.LIKE)),
                new NotInterpreter(keywordsResolver.resolveType(Keyword.Type.NOT))
        );
    }
}
