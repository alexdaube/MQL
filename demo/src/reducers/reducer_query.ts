import { FETCH_QUERY } from '../actions/queryActions';

export default function(state = null, action) {
    switch (action.type) {
        case FETCH_QUERY :
            return action.payload;
    }
    return state;
}