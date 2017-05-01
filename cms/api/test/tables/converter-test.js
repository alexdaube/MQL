const td = require('testdouble');
const TablesConverter = require("../../src/tables/converter");
const expect = require("chai").expect;

describe('TablesConverter', () => {
    const name = "table";
    const keywords = ["hello"];
    const fromColumn = "from";
    const toTable = "toT";
    const toColumn = "toC";
    const fetchedTables = [{name, keywords, columns: [name, keywords], foreignKeys: [fromColumn, toTable, toColumn]}];
    const emptyFetchedTables = [];
    let converter;

    beforeEach(() => {
        converter = new TablesConverter;
    });

    afterEach(() => {
        td.reset();
    });

    describe("mapToTables", () => {
        it('converts the returned dbTables to Tables object', () => {
            const tables = converter.mapToTables(fetchedTables);
            expect(tables.getTables()).to.have.lengthOf(1);
        });

        it('returns an empty Tables object by default', () => {
            const tables = converter.mapToTables(emptyFetchedTables);
            expect(tables.getTables()).to.have.lengthOf(0);
        });
    });
});

