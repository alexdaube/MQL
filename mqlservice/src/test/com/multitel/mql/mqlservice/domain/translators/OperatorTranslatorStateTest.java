package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.InvalidQueryException;
import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class OperatorTranslatorStateTest {
    private static final StringQuery OPERATOR_QUERY = new StringQuery("is 9.99");
    private static final StringQuery ATTRIBUTE_QUERY = new StringQuery("name is 9.99");
    private static final StringQuery VALUE_QUERY = new StringQuery("9.99");
    private static final QueryBuilder QUERY_BUILDER = QueryBuilder.create();
    private OperatorTranslatorState operatorTranslatorState;
    private QueryTranslator queryTranslator;

    @Before
    public void setUp() throws Exception {
        queryTranslator = mock(QueryTranslator.class);
        operatorTranslatorState = new OperatorTranslatorState(queryTranslator);
        willReturn(QUERY_BUILDER).given(queryTranslator).getQueryBuilder();
    }

    @Test
    public void givenAValueQuery_whenTranslating_thenTheStateShouldBeChangedToValue() throws Exception {
        operatorTranslatorState.translate(VALUE_QUERY);
        verify(queryTranslator).changeState(any(ValueTranslatorState.class));
    }

    @Test
    public void givenAnOperatorQuery_whenTranslating_thenTheStateShouldNotBeChanged() throws Exception {
        operatorTranslatorState.translate(OPERATOR_QUERY);
        verify(queryTranslator, never()).changeState(any());
    }

    @Test
    public void givenAnOperatorQuery_whenTranslating_thenReturnFalse() throws Exception {
        boolean returnedValue = operatorTranslatorState.translate(OPERATOR_QUERY);
        assertFalse(returnedValue);
    }

    @Test(expected = InvalidQueryException.class)
    public void givenANonOperatorQuery_whenTranslating_thenThrowAnInvalidQueryException() throws Exception {
        operatorTranslatorState.translate(ATTRIBUTE_QUERY);
    }
}