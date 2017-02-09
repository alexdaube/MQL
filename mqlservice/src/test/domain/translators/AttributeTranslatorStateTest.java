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

public class AttributeTranslatorStateTest {
    private StringQuery operatorQuery;
    private StringQuery attributeQuery;
    private QueryBuilder queryBuilder;
    private AttributeTranslatorState attributeTranslatorState;
    private QueryTranslator queryTranslator;

    @Before
    public void setUp() throws Exception {
        operatorQuery = new StringQuery("is 9.99");
        attributeQuery = new StringQuery("name is 9.99");
        queryBuilder = QueryBuilder.create();
        queryTranslator = mock(QueryTranslator.class);
        attributeTranslatorState = new AttributeTranslatorState(queryTranslator);
        willReturn(queryBuilder).given(queryTranslator).getQueryBuilder();
    }

    @Test
    public void givenAnOperatorQuery_whenTranslating_thenChangeTheStateToOperator() throws Exception {
        attributeTranslatorState.translate(operatorQuery);
        verify(queryTranslator).changeState(any(OperatorTranslatorState.class));
    }

    @Test
    public void givenAnOperatorQuery_whenTranslating_thenReturnFalse() throws Exception {
        boolean returnedValue = attributeTranslatorState.translate(operatorQuery);
        assertFalse(returnedValue);
    }

    @Test(expected = InvalidQueryException.class)
    public void givenANonOperatorQuery_whenTranslating_thenThrowAnInvalidQueryException() throws Exception {
        attributeTranslatorState.translate(attributeQuery);
    }
}