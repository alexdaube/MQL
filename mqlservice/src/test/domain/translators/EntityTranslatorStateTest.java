package domain.translators;

import domain.InvalidQueryException;
import domain.QueryBuilder;
import domain.StringQuery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EntityTranslatorStateTest {
    private StringQuery operatorQuery;
    private StringQuery attributeQuery;
    private QueryBuilder queryBuilder;
    private EntityTranslatorState entityTranslatorState;
    private QueryTranslator queryTranslator;

    @Before
    public void setUp() throws Exception {
        operatorQuery = new StringQuery("is 9.99");
        attributeQuery = new StringQuery("name is 9.99");
        queryBuilder = QueryBuilder.create();
        queryTranslator = mock(QueryTranslator.class);
        entityTranslatorState = new EntityTranslatorState(queryTranslator);
        willReturn(queryBuilder).given(queryTranslator).getQueryBuilder();
    }

    @Test
    public void givenAnAttributeQuery_whenTranslating_thenChangeTheStateToAttribute() throws Exception {
        entityTranslatorState.translate(attributeQuery);
        verify(queryTranslator).changeState(any(AttributeTranslatorState.class));
    }

    @Test
    public void givenAnAttributeQuery_whenTranslating_thenReturnFalse() throws Exception {
        boolean returnedValue = entityTranslatorState.translate(attributeQuery);
        assertFalse(returnedValue);
    }

    @Test(expected = InvalidQueryException.class)
    public void givenANonAttributeQuery_whenTranslating_thenThrowAnInvalidQueryException() throws Exception {
        entityTranslatorState.translate(operatorQuery);
    }
}