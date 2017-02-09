package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.InvalidQueryException;
import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InitialTranslatorStateTest {
    private StringQuery operatorQuery;
    private StringQuery entityQuery;
    private QueryBuilder queryBuilder;
    private InitialTranslatorState initialTranslatorState;
    private QueryTranslator queryTranslator;

    @Before
    public void setUp() throws Exception {
        operatorQuery = new StringQuery("is 9.99");
        entityQuery = new StringQuery("Employee name is 9.99");
        queryBuilder = QueryBuilder.create();
        queryTranslator = mock(QueryTranslator.class);
        initialTranslatorState = new InitialTranslatorState(queryTranslator);
        willReturn(queryBuilder).given(queryTranslator).getQueryBuilder();
    }

    @Test
    public void givenAnEntityQuery_whenTranslating_thenChangeTheStateToEntity() throws Exception {
        initialTranslatorState.translate(entityQuery);
        verify(queryTranslator).changeState(any(EntityTranslatorState.class));
    }

    @Test
    public void givenAnEntityQuery_whenTranslating_thenReturnFalse() throws Exception {
        boolean returnedValue = initialTranslatorState.translate(entityQuery);
        assertFalse(returnedValue);
    }

    @Test(expected = InvalidQueryException.class)
    public void givenANonEntityQuery_whenTranslating_thenThrowAnInvalidQueryException() throws Exception {
        initialTranslatorState.translate(operatorQuery);
    }
}