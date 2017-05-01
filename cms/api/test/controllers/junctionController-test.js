const td = require('testdouble');
const mockExpress = require("../helpers/mockExpress");
const MongoRepository = require("../../src/persistence/repositories/JunctionRepository");
const Junctions = require("../../src/junctions/junctions");
const JunctionsConverter = require("../../src/junctions/converter");
const Junction = require("../../src/junctions/junction");
const JunctionController = require("../../src/controllers/junctionController");


describe('JunctionController', () => {
    const fetchedJunctions = [];
    const type = "junction";
    const keyword = "keyword";
    const repoReturnedError = {hasError: true};
    let repository, converter, controller, junctions, req, res, junction;

    beforeEach(() => {
        repository = td.object(new MongoRepository);
        converter = td.object(new JunctionsConverter);
        junctions = td.constructor(new Junctions);
        junction = td.object(new Junction);
        req = {};
        res = mockExpress.res;
        controller = new JunctionController(repository, converter);
        td.when(repository.getAll()).thenReturn(fetchedJunctions);
    });

    afterEach(() => {
        td.reset();
    });

    const testCannotFetchJunctions = (testedMethod) => {
        it('cannot fetch the current junctions', () => {
            td.when(converter.mapToJunction(fetchedJunctions)).thenReturn(repoReturnedError);

            controller[testedMethod](req, res);

            td.verify(res.sendStatus(404));
        });
    };

    describe('getAll', () => {
        testCannotFetchJunctions("getAll");

        it('should return all the current junctions', () => {
            td.when(converter.mapToJunction(fetchedJunctions)).thenReturn(junctions);

            controller.getAll(req, res);

            td.verify(res.send(junctions));
        });
    });

    describe('addJunction', () => {
        beforeEach(() => {
            req = {body: {type: type} };
        });

        afterEach(() => {
            td.reset();
        });
        testCannotFetchJunctions("addJunction");

        it('cannot save the new junction', () => {
            td.when(converter.mapToJunction(fetchedJunctions)).thenReturn(junctions);
            td.when(repository.save(td.matchers.anything())).thenReturn(repoReturnedError);

            controller.addJunction(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can add a new junction', () => {
            td.when(converter.mapToJunction(fetchedJunctions)).thenReturn(junctions);
            td.when(repository.save(td.matchers.anything())).thenReturn({});

            controller.addJunction(req, res);

            td.verify(res.sendStatus(200));
        });
    });

    describe('removeJunction', () => {
        beforeEach(() => {
            req = {params: {type: type} };
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchJunctions("removeJunction");
        it('cannot destroy a junction', () => {
            td.when(converter.mapToJunction(fetchedJunctions)).thenReturn(junctions);
            td.when(repository.destroy({type})).thenReturn(repoReturnedError);

            controller.removeJunction(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can destroy a junction', () => {
            td.when(converter.mapToJunction(fetchedJunctions)).thenReturn(junctions);
            td.when(repository.destroy({type})).thenReturn({});

            controller.removeJunction(req, res);

            td.verify(res.sendStatus(200));
        });
    });

    describe('addKeyword', () => {
        beforeEach(() => {
            req = {params: {type: type}, body: {keyword: keyword} };
            junctions = new Junctions([new Junction(type, [keyword])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchJunctions("addKeyword");
        it('cannot update while adding a keyword', () => {
            td.when(converter.mapToJunction(fetchedJunctions)).thenReturn(junctions);
            td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

            controller.addKeyword(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can add a new keyword to a junction', () => {
            td.when(converter.mapToJunction(fetchedJunctions)).thenReturn(junctions);
            td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

            controller.addKeyword(req, res);

            td.verify(res.sendStatus(200));
        });
    });

    describe('removeKeyword', () => {
        beforeEach(() => {
            req = {params: {type: type, keyword: keyword} };
            junctions = new Junctions([new Junction(type, [keyword])]);
        });

        afterEach(() => {
            td.reset();
        });

        testCannotFetchJunctions("removeKeyword");
        it('cannot update while removing a keyword', () => {
            td.when(converter.mapToJunction(fetchedJunctions)).thenReturn(junctions);
            td.when(repository.update(td.matchers.anything(), td.matchers.anything())).thenReturn(repoReturnedError);

            controller.removeKeyword(req, res);

            td.verify(res.sendStatus(404));
        });

        it('can remove a keyword', () => {
            td.when(converter.mapToJunction(fetchedJunctions)).thenReturn(junctions);
            td.when(repository.update(td.matchers.anything(),td.matchers.anything())).thenReturn({});

            controller.removeKeyword(req, res);

            td.verify(res.sendStatus(200));
        });
    });
});
