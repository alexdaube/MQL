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
public class EntityInterpreterTest {
    private static final String ENTITY = "ENTITY";
    @Mock
    private QueryBuilder queryBuilder;
    private StringQuery validQuery;
    private StringQuery invalidQuery;
    private EntityInterpreter entityInterpreter;
    private Keywords entities;

    @Before
    public void setUp() throws Exception {
        entities = KeywordsBuilder.create().with(ENTITY).build();
        entityInterpreter = new EntityInterpreter(entities);
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