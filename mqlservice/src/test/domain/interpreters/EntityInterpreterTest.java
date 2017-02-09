package domain.interpreters;

import domain.QueryBuilder;
import domain.StringQuery;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class EntityInterpreterTest {
    private static final String ENTITY = "ENTITY";
    private StringQuery validQuery;
    private StringQuery invalidQuery;
    private EntityInterpreter entityInterpreter;
    private Set<String> entities;
    private QueryBuilder queryBuilder;

    @Before
    public void setUp() throws Exception {
        entities = new HashSet<>();
        entities.add(ENTITY);
        entityInterpreter = new EntityInterpreter(entities);
        queryBuilder = mock(QueryBuilder.class);
        validQuery = new StringQuery(ENTITY + " ");
        invalidQuery = new StringQuery("S" + ENTITY);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = entityInterpreter.interpret(validQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenTheEntityShouldBeAddedToTheBuilder() throws Exception {
        entityInterpreter.interpret(validQuery, queryBuilder);
        verify(queryBuilder).withEntity(ENTITY);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = entityInterpreter.interpret(invalidQuery, queryBuilder);
        assertFalse(returnValue);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        entityInterpreter.interpret(invalidQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }
}