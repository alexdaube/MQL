import {combineReducers} from "redux";
import queryReducer from "./queryReducer";

const rootReducer = combineReducers({
    query: queryReducer
});

export default rootReducer;