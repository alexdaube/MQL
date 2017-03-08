import {FETCH_QUERY_SUCCESS, FETCH_QUERY_ERROR, FETCH_QUERY_REQUEST} from "../actions/types";

export default function (state = [], action) {
    switch (action.type) {
        case FETCH_QUERY_SUCCESS :
            return action.payload;
        case FETCH_QUERY_ERROR :
            return [];
        case FETCH_QUERY_REQUEST:
            return state;
    }
    return state;
}
