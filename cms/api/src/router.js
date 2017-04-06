const connection = require('./common/connection');
// const tables = require("./tables/tables");
const operators = require("./operators/operators");
const junctions = require("./junctions/junctions");
let TableController = require("./controllers/TableController");

let tableController = new TableController(connection);

module.exports = function (app) {
    app.get('/tables', (req, res) => {
        connection.getExistingTables(function (result) {
            res.send(result);
        });
    });

    app.post('/tables', (req, res) => {
        tableController.addTable(req.body.name, res);
    });

    app.delete('/tables/:name', (req, res) => {
        tableController.removeTable(req.params.name, res);
    });

    app.post('/tables/:name/columns', (req, res) => {
        tableController.addColumn(req, res);
    });

    app.delete('/tables/:name/columns/:column', (req, res) => {
        tableController.removeColumn(req, res);
    });

    app.post('/tables/:name/keywords', (req, res) => {
        tableController.addKeyword(req, res);
    });

    app.delete('/tables/:name/keywords/:keyword', (req, res) => {
        tableController.removeKeyword(req, res);
    });

    app.post('/tables/:name/foreign_keys', (req, res) => {
        tableController.addForeignKey(req, res);
    });

    app.post('/tables/:name/foreign_keys/remove', (req, res) => {
        tableController.removeForeignKey(req, res);
    });

    app.post('/tables/:name/columns/:column/keywords', (req, res) => {
        tableController.addColumnKeyword(req, res);
    });

    app.post('/tables/:name/columns/:column/keywords/:keyword', (req, res) => {
        tableController.removeColumnKeyword(req, res);
    });

    app.post('/tables/export', (req, res) => {
        connection.exportTables(tables.getTables());
        res.sendStatus(200);
    });

    app.get('/operators', (req, res) => {
        res.send(operators.getOperators());
    });

    app.post('/operators', (req, res) => {
        operators.addOperator(req.body.type);
        res.sendStatus(201);
    });

    app.delete('/operators/:type', (req, res) => {
        operators.remove(req.params.type);
        res.sendStatus(200);
    });

    app.post('/operators/:type/keywords', (req, res) => {
        operators.addKeyword(req.params.type, req.body.keyword);
        res.sendStatus(201);
    });

    app.delete('/operators/:type/keywords/:keyword', (req, res) => {
        operators.removeKeyword(req.params.type, req.params.keyword);
        res.sendStatus(200);
    });

    app.post('/operators/export', (req, res) => {
        connection.exportOperators(operators.getOperators());
        res.sendStatus(200);
    });

    app.get('/junctions', (req, res) => {
        res.send(junctions.getJunctions());
    });

    app.post('/junctions', (req, res) => {
        junctions.addJunction(req.body.type);
        res.sendStatus(201);
    });

    app.delete('/junctions/:type', (req, res) => {
        junctions.remove(req.params.type);
        res.sendStatus(200);
    });

    app.post('/junctions/:type/keywords', (req, res) => {
        junctions.addKeyword(req.params.type, req.body.keyword);
        res.sendStatus(201);
    });

    app.delete('/junctions/:type/keywords/:keyword', (req, res) => {
        junctions.removeKeyword(req.params.type, req.params.keyword);
        res.sendStatus(200);
    });

    app.post('/junctions/export', (req, res) => {
        connection.exportJunctions(junctions.getJunctions());
        res.sendStatus(200);
    });
};