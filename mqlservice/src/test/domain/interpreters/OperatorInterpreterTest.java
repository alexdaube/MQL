package domain.interpreters;

import builders.KeywordsBuilder;
import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.keyword.Keywords;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OperatorInterpreterTest {
    private static final String OPERATOR = "OPERATOR";
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Interpreter operatorInterpreters;
    private StringQuery validQuery;
    private StringQuery invalidQuery;
    private OperatorInterpreter operatorInterpreter;
    private Keywords operators;

    @Before
    public void setUp() throws Exception {
        operators = KeywordsBuilder.create().with(OPERATOR).build();
        operatorInterpreter = new OperatorInterpreter(operatorInterpreters);
        validQuery = new StringQuery(OPERATOR + " ");
        invalidQuery = new StringQuery("S" + OPERATOR);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = operatorInterpreter.interpret(validQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenTheOperatorShouldBeAddedToTheBuilder() throws Exception {
        operatorInterpreter.interpret(validQuery, queryBuilder);
        verify(queryBuilder).withEquals();
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = operatorInterpreter.interpret(invalidQuery, queryBuilder);
        assertFalse(returnValue);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        operatorInterpreter.interpret(invalidQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }
}