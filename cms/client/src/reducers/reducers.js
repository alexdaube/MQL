import {combineReducers} from "redux";
import tables from "./TablesReducer";
import junctions from "./JunctionsReducer";
import operators from "./OperatorsReducer";

export default combineReducers({
    tables,
    junctions,
    operators
});
