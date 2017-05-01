const td = require('testdouble');
const Table = require("../../src/tables/table");
const Column = require("../../src/tables/column");
const expect = require("chai").expect;

describe('Table', () => {
    const name = "table";
    const columnName = "column";
    const keyword = "keyword";
    const newKeyword = "newkeyword";
    const keywords = [keyword];
    let table, column;

    beforeEach(() => {
        column = new Column(columnName, [keyword]);
        table = new Table(name, keywords, [column]);
    });

    afterEach(() => {
        td.reset();
    });

    describe("addKeyword", () => {
        it('should not add an existing keyword', () => {
            table.addKeyword(keyword);
            expect(table.getKeywords()).to.have.lengthOf(1);
        });

        it('should add a new keyword', () => {
            table.addKeyword(newKeyword);
            expect(table.getKeywords()).to.have.lengthOf(2);
        });
    });

    describe("addColumnKeyword", () => {
        it('should not add an existing keyword', () => {
            table.addColumnKeyword(columnName, keyword);
            expect(table.getColumns()[0].getKeywords()).to.have.lengthOf(1);
        });

        it('should add a new keyword', () => {
            table.addColumnKeyword(columnName, newKeyword);
            expect(table.getColumns()[0].getKeywords()).to.have.lengthOf(2);
        });
    });

    describe("removeColumnKeyword", () => {
        it('should not remove an un-existing keyword', () => {
            table.removeColumnKeyword(columnName, newKeyword);
            expect(table.getColumns()[0].getKeywords()).to.have.lengthOf(1);
        });

        it('should remove an existing keyword', () => {
            table.removeColumnKeyword(columnName, keyword);
            expect(table.getColumns()[0].getKeywords()).to.have.lengthOf(0);
        });
    });
});

