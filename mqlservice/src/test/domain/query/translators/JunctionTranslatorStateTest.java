package domain.query.translators;

import domain.InvalidQueryException;
import domain.interpreters.Interpreter;
import domain.keywords.KeywordsResolver;
import domain.query.Query;
import domain.query.builder.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.willReturn;

@RunWith(MockitoJUnitRunner.class)
public class JunctionTranslatorStateTest {
    @Mock
    private Interpreter entityInterpreter;
    @Mock
    private Interpreter attributeInterpreter;
    @Mock
    private Interpreter operatorInterpreter;
    @Mock
    private Interpreter valueInterpreter;
    @Mock
    private Query entityQuery;
    @Mock
    private Query attributeQuery;
    @Mock
    private Query operatorQuery;
    @Mock
    private Query valueQuery;
    @Mock
    private Query invalidQuery;
    @Mock
    private KeywordsResolver keywordsResolver;
    @Mock
    private QueryBuilder queryBuilder;
    private JunctionTranslatorState junctionTranslatorState;

    @Before
    public void setUp() {
        junctionTranslatorState = new JunctionTranslatorState(entityInterpreter, attributeInterpreter,
                operatorInterpreter, valueInterpreter, keywordsResolver, queryBuilder);
        willReturn(true).given(entityInterpreter).interpret(entityQuery, queryBuilder);
        willReturn(true).given(attributeInterpreter).interpret(attributeQuery, queryBuilder);
        willReturn(true).given(operatorInterpreter).interpret(operatorQuery, queryBuilder);
        willReturn(true).given(valueInterpreter).interpret(valueQuery, queryBuilder);
    }

    @Test
    public void givenAnEntityQuery_whenTranslating_thenTheNextStateIsEntity() {
        StateStatus stateStatus = junctionTranslatorState.translate(entityQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(EntityTranslatorState.class)));
    }

    @Test
    public void givenAnAttributeQuery_whenTranslating_thenTheNextStateIsAttribute() {
        StateStatus stateStatus = junctionTranslatorState.translate(attributeQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(AttributeTranslatorState.class)));
    }

    @Test
    public void givenAnOperatorQuery_whenTranslating_thenTheNextStateIsOperator() {
        StateStatus stateStatus = junctionTranslatorState.translate(operatorQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(OperatorTranslatorState.class)));
    }

    @Test
    public void givenAnValueQuery_whenTranslating_thenTheNextStateIsValue() {
        StateStatus stateStatus = junctionTranslatorState.translate(valueQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(ValueTranslatorState.class)));
    }

    @Test
    public void givenAnEntityQuery_whenTranslating_thenTheTranslationIsUndone() {
        StateStatus stateStatus = junctionTranslatorState.translate(entityQuery);
        assertFalse(stateStatus.isDone());
    }

    @Test
    public void givenAnAttributeQuery_whenTranslating_thenTheTranslationIsUndone() {
        StateStatus stateStatus = junctionTranslatorState.translate(attributeQuery);
        assertFalse(stateStatus.isDone());
    }

    @Test
    public void givenAnOperatorQuery_whenTranslating_thenTheTranslationIsUndone() {
        StateStatus stateStatus = junctionTranslatorState.translate(operatorQuery);
        assertFalse(stateStatus.isDone());
    }

    @Test
    public void givenAnValueQuery_whenTranslating_thenTheTranslationIsUndone() {
        StateStatus stateStatus = junctionTranslatorState.translate(valueQuery);
        assertFalse(stateStatus.isDone());
    }

    @Test(expected = InvalidQueryException.class)
    public void givenAnInvalidQuery_whenTranslating_thenThrowAnException() {
        junctionTranslatorState.translate(invalidQuery);
    }
}