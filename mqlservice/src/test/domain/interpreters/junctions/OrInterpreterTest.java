package domain.interpreters.junctions;

import domain.Query;
import domain.keywords.Keywords;
import domain.querybuilder.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrInterpreterTest {
    private static final String OR_KEYWORD = "or";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query orQuery;
    @Mock
    private Query invalidQuery;
    private OrInterpreter orInterpreter;
    private Matcher orMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        orInterpreter = new OrInterpreter(keywords);
        orMatcher = OrInterpreter.OR_PATTERN.matcher(OR_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(OR_KEYWORD);
        willReturn(orMatcher).given(orQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnOrQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(orInterpreter.interpret(orQuery, queryBuilder));
    }

    @Test
    public void givenAnOrQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        orInterpreter.interpret(orQuery, queryBuilder);
        verify(queryBuilder).or();
    }

    @Test
    public void givenAnOrQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        orInterpreter.interpret(orQuery, queryBuilder);
        verify(orQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(orInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        orInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        orInterpreter.interpret(invalidQuery, queryBuilder);
        verify(orQuery, never()).removeFirstMatch(any());
    }
}