package domain.translators;

import builders.KeywordsBuilder;
import domain.QueryBuilder;
import domain.StringQuery;
import domain.InvalidQueryException;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ValueTranslatorStateTest {
    private static final StringQuery EMPTY_QUERY = new StringQuery("   ");
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private KeywordsResolver keywordsResolver;
    private ValueTranslatorState valueTranslatorState;
    private Keywords junctions;
    private Keywords attributes;
    private StringQuery junctionQuery;
    private StringQuery attributeQuery;
    private StringQuery valueQuery;

    @Before
    public void setUp() throws Exception {
        junctionQuery = new StringQuery("and Employee name is 9.99");
        attributeQuery = new StringQuery("name is 9.99");
        valueQuery = new StringQuery("9.99");
        junctions = KeywordsBuilder.create().with("and").build();
        attributes = KeywordsBuilder.create().with("name").build();
        willReturn(junctions).given(keywordsResolver).resolveJunctions();
        willReturn(attributes).given(keywordsResolver).resolveAttributes();
        valueTranslatorState = new ValueTranslatorState(queryBuilder, keywordsResolver);
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