package domain.interpreters;

import domain.keywords.Keywords;
import domain.query.builder.SuggestionBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BaseInterpreterTest {
    @Mock
    private Keywords keywords;
    @Mock
    SuggestionBuilder suggestionBuilder;
    private BaseInterpreter interpreter;

    @Before
    public void setup() throws Exception {
        interpreter = createBaseInterpreter();
        willReturn(suggestionBuilder).given(suggestionBuilder).withQueryMatching(keywords);
        willReturn(suggestionBuilder).given(suggestionBuilder).withAllowed(keywords);
    }

    @Test
    public void givenASuggestionBuilder_whenSuggest_thenAddSuggestionsMatchingTheQuery() {
        interpreter.suggest(suggestionBuilder);
        verify(suggestionBuilder).withQueryMatching(keywords);
    }

    @Test
    public void givenASuggestionBuilder_whenSuggest_thenAddAllPossibleSuggestions() {
        interpreter.suggest(suggestionBuilder);
        verify(suggestionBuilder).withAllowed(keywords);
    }

    private BaseInterpreter createBaseInterpreter() {
        return new BaseInterpreter(keywords) {
        };
    }
}


