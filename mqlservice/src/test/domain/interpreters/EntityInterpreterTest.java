package domain.interpreters;

import domain.query.Query;
import domain.keywords.Keywords;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EntityInterpreterTest {
    private static final String ENTITY_KEYWORD = "Tablename";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query entityQuery;
    @Mock
    private Query invalidQuery;
    private EntityInterpreter entityInterpreter;
    private Matcher entityMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        entityInterpreter = new EntityInterpreter(keywords);
        entityMatcher = EntityInterpreter.ENTITY_PATTERN.matcher(ENTITY_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(ENTITY_KEYWORD);
        willReturn(entityMatcher).given(entityQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnEntityQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(entityInterpreter.interpret(entityQuery, queryBuilder));
    }

    @Test
    public void givenAnEntityQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        entityInterpreter.interpret(entityQuery, queryBuilder);
        verify(queryBuilder).withEntity(anyString());
    }

    @Test
    public void givenAnEntityQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        entityInterpreter.interpret(entityQuery, queryBuilder);
        verify(entityQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(entityInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        entityInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        entityInterpreter.interpret(invalidQuery, queryBuilder);
        verify(entityQuery, never()).removeFirstMatch(any());
    }
}