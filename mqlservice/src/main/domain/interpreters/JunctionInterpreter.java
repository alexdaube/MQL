package domain.interpreters;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.junctions.AndInterpreter;
import domain.interpreters.junctions.OrInterpreter;
import domain.keyword.Keywords;

public class JunctionInterpreter implements Interpreter {
    private final AndInterpreter andInterpreter;
    private final OrInterpreter orInterpreter;

    public JunctionInterpreter(Keywords andKeywords, Keywords orKeywords) {
        this.andInterpreter = new AndInterpreter(andKeywords);
        this.orInterpreter = new OrInterpreter(orKeywords);
    }

    // TODO: 14/02/17 Adjust tests
    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        return andInterpreter.interpret(query, queryBuilder) || orInterpreter.interpret(query, queryBuilder);
    }
}
