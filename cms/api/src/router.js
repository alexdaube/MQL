const connection = require('./common/connection');
const tables = require("./tables/tables");
const operators = require("./operators/operators");
const junctions = require("./junctions/junctions");

var mockTable = {name: "Site", keywords: ["plant", "building"]};
var mockJunction = {type: "OR", keywords: []};
var mockOperator = {type: "EQUAL", keywords: ["is", "equals"]};

tables.addTable(mockTable.name);
junctions.addJunction(mockJunction.type);
operators.addOperator(mockOperator.type);

module.exports = function (app) {

    app.get('/tables', (req, res) => {
        connection.getExistingTables(function (result) {
            res.send(result);
        });
    });

    app.post('/tables', (req, res) => {
        tables.addTable(req.body.name);
        let table = tables.getTableFromName(req.body.name);
        connection.saveTable(table, function () {
            res.sendStatus(200);
        });
    });

    app.delete('/tables/:name', (req, res) => {
        tables.removeTable(req.params.name);
        connection.deleteTable(req.params.name, function () {
            res.sendStatus(200);
        });
    });

    app.post('/tables/:name/keywords', (req, res) => {
        tables.addKeyword(req.params.name, req.body.keyword);

        let table = tables.getTableFromName(req.params.name);
        connection.updateTable(table, function () {
            res.sendStatus(200);
        });
    });

    app.delete('/tables/:name/keywords/:keyword', (req, res) => {
        tables.removeKeyword(req.params.name, req.params.keyword);

        let table = tables.getTableFromName(req.params.name);
        connection.updateTable(table, function () {
            res.sendStatus(200);
        });
    });

    app.post('/tables/:name/foreign_keys', (req, res) => {
        tables.addForeignKey(req.params.name, req.body);

        let table = tables.getTableFromName(req.params.name);
        connection.updateTable(table, function () {
            res.sendStatus(200);
        });

    });

    app.post('/tables/:name/foreign_keys/remove', (req, res) => {
        tables.removeForeignKey(req.params.name, req.body);

        let table = tables.getTableFromName(req.params.name);
        connection.updateTable(table, function () {
            res.sendStatus(200);
        });
    });

    app.post('/tables/:name/columns', (req, res) => {
        tables.addColumn(req.params.name, req.body.name);

        let table = tables.getTableFromName(req.params.name);
        connection.updateTable(table, function () {
            res.sendStatus(200);
        });
    });

    app.delete('/tables/:name/columns/:column', (req, res) => {
        tables.removeColumn(req.params.name, req.params.column);

        let table = tables.getTableFromName(req.params.name);
        connection.updateTable(table, function () {
            res.sendStatus(200);
        });
    });

    app.post('/tables/:name/columns/:column/keywords', (req, res) => {
        tables.addColumnKeyword(req.params.name, req.params.column, req.body.keyword);

        let table = tables.getTableFromName(req.params.name);
        connection.updateTable(table, function () {
            res.sendStatus(200);
        });
    });

    app.post('/tables/:name/columns/:column/keywords/:keyword', (req, res) => {
        tables.removeColumnKeyword(req.params.name, req.params.column, req.params.keyword);

        let table = tables.getTableFromName(req.params.name);
        connection.updateTable(table, function () {
            res.sendStatus(200);
        });

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