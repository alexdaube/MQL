import * as types from "../actions/types";


const initialState = {
    suggestions: [],
    error: '',
    isLoading: false
};

export default function (state = initialState, action) {
    switch (action.type) {
        case types.FETCH_SUGGESTIONS_SUCCESS :
            return {suggestions: action.payload, error: '', isLoading: false};
        case types.FETCH_SUGGESTIONS_ERROR :
            return {suggestions: [], error: action.error, isLoading: false};
        case types.FETCH_SUGGESTIONS_REQUEST:
            return {...state, isLoading: true};
    }
    return state;
}

