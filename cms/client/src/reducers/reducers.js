import {combineReducers} from "redux";
import tables from "./tablesReducer";
import junctions from "./junctionsReducer";
import operators from "./operatorsReducer";

export default combineReducers({
    tables,
    junctions,
    operators
});
