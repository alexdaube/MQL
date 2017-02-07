package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.InvalidQueryException;
import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class ValueTranslatorStateTest {
    private static final StringQuery EMPTY_QUERY = new StringQuery("   ");
    private StringQuery junctionQuery;
    private StringQuery attributeQuery;
    private StringQuery valueQuery;
    private QueryBuilder queryBuilder;
    private ValueTranslatorState valueTranslatorState;
    private QueryTranslator queryTranslator;

    @Before
    public void setUp() throws Exception {
        junctionQuery = new StringQuery("and Employee name is 9.99");
        attributeQuery = new StringQuery("name is 9.99");
        valueQuery = new StringQuery("9.99");
        queryBuilder = QueryBuilder.create();
        queryTranslator = mock(QueryTranslator.class);
        valueTranslatorState = new ValueTranslatorState(queryTranslator);
        willReturn(queryBuilder).given(queryTranslator).getQueryBuilder();
    }

    @Test
    public void givenAValueQuery_whenTranslating_thenTheStateShouldRemainUnchanged() throws Exception {
        valueTranslatorState.translate(valueQuery);
        verify(queryTranslator, never()).changeState(any());
    }

    @Test
    public void givenAJunctionQuery_whenTranslating_thenTheStateShouldChangeToJunction() throws Exception {
        valueTranslatorState.translate(junctionQuery);
        verify(queryTranslator, never()).changeState(any(JunctionTranslatorState.class));
    }

    @Test
    public void givenAnValueQuery_whenTranslating_thenReturnFalse() throws Exception {
        boolean returnedValue = valueTranslatorState.translate(valueQuery);
        assertFalse(returnedValue);
    }

    @Test
    public void givenAJunctionQuery_whenTranslating_thenReturnFalse() throws Exception {
        boolean returnedValue = valueTranslatorState.translate(junctionQuery);
        assertFalse(returnedValue);
    }

    @Test
    public void givenAnEmptyQuery_whenTranslating_thenReturnTrue() throws Exception {
        boolean returnedValue = valueTranslatorState.translate(EMPTY_QUERY);
        assertTrue(returnedValue);
    }

    @Test(expected = InvalidQueryException.class)
    public void givenAnUnsupportedQuery_whenTranslating_thenThrowAnInvalidQueryException() throws Exception {
        valueTranslatorState.translate(attributeQuery);
    }
}