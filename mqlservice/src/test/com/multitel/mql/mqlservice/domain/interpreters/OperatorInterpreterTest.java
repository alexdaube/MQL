package com.multitel.mql.mqlservice.domain.interpreters;

import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class OperatorInterpreterTest {
    private static final String OPERATOR = "OPERATOR";
    private StringQuery validQuery;
    private StringQuery invalidQuery;
    private OperatorInterpreter operatorInterpreter;
    private Set<String> operators;
    private QueryBuilder queryBuilder;

    @Before
    public void setUp() throws Exception {
        operators = new HashSet<>();
        operators.add(OPERATOR);
        operatorInterpreter = new OperatorInterpreter(operators);
        queryBuilder = mock(QueryBuilder.class);
        validQuery = new StringQuery(OPERATOR + " ");
        invalidQuery = new StringQuery("S" + OPERATOR);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = operatorInterpreter.interpret(validQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenTheOperatorShouldBeAddedToTheBuilder() throws Exception {
        operatorInterpreter.interpret(validQuery, queryBuilder);
        verify(queryBuilder).withOperator(OPERATOR);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = operatorInterpreter.interpret(invalidQuery, queryBuilder);
        assertFalse(returnValue);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        operatorInterpreter.interpret(invalidQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }
}