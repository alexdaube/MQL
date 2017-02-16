package domain.translators;

import builders.KeywordsBuilder;
import domain.InvalidQueryException;
import domain.StringQuery;
import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.keywords.Keywords;
import domain.keywords.KeywordsResolver;
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
public class AttributeTranslatorStateTest {
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private KeywordsResolver keywordsResolver;
    private Query operatorQuery;
    private Query attributeQuery;
    private AttributeTranslatorState attributeTranslatorState;
    private Keywords operators;
    private Keywords attributes;

    @Before
    public void setUp() throws Exception {
        operatorQuery = new StringQuery("is 9.99");
        attributeQuery = new StringQuery("name is 9.99");
        operators = KeywordsBuilder.create().with("is").build();
        attributes = KeywordsBuilder.create().with("name").build();
        willReturn(operators).given(keywordsResolver).resolveEqualOperators();
        willReturn(attributes).given(keywordsResolver).resolveAttributes();
        attributeTranslatorState = new AttributeTranslatorState(queryBuilder, keywordsResolver);
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
}