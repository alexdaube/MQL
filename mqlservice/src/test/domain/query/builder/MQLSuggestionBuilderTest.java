package domain.query.builder;

import domain.keywords.Keyword;
import domain.keywords.Keywords;
import domain.keywords.KeywordsSet;
import domain.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

@RunWith(MockitoJUnitRunner.class)
public class MQLSuggestionBuilderTest {
    private static final String KEYWORD_NAME = "name";
    private static final String KEYWORD_TYPE = "ATTRIBUTE";
    private static final String MATCHING_QUERY = "na";
    private static final String NON_MATCHING_QUERY = "tam";
    private static final Keyword.Type TYPE = Keyword.Type.ATTRIBUTE;
    @Mock
    private Keyword keyword;
    @Mock
    private Query query;
    private Keywords keywords;
    private SuggestionBuilder suggestionBuilder;

    @Before
    public void setUp() throws Exception {
        keywords = new KeywordsSet();
        keywords.add(keyword);
        suggestionBuilder = new MQLSuggestionBuilder(query);
        willReturn(KEYWORD_NAME).given(keyword).name();
        willReturn(MATCHING_QUERY).given(query).getQuery();
        willReturn(TYPE).given(keyword).getType();
    }

    @Test
    public void givenANewBuilder_whenBuildSuggestion_thenReturnsAnEmptySuggestionList() {
        assertEquals(0, suggestionBuilder.buildSuggestion().size());
    }

    @Test
    public void givenKeywordsToAdd_whenWithAllowed_thenReturnsAnAllowedSuggestionSection() {
        suggestionBuilder.withAllowed(keywords);
        assertEquals(1, suggestionBuilder.buildSuggestion().size());
    }

    @Test
    public void givenATypeToAdd_whenWithValue_thenReturnsValueSuggestionSection() {
        suggestionBuilder.withValue(KEYWORD_TYPE);
        assertEquals(1, suggestionBuilder.buildSuggestion().size());
    }

    @Test
    public void givenKeywordsToAddAndMatchingQuery_whenWithQueryMatching_thenReturnsMatchedQuerySuggestionSection() {
        suggestionBuilder.withQueryMatching(keywords);
        assertEquals(1, suggestionBuilder.buildSuggestion().size());
    }

    @Test
    public void givenKeywordsToAddAndNotMatchingQuery_whenWithQueryMatching_thenReturnsNoMatchedQuerySuggestionSection() {
        willReturn(NON_MATCHING_QUERY).given(query).getQuery();
        suggestionBuilder.withQueryMatching(keywords);
        assertEquals(0, suggestionBuilder.buildSuggestion().size());
    }

    @Test
    public void givenChainedBuilderMethodCalls_whenBuildSuggestion_thenReturnsManySuggestionSections() {
        suggestionBuilder.withValue(KEYWORD_TYPE)
                .withAllowed(keywords).withQueryMatching(keywords);
        assertEquals(3, suggestionBuilder.buildSuggestion().size());
    }
}
