const td = require('testdouble');
const expect = require("chai").expect;
const mockMongoDb = require("../../helpers/mockMongoDb");
const Connection = require("../../../src/persistence/connection");
const MongoRepository = require("../../../src/persistence/repositories/MongoRepository");


describe('Connection', () => {
    const collectionName = "collection";
    let repository, connection, db, err, cursor;


    beforeEach(() => {
        cursor = mockMongoDb.cursor;
        connection = mockMongoDb.db;
        err = new Error;
        repository = new MongoRepository(connection, collectionName);
    });

    afterEach(() => {
        td.reset();
    });

    describe("getAll", () => {
        it("should return an error if when looping on cursor fails", () => {
            const errorObj = {hasError: true};
            td.when(connection.collection(collectionName)).thenReturn(connection);
            td.when(repository.connection.find()).thenReturn(cursor);
            td.when(cursor.each(td.callback(err, ""))).thenReturn(errorObj);

            const collection = repository.getAll();

            //expect(collection.hasError).to.be.true;
        });
    });
});

