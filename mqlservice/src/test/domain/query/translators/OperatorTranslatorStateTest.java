package domain.query.translators;

import domain.InvalidQueryException;
import domain.interpreters.Interpreter;
import domain.keywords.KeywordsResolver;
import domain.query.Query;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SuggestionBuilder;
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
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OperatorTranslatorStateTest {
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private KeywordsResolver keywordsResolver;
    @Mock
    private Query operatorQuery;
    @Mock
    private Query attributeQuery;
    @Mock
    private Query valueQuery;
    @Mock
    private Interpreter valueInterpreter;
    @Mock
    private Interpreter operatorInterpreter;
    @Mock
    private SuggestionBuilder suggestionBuilder;
    private OperatorTranslatorState operatorTranslatorState;

    @Before
    public void setUp() throws Exception {
        operatorTranslatorState = new OperatorTranslatorState(valueInterpreter, operatorInterpreter, keywordsResolver, queryBuilder);
        willReturn(true).given(valueInterpreter).interpret(valueQuery, queryBuilder);
        willReturn(true).given(operatorInterpreter).interpret(operatorQuery, queryBuilder);
    }

    @Test
    public void givenAValueQuery_whenTranslating_thenTheNextStateIsValue() throws Exception {
        StateStatus stateStatus = operatorTranslatorState.translate(valueQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(ValueTranslatorState.class)));
    }

    @Test
    public void givenAnOperatorQuery_whenTranslating_thenTheStateRemainsUnchanged() throws Exception {
        StateStatus stateStatus = operatorTranslatorState.translate(operatorQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(OperatorTranslatorState.class)));
    }

    @Test
    public void givenAnOperatorQuery_whenTranslating_thenTheTranslationIsUndone() throws Exception {
        StateStatus stateStatus = operatorTranslatorState.translate(operatorQuery);
        assertFalse(stateStatus.isDone());
    }

    @Test(expected = InvalidQueryException.class)
    public void givenANonOperatorQuery_whenTranslating_thenThrowAnInvalidQueryException() throws Exception {
        operatorTranslatorState.translate(attributeQuery);
    }

    @Test
    public void givenASuggestionBuilder_whenTranslateNextSuggestion_thenSuggestBasedOnValueInterpreter() {
        operatorTranslatorState.translateNextSuggestion(suggestionBuilder);
        verify(valueInterpreter).suggest(suggestionBuilder);
    }

    @Test
    public void givenASuggestionBuilder_whenTranslateNextSuggestion_thenSuggestBasedOnOperatorInterpreter() {
        operatorTranslatorState.translateNextSuggestion(suggestionBuilder);
        verify(operatorInterpreter).suggest(suggestionBuilder);
    }

}