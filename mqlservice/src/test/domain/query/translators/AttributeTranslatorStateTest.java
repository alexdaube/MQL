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
public class AttributeTranslatorStateTest {
    private static final String OPERATOR = "Operator";

    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private KeywordsResolver keywordsResolver;
    @Mock
    private Query operatorQuery;
    @Mock
    private Query attributeQuery;
    @Mock
    private Interpreter interpreter;
    @Mock
    private SuggestionBuilder suggestionBuilder;
    private AttributeTranslatorState attributeTranslatorState;

    @Before
    public void setUp() throws Exception {
        attributeTranslatorState = new AttributeTranslatorState(queryBuilder, interpreter, keywordsResolver);
        willReturn(true).given(interpreter).interpret(operatorQuery, queryBuilder);
    }

    @Test
    public void givenAnOperatorQuery_whenTranslating_thenTheNextStateIsOperator() throws Exception {
        StateStatus stateStatus = attributeTranslatorState.translate(operatorQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(OperatorTranslatorState.class)));
    }

    @Test
    public void givenAnOperatorQuery_whenTranslating_thenTheTranslationIsUndone() throws Exception {
        StateStatus stateStatus = attributeTranslatorState.translate(operatorQuery);
        assertFalse(stateStatus.isDone());
    }

    @Test(expected = InvalidQueryException.class)
    public void givenANonOperatorQuery_whenTranslating_thenThrowAnInvalidQueryException() throws Exception {
        attributeTranslatorState.translate(attributeQuery);
    }

    @Test
    public void givenASuggestionBuilder_whenTranslateNextSuggestion_thenAddOperatorHint() {
        attributeTranslatorState.translateNextSuggestion(suggestionBuilder);
        verify(suggestionBuilder).withHint(OPERATOR);
    }

    @Test
    public void givenASuggestionBuilder_whenTranslateNextSuggestion_thenSuggestBasedOnInterpreter() {
        attributeTranslatorState.translateNextSuggestion(suggestionBuilder);
        verify(interpreter).suggest(suggestionBuilder);
    }
}