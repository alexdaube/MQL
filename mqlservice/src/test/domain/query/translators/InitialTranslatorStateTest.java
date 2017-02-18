package domain.query.translators;

import domain.InvalidQueryException;
import domain.query.Query;
import domain.interpreters.Interpreter;
import domain.keywords.KeywordsResolver;
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
public class InitialTranslatorStateTest {
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private KeywordsResolver keywordsResolver;
    @Mock
    private Query operatorQuery;
    @Mock
    private Query entityQuery;
    @Mock
    private Interpreter interpreter;
    private InitialTranslatorState initialTranslatorState;

    @Before
    public void setUp() throws Exception {
        initialTranslatorState = new InitialTranslatorState(interpreter, keywordsResolver, queryBuilder);
        willReturn(true).given(interpreter).interpret(entityQuery, queryBuilder);
    }

    @Test
    public void givenAnEntityQuery_whenTranslating_thenTheNextStateIsEntity() throws Exception {
        StateStatus stateStatus = initialTranslatorState.translate(entityQuery);
        assertThat(stateStatus.nextState(), is(instanceOf(EntityTranslatorState.class)));
    }

    @Test
    public void givenAnEntityQuery_whenTranslating_thenTheTranslationIsUndone() throws Exception {
        StateStatus stateStatus = initialTranslatorState.translate(entityQuery);
        assertFalse(stateStatus.isDone());
    }

    @Test(expected = InvalidQueryException.class)
    public void givenANonEntityQuery_whenTranslating_thenThrowAnInvalidQueryException() throws Exception {
        initialTranslatorState.translate(operatorQuery);
    }
}