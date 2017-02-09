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

public class JunctionInterpreterTest {
    private static final String JUNCTION = "JUNCTION";
    private StringQuery validQuery;
    private StringQuery invalidQuery;
    private JunctionInterpreter junctionInterpreter;
    private Set<String> junctions;
    private QueryBuilder queryBuilder;

    @Before
    public void setUp() throws Exception {
        junctions = new HashSet<>();
        junctions.add(JUNCTION);
        junctionInterpreter = new JunctionInterpreter(junctions);
        queryBuilder = mock(QueryBuilder.class);
        validQuery = new StringQuery(JUNCTION + " ");
        invalidQuery = new StringQuery("S" + JUNCTION);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = junctionInterpreter.interpret(validQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenTheJunctionShouldBeAddedToTheBuilder() throws Exception {
        junctionInterpreter.interpret(validQuery, queryBuilder);
        verify(queryBuilder).withJunction(JUNCTION);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenReturnFalse() throws Exception {
        boolean returnValue = junctionInterpreter.interpret(invalidQuery, queryBuilder);
        assertFalse(returnValue);
    }

    @Test
    public void givenAnInvalidStringQueryAndABuilder_whenInterpreting_thenTheBuilderShouldNotBeCalled() throws Exception {
        junctionInterpreter.interpret(invalidQuery, queryBuilder);
        verifyZeroInteractions(queryBuilder);
    }
}