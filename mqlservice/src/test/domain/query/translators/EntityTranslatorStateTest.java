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
public class EntityTranslatorStateTest {
    private static final String ATTRIBUTE = "Attribute";

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
    private EntityTranslatorState entityTranslatorState;

    @Before
    public void setUp() throws Exception {
        entityTranslatorState = new EntityTranslatorState(interpreter, queryBuilder, keywordsResolver);
        willReturn(true).given(interpreter).interpret(attributeQuery, queryBuilder);
    }

    @Test
    public void givenAnAttributeQuery_whenTranslating_thenTheNextStateIsAttribute() throws Exception {
        StateStatus stateStatus = entityTranslatorState.translate(attributeQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(AttributeTranslatorState.class)));
    }

    @Test
    public void givenAnAttributeQuery_whenTranslating_thenTheTranslationIsUndone() throws Exception {
        StateStatus stateStatus = entityTranslatorState.translate(attributeQuery);
        assertFalse(stateStatus.isDone());
    }

    @Test(expected = InvalidQueryException.class)
    public void givenANonAttributeQuery_whenTranslating_thenThrowAnInvalidQueryException() throws Exception {
        entityTranslatorState.translate(operatorQuery);
    }

    @Test
    public void givenASuggestionBuilder_whenTranslateNextSuggestion_thenAddAttributeHint() {
        entityTranslatorState.translateNextSuggestion(suggestionBuilder);
        verify(suggestionBuilder).withHint(ATTRIBUTE);
    }

    @Test
    public void givenASuggestionBuilder_whenTranslateNextSuggestion_thenSuggestBasedOnInterpreter() {
        entityTranslatorState.translateNextSuggestion(suggestionBuilder);
        verify(interpreter).suggest(suggestionBuilder);
    }
}