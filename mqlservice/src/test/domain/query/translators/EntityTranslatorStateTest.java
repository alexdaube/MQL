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
public class EntityTranslatorStateTest {
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
}