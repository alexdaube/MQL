package domain.interpreters.values;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class VarcharInterpreterTest {
    private static final String VALUE = "A VALUE";
    private static final String STRING_VALUE = "\"" + VALUE + "\"";
    @Mock
    private QueryBuilder queryBuilder;
    private StringQuery validStringQuery;
    private StringQuery invalidStringQuery;
    private VarcharInterpreter varcharInterpreter;

    @Before
    public void setUp() throws Exception {
        varcharInterpreter = new VarcharInterpreter();
        validStringQuery = new StringQuery(STRING_VALUE);
        invalidStringQuery = new StringQuery("a" + STRING_VALUE);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = varcharInterpreter.interpret(validStringQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenTheValueIsAddedToTheBuilder() throws Exception {
        varcharInterpreter.interpret(validStringQuery, queryBuilder);
        verify(queryBuilder).withVarchar(VALUE);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = varcharInterpreter.interpret(invalidStringQuery, queryBuilder);
        assertFalse(returnValue);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        varcharInterpreter.interpret(invalidStringQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }
}
