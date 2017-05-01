const td = require('testdouble');
const mockExpress = require("../helpers/mockExpress");
const MongoRepository = require("../../src/persistence/repositories/mongoRepository");
const Connection = require("../../src/persistence/connection");
const Operators = require("../../src/operators/operators");
const OperatorsConverter = require("../../src/operators/converter");
const Operator = require("../../src/operators/operator");
const OperatorController = require("../../src/controllers/operatorController");


describe('OperatorController', () => {
    const fetchedOperators = [];
    const type = "operator";
    const keyword = "keyword";
    const repoReturnedError = {hasError: true};
    let connection, repository, converter, controller, operators, operator, req, res;

    beforeEach(() => {
        connection = td.object(new Connection);
        repository = td.object(new MongoRepository(connection));
        converter = td.object(new OperatorsConverter);
        operators = td.constructor(new Operators);
        operator = td.object(new Operator);
        req = {};
        res = mockExpress.res;
        controller = new OperatorController(repository, converter);
        td.when(repository.getAll()).thenReturn(fetchedOperators);
    });

    afterEach(() => {
        td.reset();
    });

    const testCannotFetchOperators = (testedMethod) => {
        it('cannot fetch the current operators', () => {
            td.when(converter.mapToOperator(fetchedOperators)).thenReturn(repoReturnedError);

            controller[testedMethod](req, res);

            td.verify(res.sendStatus(404));
        });
    };

    describe('getAll', () => {
        testCannotFetchOperators("getAll");

        it('should return all the current operators', () => {
            td.when(converter.mapToOperator(fetchedOperators)).thenReturn(operators);

            controller.getAll(req, res);

            td.verify(res.send(operators));
        });
    });

    describe('addOperator', () => {
        beforeEach(() => {
            req = {body: {type: type} };
        });

        afterEach(() => {
            td.reset();
        });
        testCannotFetchOperators("addOperator");

        it('cannot save the new operator', () => {
            td.when(converter.mapToOperator(fetchedOperators)).thenReturn(operators);
            td.when(repository.save(td.matchers.anything())).thenReturn(repoReturnedError);

            controller.addOperator(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can add a new operator', () => {
            td.when(converter.mapToOperator(fetchedOperators)).thenReturn(operators);
            td.when(repository.save(td.matchers.anything())).thenReturn({});

            controller.addOperator(req, res);

            td.verify(res.sendStatus(200));
        });
    });

    describe('removeOperator', () => {
        beforeEach(() => {
            req = {params: {type: type} };
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchOperators("removeOperator");
        it('cannot destroy an operator', () => {
            td.when(converter.mapToOperator(fetchedOperators)).thenReturn(operators);
            td.when(repository.destroy({type})).thenReturn(repoReturnedError);

            controller.removeOperator(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can destroy an operator', () => {
            td.when(converter.mapToOperator(fetchedOperators)).thenReturn(operators);
            td.when(repository.destroy({type})).thenReturn({});

            controller.removeOperator(req, res);

            td.verify(res.sendStatus(200));
        });
    });

    describe('addKeyword', () => {
        beforeEach(() => {
            req = {params: {type: type}, body: {keyword: keyword} };
            operators = new Operators([new Operator(type, [keyword])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchOperators("addKeyword");
        it('cannot update while adding a keyword', () => {
            td.when(converter.mapToOperator(fetchedOperators)).thenReturn(operators);
            td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

            controller.addKeyword(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can add a new keyword to an operator', () => {
            td.when(converter.mapToOperator(fetchedOperators)).thenReturn(operators);
            td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

            controller.addKeyword(req, res);

            td.verify(res.sendStatus(200));
        });
    });

    describe('removeKeyword', () => {
        beforeEach(() => {
            req = {params: {type: type, keyword: keyword} };
            operators = new Operators([new Operator(type, [keyword])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchOperators("removeKeyword");
        it('cannot update while removing a keyword', () => {
            td.when(converter.mapToOperator(fetchedOperators)).thenReturn(operators);
            td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

            controller.removeKeyword(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can remove a keyword', () => {
            td.when(converter.mapToOperator(fetchedOperators)).thenReturn(operators);
            td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

            controller.removeKeyword(req, res);

            td.verify(res.sendStatus(200));
        });
    });
});
