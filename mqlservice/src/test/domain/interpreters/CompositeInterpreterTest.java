package domain.interpreters;

import domain.Query;
import domain.querybuilder.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
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
}