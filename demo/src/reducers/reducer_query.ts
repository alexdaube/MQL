import {FETCH_QUERY} from "../actions/types";

export default function (state = [], action) {
    switch (action.type) {
        case FETCH_QUERY :
            return action.payload.data;
    }
    return state;
}