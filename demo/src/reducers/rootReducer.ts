import { combineReducers } from 'redux';
import QueryReducer from './reducer_query';

const rootReducer = combineReducers({
    query: QueryReducer
});

export default rootReducer;