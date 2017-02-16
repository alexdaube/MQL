package domain.interpreters;

import builders.KeywordsBuilder;
import domain.StringQuery;
import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.keywords.Keywords;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JunctionInterpreterTest {
    private static final String JUNCTION = "JUNCTION";
    @Mock
    private QueryBuilder queryBuilder;
    private Query validQuery;
    private Query invalidQuery;
    private JunctionInterpreter junctionInterpreter;
    private Keywords junctions;

    @Before
    public void setUp() throws Exception {
        junctions = KeywordsBuilder.create().with(JUNCTION).build();
        junctionInterpreter = new JunctionInterpreter(junctions, junctions);
        validQuery = new StringQuery(JUNCTION + " ");
        invalidQuery = new StringQuery("S" + JUNCTION);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = junctionInterpreter.interpret(validQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenTheJunctionShouldBeAddedToTheBuilder() throws Exception {
        junctionInterpreter.interpret(validQuery, queryBuilder);
        verify(queryBuilder).and();
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = junctionInterpreter.interpret(invalidQuery, queryBuilder);
        assertFalse(returnValue);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        junctionInterpreter.interpret(invalidQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }
}