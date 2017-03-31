import {FETCH_SUGGESTIONS_SUCCESS, FETCH_SUGGESTIONS_ERROR, FETCH_SUGGESTIONS_REQUEST} from "../../src/actions/types";
import suggestionsReducer from "../../src/reducers/suggestionsReducer";

describe('suggestionsReducer', () => {
    const errorMessage = 'error Message';
    const retrievedSuggestions = ['new query'];
    let suggestions;
    beforeEach(() => {
        suggestions = {suggestions: [], error: '', isLoading: false};
    });

    it('handles actions with unknown type', () => {
        expect(suggestionsReducer(undefined, {})).to.eql(suggestions);
    });

    it('handles fetch suggestions error', () => {
        const action = {type: FETCH_SUGGESTIONS_ERROR, error: errorMessage};
        expect(suggestionsReducer(suggestions, action)).to.eql({...suggestions, error: errorMessage});
    });

    it('updates state with response on fetch suggestions success', () => {
        const action = {type: FETCH_SUGGESTIONS_SUCCESS, payload: retrievedSuggestions};
        expect(suggestionsReducer(suggestions, action)).to.eql({...suggestions, suggestions: retrievedSuggestions});
    });

    it('handles FETCH_SUGGESTIONS_REQUEST while waiting for fetch to end', () => {
        const action = {type: FETCH_SUGGESTIONS_REQUEST};
        expect(suggestionsReducer(suggestions, action)).to.eql({...suggestions, isLoading: true});
    });
});