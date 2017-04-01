import InMemoryKeywordsClient from "./InMemoryTablesClient";

//const tablesClient = new KeywordsClient("http://rest.learncode.academy/api/test123/tweets");
const tablesClient = new InMemoryKeywordsClient();

module.exports = {
    tablesClient,
};
