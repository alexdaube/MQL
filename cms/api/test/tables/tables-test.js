const td = require('testdouble');
const Tables = require("../../src/tables/tables");
const Table = require("../../src/tables/table");
const Column = require("../../src/tables/column");
const ForeignKey = require("../../src/tables/foreignKey");
const expect = require("chai").expect;

describe('Tables', () => {
    const name = "table";
    const anotherTableName = "anotherTable";
    const columnName = "column";
    const newColumnName = "newcolumn";
    const keyword = "keyword";
    const newKeyword = "newkeyword";
    const keywords = [keyword];
    const fromColumn = "fromColumn";
    const toTable = "toTable";
    const toColumn = "toColumn";
    const newForeignKeyObj = {fromColumn: "newFromColumn", toTable: "newToTable", toColumn: "newToColum "};
    const foreignKeyObj = {fromColumn, toTable, toColumn};
    let tables, table, column;

    beforeEach(() => {
        column = new Column(columnName, [keyword]);
        table = new Table(name, keywords, [new Column(columnName, keywords)], [new ForeignKey(fromColumn, toTable, toColumn)]);
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

    describe("removeKeyword", () => {
        it('should not add an non-existing keyword', () => {
            tables.removeKeyword(name, newKeyword);
            expect(tables.getTables()[0].getKeywords()).to.have.lengthOf(1);
        });

        it('should remove an existing keyword', () => {
            tables.removeKeyword(name, keyword);
            expect(tables.getTables()[0].getKeywords()).to.have.lengthOf(0);
        });
    });

    describe("addColumn", () => {
        it('should not add an existing column', () => {
            tables.addColumn(name, columnName);
            expect(tables.getTables()[0].getColumns()).to.have.lengthOf(1);
        });

        it('should add a new column', () => {
            tables.addColumn(name, newColumnName);
            expect(tables.getTables()[0].getColumns()).to.have.lengthOf(2);
        });
    });

    describe("removeColumn", () => {
        it('should not remove an non existing column', () => {
            tables.removeColumn(name, newColumnName);
            expect(tables.getTables()[0].getColumns()).to.have.lengthOf(1);
        });

        it('should remove an existing column', () => {
            tables.removeColumn(name, columnName);
            expect(tables.getTables()[0].getColumns()).to.have.lengthOf(0);
        });
    });

    describe("addForeignKey", () => {
        it('should not add an existing foreign key', () => {
            tables.addForeignKey(anotherTableName, newForeignKeyObj);
            expect(tables.getTables()[0].getForeignKeys()).to.have.lengthOf(1);
        });

        it('should add a new foreign key', () => {
            tables.addForeignKey(name, newForeignKeyObj);
            expect(tables.getTables()[0].getForeignKeys()).to.have.lengthOf(2);
        });
    });

    describe("removeForeignKey", () => {
        it('should not remove on a non existing table', () => {
            tables.removeForeignKey(anotherTableName, foreignKeyObj);
            expect(tables.getTables()[0].getForeignKeys()).to.have.lengthOf(1);
        });

        it('should not remove an non existing foreign key', () => {
            tables.removeForeignKey(anotherTableName, newForeignKeyObj);
            expect(tables.getTables()[0].getForeignKeys()).to.have.lengthOf(1);
        });

        it('should remove an existing foreign key', () => {
            tables.removeForeignKey(name, foreignKeyObj);
            expect(tables.getTables()[0].getForeignKeys()).to.have.lengthOf(0);
        });
    });

    describe("addColumnKeyword", () => {
        it('should not add a keyword to a non existing table', () => {
            tables.addColumnKeyword(anotherTableName, columnName, newKeyword);
            expect(tables.getTables()[0].getColumns()[0].getKeywords()).to.have.lengthOf(1);
        });

        it('should not add an existing keyword ', () => {
            tables.addColumnKeyword(name, columnName, keyword);
            expect(tables.getTables()[0].getColumns()[0].getKeywords()).to.have.lengthOf(1);
        });

        it('should add a new keyword ', () => {
            tables.addColumnKeyword(name, columnName, newKeyword);
            expect(tables.getTables()[0].getColumns()[0].getKeywords()).to.have.lengthOf(2);
        });
    });

    describe("removeColumnKeyword", () => {
        it('should not remove on a non existing table', () => {
            tables.removeColumnKeyword(anotherTableName, columnName, keyword);
            expect(tables.getTables()[0].getColumns()[0].getKeywords()).to.have.lengthOf(2);
        });

        it('should not remove an non existing keyword', () => {
            tables.removeColumnKeyword(name, columnName, newKeyword);
            expect(tables.getTables()[0].getColumns()[0].getKeywords()).to.have.lengthOf(1);
        });

        it('should remove an existing foreign key', () => {
            tables.removeColumnKeyword(name, columnName, keyword);
            expect(tables.getTables()[0].getColumns()[0].getKeywords()).to.have.lengthOf(1);
        });
    });

    describe("getTableFromName", () => {
        it('should return table with the name', () => {
            expect(tables.getTableFromName(name)).to.eql(table);
        });
    });
});

