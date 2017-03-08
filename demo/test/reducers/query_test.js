import {FETCH_QUERY} from "../../src/actions/types";
import queryReducer from "../../src/reducers/queryReducer";

describe('Query Reducer', () => {

    it('handles actions with unknown type', () => {
        expect(queryReducer(undefined, {})).to.eql([]);
    });

    it('handles action of type FETCH_QUERY', () => {
        const query = ['new query'];
        const data = {data: query};
        const action = {type: FETCH_QUERY, payload: data};
        expect(queryReducer([], action)).to.eql(query);
    });
});