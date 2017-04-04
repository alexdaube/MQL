package domain.interpreters.operators;

import domain.keywords.Keywords;
import domain.query.Query;
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
public class OtherInterpreterTest {
    private static final String OTHER_KEYWORD = "than";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query otherQuery;
    @Mock
    private Query invalidQuery;
    private OtherInterpreter otherInterpreter;
    private Matcher otherMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        otherInterpreter = new OtherInterpreter(keywords);
        otherMatcher = OtherInterpreter.OTHER_PATTERN.matcher(OTHER_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(OTHER_KEYWORD);
        willReturn(otherMatcher).given(otherQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnOtherQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(otherInterpreter.interpret(otherQuery, queryBuilder));
    }

    @Test
    public void givenAnOtherQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        otherInterpreter.interpret(otherQuery, queryBuilder);
        verify(otherQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(otherInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        otherInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        otherInterpreter.interpret(invalidQuery, queryBuilder);
        verify(otherQuery, never()).removeFirstMatch(any());
    }
}