const td = require('testdouble');
const Connection = require("../../src/persistence/connection");
const mockMongoDb = require("../helpers/mockMongoDb");


describe('Connection', () => {
    let connection, db;

    beforeEach(() => {
        db = mockMongoDb.db;
        connection = new Connection(db);
    });

    afterEach(() => {
        td.reset();
    });

    it("should open a connection", () => {
        connection.open();

        td.verify(db.open(td.matchers.anything()));
    });
});
