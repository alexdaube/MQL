import InMemoryKeywordsClient from "./InMemoryTablesClient";
import InMemoryJunctionsClient from "./InMemoryJunctionsClient";
import InMemoryOperatorsClient from "./InMemoryOperatorsClient";

//const tablesClient = new KeywordsClient("http://rest.learncode.academy/api/test123/tweets");
const tablesClient = new InMemoryKeywordsClient();

const junctionsClient = new InMemoryJunctionsClient();

const operatorsClient = new InMemoryOperatorsClient();

module.exports = {
    tablesClient,
    junctionsClient,
    operatorsClient
};
