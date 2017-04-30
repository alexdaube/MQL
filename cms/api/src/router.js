const connection = require('./persistence/connection');
let TableController = require("./controllers/TableController");
let JunctionController = require("./controllers/JunctionController");
let OperatorController = require("./controllers/OperatorController");

let tableController = new TableController(connection);
let operatorController = new OperatorController(connection);
let junctionController = new JunctionController(connection);

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

    app.delete('/tables/:name/columns/:column/keywords/:keyword', (req, res) => {
        tableController.removeColumnKeyword(req, res);
    });

    app.get('/operators', (req, res) => {
        connection.getExistingOperators(function (result) {
            res.send(result);
        });
    });

    app.post('/operators', (req, res) => {
        operatorController.addOperator(req.body.type, res);
    });

    app.delete('/operators/:type', (req, res) => {
        operatorController.removeOperator(req.params.type, res);
    });

    app.post('/operators/:type/keywords', (req, res) => {
        operatorController.addKeyword(req, res);
    });

    app.delete('/operators/:type/keywords/:keyword', (req, res) => {
        operatorController.removeKeyword(req, res);
    });

    app.get('/junctions', (req, res) => {
        connection.getExistingJunctions(function (result) {
            res.send(result);
        });
    });

    app.post('/junctions', (req, res) => {
        junctionController.addJunction(req.body.type, res);
    });

    app.delete('/junctions/:type', (req, res) => {
        junctionController.removeJunction(req.params.type, res);
    });

    app.post('/junctions/:type/keywords', (req, res) => {
        junctionController.addKeyword(req, res);
    });

    app.delete('/junctions/:type/keywords/:keyword', (req, res) => {
        junctionController.removeKeyword(req, res);
    });
};
