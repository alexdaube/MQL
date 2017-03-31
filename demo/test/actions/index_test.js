import configureMockStore from "redux-mock-store";
import thunk from "redux-thunk";
import * as actions from "../../src/actions/index";
import * as types from "../../src/actions/types";
import moxios from "moxios";

const middlewares = [thunk];
const mockStore = configureMockStore(middlewares);


describe('actions', () => {
    describe('that are async actions', () => {
        let store, expectedActions;
        const someQueryResponse = ['some query response'];
        const someQueryErrorMessage = 'Wrong Query';
        const query = 'some query';

        const storeActionsExpectation = (actionInvocation) => {
            return store.dispatch(actionInvocation)
                .then(() => {
                    expect(store.getActions()).to.eql(expectedActions)
                });
        };

        const successCallbackTest = (type, actionInvocation) => {
            it(`creates ${type} when request is successful`, () => {
                moxios.wait(() => {
                    const request = moxios.requests.mostRecent();
                    request.respondWith({
                        status: 200,
                        response: someQueryResponse
                    });
                });
                expectedActions.push({type: type, payload: someQueryResponse});
                return storeActionsExpectation(actionInvocation);
            });
        };

        const errorCallBackTest = (type, actionInvocation) => {
            it(`creates ${type} when request is not successful`, () => {
                moxios.wait(() => {
                    const request = moxios.requests.mostRecent();
                    request.respondWith({
                        status: 404,
                        response: {errorMessage: someQueryErrorMessage}
                    });
                });
                expectedActions.push({type: type, error: someQueryErrorMessage});
                return storeActionsExpectation(actionInvocation);
            });
        };

        beforeEach(() => {
            expectedActions = [{type: types.FETCH_QUERY_REQUEST}];
            store = mockStore({query: {}});
            moxios.install();
        });

        afterEach(() => {
            moxios.uninstall();
        });

        describe('fetch query', () => {
            beforeEach(() => {
                expectedActions = [{type: types.FETCH_QUERY_REQUEST}];
                store = mockStore({query: {}});
            });
            successCallbackTest(types.FETCH_QUERY_SUCCESS, actions.fetchQuery(query));
            errorCallBackTest(types.FETCH_QUERY_ERROR, actions.fetchQuery(query));
        });

        describe('fetch suggestions', () => {
            beforeEach(() => {
                expectedActions = [{type: types.FETCH_SUGGESTIONS_REQUEST}];
                store = mockStore({suggestions: {}});
            });
            successCallbackTest(types.FETCH_SUGGESTIONS_SUCCESS, actions.fetchSuggestions(query));
            errorCallBackTest(types.FETCH_SUGGESTIONS_ERROR, actions.fetchSuggestions(query));
        });
    });
});
