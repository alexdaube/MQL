package domain.interpreters;


import domain.keywords.Keywords;
import domain.query.builder.SuggestionBuilder;

public abstract class BaseInterpreter implements Suggester {
    protected final Keywords keywords;

    public BaseInterpreter(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public void suggest(SuggestionBuilder suggestionBuilder) {
        suggestionBuilder.withQueryMatching(keywords).withAllowed(keywords);
    }
}
