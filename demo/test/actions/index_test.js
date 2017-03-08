import configureMockStore from 'redux-mock-store'
import thunk from 'redux-thunk'
import * as actions from '../../src/actions/index'
import * as types from "../../src/actions/types";

import moxios from 'moxios'

const middlewares = [ thunk ];
const mockStore = configureMockStore(middlewares);

describe('actions', () => {

    describe('that are async actions', () => {
        let store, expectedActions;
        const someQueryResponse = ['some query response'];
        const query = 'some query';

        const storeActionsExpectation = () => {
            return store.dispatch(actions.fetchQuery(query))
                .then(() => {
                    expect(store.getActions()).to.eql(expectedActions)
                });
        };

        beforeEach(() => {
            expectedActions = [{ type: types.FETCH_QUERY_REQUEST }];
            store = mockStore({ query: [] });
            moxios.install();
        });

        afterEach(() => {
            moxios.uninstall();
        });

        it('creates FETCH_QUERY_SUCCESS when request is successful', () => {
            moxios.wait(() => {
                const request = moxios.requests.mostRecent();
                request.respondWith({
                    status: 200,
                    response: someQueryResponse
                });
            });

            expectedActions.push({ type: types.FETCH_QUERY_SUCCESS, payload: someQueryResponse });

            return storeActionsExpectation();
        });

        it('creates FETCH_QUERY_ERROR when request is not successful', () => {
            moxios.wait(() => {
                const request = moxios.requests.mostRecent();
                request.respondWith({
                    status: 404,
                });
            });

            expectedActions.push({ type: types.FETCH_QUERY_ERROR });

            return storeActionsExpectation();
        });
    });
});
