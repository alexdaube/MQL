package domain.interpreters.operators;

import domain.keywords.Keywords;
import domain.query.Query;
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
public class LikeInterpreterTest {

    private static final String LIKE_KEYWORD = "like";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query likeQuery;
    @Mock
    private Query invalidQuery;
    private LikeInterpreter likeInterpreter;
    private Matcher likeMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        likeInterpreter = new LikeInterpreter(keywords);
        likeMatcher = LikeInterpreter.LIKE_PATTERN.matcher(LIKE_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(LIKE_KEYWORD);
        willReturn(likeMatcher).given(likeQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnLikeQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(likeInterpreter.interpret(likeQuery, queryBuilder));
    }

    @Test
    public void givenAnLikeQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        likeInterpreter.interpret(likeQuery, queryBuilder);
        verify(queryBuilder).withOperator(any(OperatorType.class));
    }

    @Test
    public void givenAnLikeQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        likeInterpreter.interpret(likeQuery, queryBuilder);
        verify(likeQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(likeInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        likeInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        likeInterpreter.interpret(invalidQuery, queryBuilder);
        verify(likeQuery, never()).removeFirstMatch(any());
    }
}