package domain.interpreters.values;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IntegerInterpreterTest {
    private static final String NUMBER_VALUE = "9.98";
    @Mock
    private QueryBuilder queryBuilder;
    private StringQuery validNumberQuery;
    private StringQuery invalidNumberQuery;
    private IntegerInterpreter integerInterpreter;

    @Before
    public void setUp() throws Exception {
        integerInterpreter = new IntegerInterpreter();
        validNumberQuery = new StringQuery(NUMBER_VALUE);
        invalidNumberQuery = new StringQuery("a" + NUMBER_VALUE);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        integerInterpreter.interpret(invalidNumberQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }

    @Test
    public void givenAValidNumberQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = integerInterpreter.interpret(validNumberQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidNumberQueryAndABuilder_whenInterpreting_thenTheValueIsPassedToTheBuilder() throws Exception {
        integerInterpreter.interpret(validNumberQuery, queryBuilder);
        verify(queryBuilder).withInteger(anyInt());
    }

    @Test
    public void givenAnInvalidNumberQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = integerInterpreter.interpret(invalidNumberQuery, queryBuilder);
        assertFalse(returnValue);
    }
}