const td = require('testdouble');
const mockExpress = require("../helpers/mockExpress");
const MongoRepository = require("../../src/persistence/repositories/mongoRepository");
const Tables = require("../../src/tables/tables");
const TablesConverter = require("../../src/tables/converter");
const Table = require("../../src/tables/table");
const Column = require("../../src/tables/column");
const ForeignKey = require("../../src/tables/foreignKey");
const TableController = require("../../src/controllers/TableController");


describe('TableController', () => {
    const fetchedTables = [];
    const name = "table";
    const keyword = "keyword";
    const column = "column";
    const anotherColummn = "anotherColumn";
    const repoReturnedError = {hasError: true};
    let repository, converter, controller, tables, table, req, res;

    beforeEach(() => {
        repository = td.object(new MongoRepository);
        converter = td.object(new TablesConverter);
        tables = td.constructor(new Tables);
        table = td.object(new Table);
        req = {};
        res = mockExpress.res;
        controller = new TableController(repository, converter);
        td.when(repository.getAll()).thenReturn(fetchedTables);
    });

    afterEach(() => {
        td.reset();
    });

    const testCannotFetchTables = (testedMethod) => {
        it('cannot fetch the current tables', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(repoReturnedError);

            controller[testedMethod](req, res);

            td.verify(res.sendStatus(404));
        });
    };

    const testUpdateFails = (testedMethod, tesMessage) => {
        it(tesMessage, () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

            controller[testedMethod](req, res);

            td.verify(res.sendStatus(404));
        });
    };

    const testUpdateSuccess = (testedMethod, testMessage) => {
        it(testMessage, () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

            controller[testedMethod](req, res);

            td.verify(res.sendStatus(200));
        });
    };

    describe('getAll', () => {
        testCannotFetchTables("getAll");

        it('should return all the current tables', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);

            controller.getAll(req, res);

            td.verify(res.send(tables));
        });
    });

    describe('addTable', () => {
        beforeEach(() => {
            req = {body: {name: name} };
        });

        afterEach(() => {
            td.reset();
        });
        testCannotFetchTables("addTable");

        it('cannot save the new table', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.save(td.matchers.anything())).thenReturn(repoReturnedError);

            controller.addTable(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can add a new table', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.save(td.matchers.anything())).thenReturn({});

            controller.addTable(req, res);

            td.verify(res.sendStatus(200));
        });
    });

    describe('removeTable', () => {
        beforeEach(() => {
            req = {params: {name: name} };
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchTables("removeTable");
        it('cannot destroy a table', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.destroy({name})).thenReturn(repoReturnedError);

            controller.removeTable(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can destroy a table', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.destroy({name})).thenReturn({});

            controller.removeTable(req, res);

            td.verify(res.sendStatus(200));
        });
    });

    describe('addColumn', () => {
        beforeEach(() => {
            req = {params: {name: name}, body: {name: name} };
            tables = new Tables([new Table(name, [keyword])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchTables("addColumn");
        testUpdateFails("addColumn", 'cannot update while adding a column');
        testUpdateSuccess("addColumn", 'can add a new column to a table');
    });

    describe('removeColumn', () => {
        beforeEach(() => {
            req = {params: {name: name, column: column} };
            tables = new Tables([new Table(name, [keyword], [new Column(column)])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchTables("removeColumn");
        testUpdateFails("removeColumn", 'cannot update while removing a column');
        testUpdateSuccess("removeColumn", 'can remove a column');
    });

    describe('addKeyword', () => {
        beforeEach(() => {
            req = {params: {name: name}, body: {keyword: keyword} };
            tables = new Tables([new Table(name, [keyword])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchTables("addKeyword");
        testUpdateFails("addKeyword", 'cannot update while adding a keyword');
        testUpdateSuccess("addKeyword", 'can add a new keyword to a table');
    });

    describe('removeKeyword', () => {
        beforeEach(() => {
            req = {params: {name: name, keyword: keyword} };
            tables = new Tables([new Table(name, [keyword])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchTables("removeKeyword");
        testUpdateFails("removeKeyword", 'cannot update while removing a keyword');
        testUpdateSuccess("removeKeyword", "can remove a keyword");
    });

    describe('addForeignKey', () => {
        beforeEach(() => {
            req = {params: {name: name}, body: {keyword: keyword} };
            tables = new Tables([new Table(name, [keyword])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchTables("addForeignKey");
        testUpdateFails("addForeignKey", 'cannot update while adding a foreign key');
        testUpdateSuccess("addForeignKey", "can add a new foreign key to a table");
    });

    describe('removeForeignKey', () => {
        beforeEach(() => {
            req = {params: {name: name}, body: {fromColumn: column, toTable: name, toColumn: anotherColummn} };
            tables = new Tables([new Table(name, [keyword], [new Column(column)], [new ForeignKey(column, name, anotherColummn)])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchTables("removeForeignKey");
        testUpdateFails("removeForeignKey", 'cannot update while removing a foreign key');
        testUpdateSuccess("removeForeignKey", 'can remove a foreign key');
    });

    describe('addColumnKeyword', () => {
        beforeEach(() => {
            req = {params: {name: name, column: column}, body: {keyword: keyword} };
            tables = new Tables([new Table(name, [keyword])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchTables("addColumnKeyword");
        testUpdateFails("addColumnKeyword", 'cannot update while adding a keyword to a column');
        testUpdateSuccess("addColumnKeyword", 'can add a new keyword to a column');
    });

    describe('removeColumnKeyword', () => {
        beforeEach(() => {
            req = {params: {name, column, keyword} };
            tables = new Tables([new Table(name, [keyword], [new Column(column)])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchTables("removeColumnKeyword");
        testUpdateFails("removeColumnKeyword", 'cannot update while removing a keyword from a column');
        testUpdateSuccess("removeColumnKeyword", 'can remove a keyword from a column');
    });
});

