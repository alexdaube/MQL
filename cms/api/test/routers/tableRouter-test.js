const td = require('testdouble');
const router = require('../../src/routers/tableRouter');
const mockExpress = require("../helpers/mockExpress");
const TableController = require("../../src/controllers/TableController");


describe('tableRouter', () => {
    const getTablesPath = "/tables";
    const addTablesPath = "/tables";
    const removeTablesPath = "/tables/:name";
    const addColumnPath = "/tables/:name/columns";
    const removeColumnPath = "/tables/:name/columns/:column";
    const addKeywordPath = "/tables/:name/keywords";
    const removeKeywordPath = "/tables/:name/keywords/:keyword";
    const addForeignKeyPath = "/tables/:name/foreign_keys";
    const removeForeignKeyPath = "/tables/:name/foreign_keys/remove";
    const addColumnKeywordPath = "/tables/:name/columns/:column/keywords";
    const removeColumnKeywordPath = "/tables/:name/columns/:column/keywords/:keyword";
    let app, controller;

    beforeEach(() => {
        app = mockExpress.app;
        controller = td.object(new TableController);
        router(app, controller);
    });

    afterEach(() => {
        td.reset();
    });

    it('should get all the tables', () => {
        td.verify(app.get(getTablesPath, controller.getAll));
    });

    it('should add a table', () => {
        td.verify(app.post(addTablesPath, controller.addTable));
    });

    it('should remove a table', () => {
        td.verify(app.delete(removeTablesPath, controller.removeTable));
    });

    it('should add a column to a table', () => {
        td.verify(app.post(addColumnPath, controller.addColumn));
    });

    it('should remove a column to a table', () => {
        td.verify(app.delete(removeColumnPath, controller.removeColumn));
    });

    it('should add a keyword to a table', () => {
        td.verify(app.post(addKeywordPath, controller.addKeyword));
    });

    it('should remove a keyword from a table', () => {
        td.verify(app.delete(removeKeywordPath, controller.removeKeyword));
    });

    it('should add a foreign key to a table', () => {
        td.verify(app.post(addForeignKeyPath, controller.addForeignKey));
    });

    it('should remove a foreign key from a table', () => {
        td.verify(app.post(removeForeignKeyPath, controller.removeForeignKey));
    });

    it('should add a keyword to a column of a certain table', () => {
        td.verify(app.post(addColumnKeywordPath, controller.addColumnKeyword));
    });

    it('should remove a keyword from a column of a certain table', () => {
        td.verify(app.delete(removeColumnKeywordPath, controller.removeColumnKeyword));
    });
});
