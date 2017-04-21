import {combineReducers} from "redux";
import queryReducer from "./queryReducer";
import suggestionsReducer from "./suggestionsReducer";

const rootReducer = combineReducers({
    query: queryReducer,
    suggestions: suggestionsReducer
});

export default rootReducer;