package domain.interpreters.values;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class DecimalInterpreterTest {
    private static final String STRING_VALUE = "9.99";
    @Mock
    private QueryBuilder queryBuilder;
    private StringQuery validDecimalQuery;
    private StringQuery invalidDecimalQuery;
    private DecimalInterpreter decimalInterpreter;

    @Before
    public void setUp() throws Exception {
        decimalInterpreter = new DecimalInterpreter();
        validDecimalQuery = new StringQuery(STRING_VALUE);
        invalidDecimalQuery = new StringQuery("a" + STRING_VALUE);
    }

    @Test
    public void givenAValidDecimalQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = decimalInterpreter.interpret(validDecimalQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidDecimalQueryAndABuilder_whenInterpreting_thenTheValueIsAddedToTheBuilder() throws Exception {
        decimalInterpreter.interpret(validDecimalQuery, queryBuilder);
        verify(queryBuilder).withDecimal(anyDouble());
    }

    @Test
    public void givenAnInvalidDecimalQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = decimalInterpreter.interpret(invalidDecimalQuery, queryBuilder);
        assertFalse(returnValue);
    }

    @Test
    public void givenAnInvalidDecimalQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        decimalInterpreter.interpret(invalidDecimalQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }
}
