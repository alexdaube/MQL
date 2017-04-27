const td = require('testdouble');
const router = require('../../src/routers/junctionRouter');
const mockExpress = require("../helpers/mockExpress");
const JunctionController = require("../../src/controllers/JunctionController");


describe('junctionRouter', () => {
    const getJunctionsPath = "/junctions";
    const addJunctionPath = "/junctions";
    const removeJunctionPath = "/junctions/:type";
    const addKeywordPath = "/junctions/:type/keywords";
    const removeKeywordPath = "/junctions/:type/keywords/:keyword";
    let app, controller;

    beforeEach(() => {
        app = mockExpress;
        controller = td.object(new JunctionController);
        router(app, controller);
    });

    afterEach(() => {
        td.reset();
    });

    it('should get all the junctions', () => {
        td.verify(app.get(getJunctionsPath, controller.getAll));
    });

    it('should add a junction', () => {
        td.verify(app.post(addJunctionPath, controller.addJunction));
    });

    it('should remove a junction', () => {
        td.verify(app.delete(removeJunctionPath, controller.removeJunction));
    });

    it('should add a keyword to a junction', () => {
        td.verify(app.post(addKeywordPath, controller.addKeyword));
    });

    it('should remove a keyword from a junction', () => {
        td.verify(app.delete(removeKeywordPath, controller.removeKeyword));
    });
});
