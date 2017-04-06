import * as types from "../actions/types";


const initialState = {
    data: [],
    error: ''
};

export default function (state = initialState, action) {
    switch (action.type) {
        case types.FETCH_QUERY_SUCCESS :
            return {data: action.payload, error: ''};
        case types.FETCH_QUERY_ERROR :
            return {data: [], error: action.error};
        case types.FETCH_QUERY_REQUEST:
            return state;
    }
    return state;
}
