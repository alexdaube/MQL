package com.multitel.mql.mqlservice.domain.interpreters;

import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class ValueInterpreterTest {
    private static final String STRING_VALUE = "\"STRING_VALUE\"";
    private static final String NUMBER_VALUE = "9.98";
    private StringQuery validStringQuery;
    private StringQuery invalidStringQuery;
    private ValueInterpreter valueInterpreter;
    private QueryBuilder queryBuilder;
    private StringQuery validNumberQuery;
    private StringQuery invalidNumberQuery;

    @Before
    public void setUp() throws Exception {
        valueInterpreter = new ValueInterpreter();
        queryBuilder = mock(QueryBuilder.class);
        validStringQuery = new StringQuery(STRING_VALUE);
        invalidStringQuery = new StringQuery("a" + STRING_VALUE);
        validNumberQuery = new StringQuery(NUMBER_VALUE);
        invalidNumberQuery = new StringQuery("a"+NUMBER_VALUE);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = valueInterpreter.interpret(validStringQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenTheEntityShouldBeAddedToTheBuilder() throws Exception {
        valueInterpreter.interpret(validStringQuery, queryBuilder);
        verify(queryBuilder).withValue(STRING_VALUE);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = valueInterpreter.interpret(invalidStringQuery, queryBuilder);
        assertFalse(returnValue);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        valueInterpreter.interpret(invalidStringQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }

    @Test
    public void givenAValidNumberQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = valueInterpreter.interpret(validNumberQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAnInvalidNumberQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = valueInterpreter.interpret(invalidNumberQuery, queryBuilder);
        assertFalse(returnValue);
    }
}