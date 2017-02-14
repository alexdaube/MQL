package domain.interpreters.values;

import domain.QueryBuilder;
import domain.StringQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class NoValueInterpreterTest {
    @Mock
    private QueryBuilder anyBuilder;
    @Mock
    private StringQuery anyQuery;
    private NoValueInterpreter noValueInterpreter;


    @Before
    public void setUp() throws Exception {
        noValueInterpreter = new NoValueInterpreter();
    }

    @Test
    public void givenAnyQueryAndAnyBuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = noValueInterpreter.interpret(anyQuery, anyBuilder);
        assertFalse(returnValue);
    }
}