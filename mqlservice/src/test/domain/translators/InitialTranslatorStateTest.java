package domain.translators;

import builders.KeywordsBuilder;
import domain.InvalidQueryException;
import domain.QueryBuilder;
import domain.StringQuery;
import domain.keyword.Keywords;
import domain.keyword.KeywordsResolver;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InitialTranslatorStateTest {
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private KeywordsResolver keywordsResolver;
    private StringQuery operatorQuery;
    private StringQuery entityQuery;
    private InitialTranslatorState initialTranslatorState;
    private Keywords operators;
    private Keywords entities;

    @Before
    public void setUp() throws Exception {
        operatorQuery = new StringQuery("is 9.99");
        entityQuery = new StringQuery("Employee name is 9.99");
        operators = KeywordsBuilder.create().with("is").build();
        entities = KeywordsBuilder.create().with("Employee").build();
        willReturn(operators).given(keywordsResolver).resolveOperators();
        willReturn(entities).given(keywordsResolver).resolveEntities();
        initialTranslatorState = new InitialTranslatorState(queryBuilder, keywordsResolver);
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