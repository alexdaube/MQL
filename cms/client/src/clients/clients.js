import InMemoryKeywordsClient from "./InMemoryTablesClient";
import InMemoryJunctionsClient from "./InMemoryJunctionsClient";
import InMemoryOperatorsClient from "./InMemoryOperatorsClient";
import OperatorsClient from "./OperatorsClient";
import JunctionsClient from "./JunctionsClient";
import TablesClient from "./TablesClient";

//const tablesClient = new InMemoryKeywordsClient();
const tablesClient = new TablesClient("http://localhost:3000/tables");

//const junctionsClient = new InMemoryJunctionsClient();
const junctionsClient = new JunctionsClient("http://localhost:3000/junctions");

//const operatorsClient = new InMemoryOperatorsClient();
const operatorsClient = new OperatorsClient("http://localhost:3000/operators");

module.exports = {
    tablesClient,
    junctionsClient,
    operatorsClient
};
