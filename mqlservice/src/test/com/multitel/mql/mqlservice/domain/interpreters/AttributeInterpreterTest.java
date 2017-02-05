package com.multitel.mql.mqlservice.domain.interpreters;

import com.multitel.mql.mqlservice.domain.QueryBuilder;
import com.multitel.mql.mqlservice.domain.StringQuery;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class AttributeInterpreterTest {
    private static final String ATTRIBUTE = "ATTRIBUTE";
    private StringQuery validQuery;
    private StringQuery invalidQuery;
    private AttributeInterpreter attributeInterpreter;
    private List<String> attributes;
    private QueryBuilder queryBuilder;

    @Before
    public void setUp() throws Exception {
        attributes = new LinkedList<>();
        attributes.add(ATTRIBUTE);
        attributeInterpreter = new AttributeInterpreter(attributes);
        queryBuilder = mock(QueryBuilder.class);
        validQuery = new StringQuery(ATTRIBUTE + " ");
        invalidQuery = new StringQuery("S" + ATTRIBUTE);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenReturnTrue() throws Exception {
        boolean returnValue = attributeInterpreter.interpret(validQuery, queryBuilder);
        assertTrue(returnValue);
    }

    @Test
    public void givenAValidStringQueryAndABuilder_whenInterpreting_thenTheEntityShouldBeAddedToTheBuilder() throws Exception {
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