package domain.interpreters.values;

import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class DateInterpreterTest {
    private static final String SMALL_DATE = "1992-10-22";
    private static final String MEDIUM_DATE = SMALL_DATE + " 18:35:30";
    private static final String FULL_DATE = MEDIUM_DATE + ".999";
    @Mock
    private QueryBuilder queryBuilder;
    private StringQuery validDateQuery;
    private StringQuery invalidDateQuery;
    private DateInterpreter dateInterpreter;

    @Before
    public void setUp() throws Exception {
        dateInterpreter = new DateInterpreter();
        validDateQuery = new StringQuery(FULL_DATE);
        invalidDateQuery = new StringQuery("a" + FULL_DATE);
    }

    @Test
    public void givenAValidDateQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = dateInterpreter.interpret(validDateQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidDateQueryAndABuilder_whenInterpreting_thenTheValueIsAddedToTheBuilder() throws Exception {
        dateInterpreter.interpret(validDateQuery, queryBuilder);
        verify(queryBuilder).withDate(any(Date.class));
    }

    @Test
    public void givenAnInvalidDateQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = dateInterpreter.interpret(invalidDateQuery, queryBuilder);
        assertFalse(returnValue);
    }

    @Test
    public void givenAnInvalidDateQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        dateInterpreter.interpret(invalidDateQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }
}
