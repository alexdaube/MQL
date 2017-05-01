const td = require('testdouble');
const Tables = require("../../src/tables/tables");
const Table = require("../../src/tables/table");
const Column = require("../../src/tables/column");
const expect = require("chai").expect;

describe('Tables', () => {
    const name = "table";
    const anotherTableName = "anotherTable";
    const columnName = "column";
    const keyword = "keyword";
    const newKeyword = "newkeyword";
    const keywords = [keyword];
    let tables, table, column;

    beforeEach(() => {
        column = new Column(columnName, [keyword]);
        table = new Table(name, keywords, [column]);
        tables = new Tables([table]);
    });

    afterEach(() => {
        td.reset();
    });

    describe("addTable", () => {
        it('should not add an existing table', () => {
            tables.addTable(name);
            expect(tables.getTables()).to.have.lengthOf(1);
        });

        it('should add a new table', () => {
            tables.addTable(anotherTableName);
            expect(tables.getTables()).to.have.lengthOf(2);
        });
    });

    describe("removeTable", () => {
        it('should not remove a non existing table', () => {
            tables.removeTable(anotherTableName);
            expect(tables.getTables()).to.have.lengthOf(1);
        });

        it('should remove an existing table', () => {
            tables.removeTable(name);
            expect(tables.getTables()).to.have.lengthOf(0);
        });
    });


    describe("addKeyword", () => {
        it('should not add an existing keyword', () => {
            tables.addKeyword(name, keyword);
            expect(tables.getTables()[0].getKeywords()).to.have.lengthOf(1);
        });

        it('should add a new keyword', () => {
            tables.addKeyword(name, newKeyword);
            expect(tables.getTables()[0].getKeywords()).to.have.lengthOf(2);
        });
    });
});

