import {FETCH_QUERY_SUCCESS, FETCH_QUERY_ERROR, FETCH_QUERY_REQUEST} from "../../src/actions/types";
import queryReducer from "../../src/reducers/queryReducer";

describe('queryReducer', () => {

    it('handles actions with unknown type', () => {
        expect(queryReducer(undefined, {})).to.eql([]);
    });

    it('handles fetch query error', () => {
        const action = {type: FETCH_QUERY_ERROR};
        expect(queryReducer(['previous state'], action)).to.eql([]);

    });

    it('updates state with response on fetch query success', () => {
        const query = ['new query'];
        const action = {type: FETCH_QUERY_SUCCESS, payload: query};
        expect(queryReducer([], action)).to.eql(query);
    });

    it('handles FETCH_QUERY_REQUEST while waiting for fetch to end', () => {
        const someCurrentState = ['some piece of state'];
        const action = {type: FETCH_QUERY_REQUEST};
        expect(queryReducer(someCurrentState, action)).to.eql(someCurrentState);

    });
});