package domain.interpreters.operators;

import domain.query.Query;
import domain.keywords.Keywords;
import domain.query.builder.OperatorType;
import domain.query.builder.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BetweenInterpreterTest {
    private static final String BETWEEN_KEYWORD = "between";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query betweenQuery;
    @Mock
    private Query invalidQuery;
    private BetweenInterpreter betweenInterpreter;
    private Matcher betweenMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        betweenInterpreter = new BetweenInterpreter(keywords);
        betweenMatcher = BetweenInterpreter.BETWEEN_PATTERN.matcher(BETWEEN_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(BETWEEN_KEYWORD);
        willReturn(betweenMatcher).given(betweenQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnBetweenQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(betweenInterpreter.interpret(betweenQuery, queryBuilder));
    }

    @Test
    public void givenAnBetweenQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        betweenInterpreter.interpret(betweenQuery, queryBuilder);
        verify(queryBuilder).withOperator(OperatorType.BETWEEN);
    }

    @Test
    public void givenAnBetweenQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        betweenInterpreter.interpret(betweenQuery, queryBuilder);
        verify(betweenQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(betweenInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        betweenInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        betweenInterpreter.interpret(invalidQuery, queryBuilder);
        verify(betweenQuery, never()).removeFirstMatch(any());
    }
}