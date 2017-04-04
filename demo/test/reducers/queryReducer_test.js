import {FETCH_QUERY_ERROR, FETCH_QUERY_REQUEST, FETCH_QUERY_SUCCESS} from "../../src/actions/types";
import queryReducer from "../../src/reducers/queryReducer";

describe('queryReducer', () => {
    const errorMessage = 'error Message';
    const queryData = ['new query'];
    let query;
    beforeEach(() => {
        query = {data: [], error: ''};
    });

    it('handles actions with unknown type', () => {
        expect(queryReducer(undefined, {})).to.eql(query);
    });

    it('handles fetch query error', () => {
        const action = {type: FETCH_QUERY_ERROR, error: errorMessage};
        expect(queryReducer(query, action)).to.eql({...query, error: errorMessage});

    });

    it('updates state with response on fetch query success', () => {

        const action = {type: FETCH_QUERY_SUCCESS, payload: queryData};
        expect(queryReducer(query, action)).to.eql({...query, data: queryData});
    });

    it('handles FETCH_QUERY_REQUEST while waiting for fetch to end', () => {
        const someCurrentState = ['some piece of state'];
        const action = {type: FETCH_QUERY_REQUEST};
        expect(queryReducer(someCurrentState, action)).to.eql(someCurrentState);

    });
});