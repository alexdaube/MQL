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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EntityTranslatorStateTest {
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private KeywordsResolver keywordsResolver;
    private EntityTranslatorState entityTranslatorState;
    private Keywords operators;
    private Keywords attributes;
    private StringQuery operatorQuery;
    private StringQuery attributeQuery;

    @Before
    public void setUp() throws Exception {
        operatorQuery = new StringQuery("is 9.99");
        attributeQuery = new StringQuery("name is 9.99");
        operators = KeywordsBuilder.create().with("is").build();
        attributes = KeywordsBuilder.create().with("name").build();
        willReturn(operators).given(keywordsResolver).resolveOperators();
        willReturn(attributes).given(keywordsResolver).resolveAttributes();
        entityTranslatorState = new EntityTranslatorState(queryBuilder, keywordsResolver);
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