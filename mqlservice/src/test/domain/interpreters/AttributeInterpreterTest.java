package domain.interpreters;

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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AttributeInterpreterTest {
    private static final String ATTRIBUTE_KEYWORD = "name";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query attributeQuery;
    @Mock
    private Query invalidQuery;
    private AttributeInterpreter attributeInterpreter;
    private Matcher attributeMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        attributeInterpreter = new AttributeInterpreter(keywords);
        attributeMatcher = AttributeInterpreter.ATTRIBUTE_PATTERN.matcher(ATTRIBUTE_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(ATTRIBUTE_KEYWORD);
        willReturn(attributeMatcher).given(attributeQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnAttributeQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(attributeInterpreter.interpret(attributeQuery, queryBuilder));
    }

    @Test
    public void givenAnAttributeQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        attributeInterpreter.interpret(attributeQuery, queryBuilder);
        verify(queryBuilder).withAttribute(anyString());
    }

    @Test
    public void givenAnAttributeQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        attributeInterpreter.interpret(attributeQuery, queryBuilder);
        verify(attributeQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(attributeInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        attributeInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        attributeInterpreter.interpret(invalidQuery, queryBuilder);
        verify(attributeQuery, never()).removeFirstMatch(any());
    }
}
