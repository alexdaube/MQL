import {combineReducers} from "redux";
import queryReducer from "./reducer_query";

const rootReducer = combineReducers({
    query: queryReducer
});

export default rootReducer;