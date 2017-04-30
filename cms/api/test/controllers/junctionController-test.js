const td = require('testdouble');
const JunctionRepository = require("../../src/persistence/repositories/JunctionRepository");
const Junctions = require("../../src/junctions/junctions");
const Junction = require("../../src/junctions/junction");
const JunctionController = require("../../src/controllers/JunctionController");


describe('JunctionController', () => {
    let repository, controller, junctions, req, res, junction, callback;

    beforeEach(() => {
        repository = td.object(new JunctionRepository);
        junctions = td.object(new Junctions);
        junction = td.object(new Junction);
        callback = td.function("callback");
        req = {};
        res = {sendStatus: td.function("sendStatus")};
        controller = new JunctionController(repository, junctions);
    });

    afterEach(() => {
        td.reset();
    });

    // it('should get all the junctions', () => {
    //     td.verify(app.get(getJunctionsPath, controller.getAll));
    // });

    it('should add a junction', () => {
        const type = "hello";
        req = {body: {type}};
        td.when(controller.junctions.getJunctionFromType(type)).thenReturn(junction);
        td.when(controller.repository.save(junction)).thenCallback();
        controller.addJunction(req, res);
        td.verify(controller.junctions.addJunction(type));
        td.verify(controller.junctions.getJunctionFromType(type));
        //td.verify(controller.repository.save(junction, td.matchers.anything));
        td.verify(res.sendStatus(200));
    });

    // it('should remove a junction', () => {
    //     td.verify(app.delete(removeJunctionPath, controller.removeJunction));
    // });
    //
    // it('should add a keyword to a junction', () => {
    //     td.verify(app.post(addKeywordPath, controller.addKeyword));
    // });
    //
    // it('should remove a keyword from a junction', () => {
    //     td.verify(app.delete(removeKeywordPath, controller.removeKeyword));
    // });
});
