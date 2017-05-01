const td = require('testdouble');
const router = require('../../src/routers/operatorRouter');
const mockExpress = require("../helpers/mockExpress");
const OperatorController = require("../../src/controllers/operatorController");


describe('operatorRouter', () => {
    const getOperatorsPath = "/operators";
    const addOperatorPath = "/operators";
    const removeOperatorPath = "/operators/:type";
    const addKeywordPath = "/operators/:type/keywords";
    const removeKeywordPath = "/operators/:type/keywords/:keyword";
    let app, controller;

    beforeEach(() => {
        app = mockExpress.app;
        controller = td.object(new OperatorController);
        router(app, controller);
    });

    afterEach(() => {
        td.reset();
    });

    it('should get all the operators', () => {
        td.verify(app.get(getOperatorsPath, controller.getAll));
    });

    it('should add an operator', () => {
        td.verify(app.post(addOperatorPath, controller.addOperator));
    });

    it('should remove an operator', () => {
        td.verify(app.delete(removeOperatorPath, controller.removeOperator));
    });

    it('should add a keyword to an operator', () => {
        td.verify(app.post(addKeywordPath, controller.addKeyword));
    });

    it('should remove a keyword from an operator', () => {
        td.verify(app.delete(removeKeywordPath, controller.removeKeyword));
    });
});
