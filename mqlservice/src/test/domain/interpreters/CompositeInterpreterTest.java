package domain.interpreters;

import domain.query.Query;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SuggestionBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CompositeInterpreterTest {
    @Mock
    private Interpreter firstInterpreter;
    @Mock
    private Interpreter secondInterpreter;
    @Mock
    private Query query;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    SuggestionBuilder suggestionBuilder;
    private CompositeInterpreter compositeInterpreter;

    @Before
    public void setUp() throws Exception {
        compositeInterpreter = new CompositeInterpreter(firstInterpreter, secondInterpreter);
    }

    @Test
    public void givenAQueryAndAQueryBuilder_whenInterpreting_thenTheInterpretersAreCalled() throws Exception {
        compositeInterpreter.interpret(query, queryBuilder);
        verify(firstInterpreter).interpret(query, queryBuilder);
        verify(secondInterpreter).interpret(query, queryBuilder);
    }

    @Test
    public void givenASuggestionBuilder_whenSuggest_thenCallsSuggestForEachChildInterpreter() {
        compositeInterpreter.suggest(suggestionBuilder);
        verify(firstInterpreter).suggest(suggestionBuilder);
        verify(secondInterpreter).suggest(suggestionBuilder);
    }
}