import * as types from "../../src/actions/types";
import suggestionsReducer from "../../src/reducers/suggestionsReducer";

describe('suggestionsReducer', () => {
    const initialState = {suggestions: [], error: '', isLoading: false};
    const errorMessage = 'error Message';
    const retrievedSuggestions = ['new query'];
    let suggestions;
    beforeEach(() => {
        suggestions = initialState;
    });

    it('handles actions with unknown type', () => {
        expect(suggestionsReducer(undefined, {})).to.eql(suggestions);
    });

    it('handles fetch suggestions error', () => {
        const action = {type: types.FETCH_SUGGESTIONS_ERROR, error: errorMessage};
        expect(suggestionsReducer(suggestions, action)).to.eql({...suggestions, error: errorMessage});
    });

    it('updates state with response on fetch suggestions success', () => {
        const action = {type: types.FETCH_SUGGESTIONS_SUCCESS, payload: retrievedSuggestions};
        expect(suggestionsReducer(suggestions, action)).to.eql({...suggestions, suggestions: retrievedSuggestions});
    });

    it('handles FETCH_SUGGESTIONS_REQUEST while waiting for fetch to end', () => {
        const action = {type: types.FETCH_SUGGESTIONS_REQUEST};
        expect(suggestionsReducer(suggestions, action)).to.eql({...suggestions, isLoading: true});
    });

    it('reverts to initial state when clearing suggestions is called', () => {
        const action = {type: types.CLEAR_SUGGESTIONS};
        suggestions = {...suggestions, isLoading: true};
        expect(suggestionsReducer(suggestions, action)).to.eql(initialState);
    });
});