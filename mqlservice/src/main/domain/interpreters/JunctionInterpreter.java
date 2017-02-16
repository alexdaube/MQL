package domain.interpreters;

import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.interpreters.junctions.AndInterpreter;
import domain.interpreters.junctions.OrInterpreter;
import domain.keywords.Keywords;

public class JunctionInterpreter implements Interpreter {
    private final AndInterpreter andInterpreter;
    private final OrInterpreter orInterpreter;

    public JunctionInterpreter(Keywords andKeywords, Keywords orKeywords) {
        this.andInterpreter = new AndInterpreter(andKeywords);
        this.orInterpreter = new OrInterpreter(orKeywords);
    }

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        return andInterpreter.interpret(query, queryBuilder) || orInterpreter.interpret(query, queryBuilder);
    }
}
