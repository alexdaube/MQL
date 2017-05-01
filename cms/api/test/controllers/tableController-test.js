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
    const foreignKey = {};
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
        it('cannot update while adding a column', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

            controller.addColumn(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can add a new column to a table', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

            controller.addColumn(req, res);

            td.verify(res.sendStatus(200));
        });
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
        it('cannot update while removing a column', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

            controller.removeColumn(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can remove a column', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

            controller.removeColumn(req, res);

            td.verify(res.sendStatus(200));
        });
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
        it('cannot update while adding a keyword', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

            controller.addKeyword(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can add a new keyword to a table', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

            controller.addKeyword(req, res);

            td.verify(res.sendStatus(200));
        });
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
        it('cannot update while removing a keyword', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

            controller.removeKeyword(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can remove a keyword', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

            controller.removeKeyword(req, res);

            td.verify(res.sendStatus(200));
        });
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
        it('cannot update while adding a foreign key', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

            controller.addForeignKey(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can add a new foreign key to a table', () => {
            td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
            td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

            controller.addForeignKey(req, res);

            td.verify(res.sendStatus(200));
        });

        describe('removeForeignKey', () => {
            beforeEach(() => {
                req = {params: {name: name, keyword: keyword} };
                tables = new Tables([new Table(name, [keyword], [new Column(column)], [new ForeignKey(column, name, "anotherColumn")])]);
            });

            afterEach(() => {
                td.reset();
            });

            testCannotFetchTables("removeForeignKey");
            it('cannot update while removing a keyword', () => {
                td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
                td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

                controller.removeForeignKey(req, res);

                td.verify(res.sendStatus(404));
            });

            it('can remove a keyword', () => {
                td.when(converter.mapToTables(fetchedTables)).thenReturn(tables);
                td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

                controller.removeForeignKey(req, res);

                td.verify(res.sendStatus(200));
            });
        });
    });
});

