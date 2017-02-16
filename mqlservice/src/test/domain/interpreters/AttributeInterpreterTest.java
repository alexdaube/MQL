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
public class AttributeInterpreterTest {
    private static final String ATTRIBUTE = "ATTRIBUTE";
    @Mock
    private QueryBuilder queryBuilder;
    private StringQuery validQuery;
    private StringQuery invalidQuery;
    private AttributeInterpreter attributeInterpreter;
    private Keywords attributes;

    @Before
    public void setUp() throws Exception {
        attributes = KeywordsBuilder.create().with(ATTRIBUTE).build();
        attributeInterpreter = new AttributeInterpreter(attributes);
        validQuery = new StringQuery(ATTRIBUTE + " ");
        invalidQuery = new StringQuery("S" + ATTRIBUTE);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = attributeInterpreter.interpret(validQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenTheAttributeShouldBeAddedToTheBuilder() throws Exception {
        attributeInterpreter.interpret(validQuery, queryBuilder);
        verify(queryBuilder).withAttribute(ATTRIBUTE);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = attributeInterpreter.interpret(invalidQuery, queryBuilder);
        assertFalse(returnValue);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        attributeInterpreter.interpret(invalidQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }
}