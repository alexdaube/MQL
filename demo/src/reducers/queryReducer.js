import {FETCH_QUERY_SUCCESS, FETCH_QUERY_ERROR, FETCH_QUERY_REQUEST} from "../actions/types";

export default function (state = {data: [], error: ''}, action) {
    switch (action.type) {
        case FETCH_QUERY_SUCCESS :
            return {data: action.payload, error: ''};
        case FETCH_QUERY_ERROR :
            return {data: [], error: action.error};
        case FETCH_QUERY_REQUEST:
            return state;
    }
    return state;
}
