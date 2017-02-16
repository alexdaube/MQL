package domain.translators;

import domain.InvalidQueryException;
import domain.Query;
import domain.StringQuery;
import domain.interpreters.Interpreter;
import domain.keywords.KeywordsResolver;
import domain.querybuilder.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.willReturn;

@RunWith(MockitoJUnitRunner.class)
public class ValueTranslatorStateTest {
    private static final Query EMPTY_QUERY = new StringQuery("   ");
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private KeywordsResolver keywordsResolver;
    @Mock
    private Query junctionQuery;
    @Mock
    private Query attributeQuery;
    @Mock
    private Query valueQuery;
    @Mock
    private Interpreter valueInterpreter;
    @Mock
    private Interpreter junctionInterpreter;
    private ValueTranslatorState valueTranslatorState;

    @Before
    public void setUp() throws Exception {
        valueTranslatorState = new ValueTranslatorState(valueInterpreter, junctionInterpreter, keywordsResolver, queryBuilder);
        willReturn(true).given(valueInterpreter).interpret(valueQuery, queryBuilder);
        willReturn(true).given(junctionInterpreter).interpret(junctionQuery, queryBuilder);
    }

    @Test
    public void givenAValueQuery_whenTranslating_thenTheNextStateRemainUnchanged() throws Exception {
        StateStatus stateStatus = valueTranslatorState.translate(valueQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(ValueTranslatorState.class)));
    }

    @Test
    public void givenAJunctionQuery_whenTranslating_thenTheNextStateIsJunction() throws Exception {
        StateStatus stateStatus = valueTranslatorState.translate(junctionQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(JunctionTranslatorState.class)));
    }

    @Test
    public void givenAnValueQuery_whenTranslating_thenTheTranslationIsUndone() throws Exception {
        StateStatus stateStatus = valueTranslatorState.translate(valueQuery);
        assertFalse(stateStatus.isDone());
    }

    @Test
    public void givenAJunctionQuery_whenTranslating_thenTheTranslationIsUndone() throws Exception {
        StateStatus stateStatus = valueTranslatorState.translate(junctionQuery);
        assertFalse(stateStatus.isDone());
    }

    @Test
    public void givenAnEmptyQuery_whenTranslating_thenTheTranslationIsDone() throws Exception {
        StateStatus stateStatus = valueTranslatorState.translate(EMPTY_QUERY);
        assertTrue(stateStatus.isDone());
    }

    @Test(expected = InvalidQueryException.class)
    public void givenAnUnsupportedQuery_whenTranslating_thenThrowAnInvalidQueryException() throws Exception {
        valueTranslatorState.translate(attributeQuery);
    }
}