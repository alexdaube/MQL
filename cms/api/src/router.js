const connection = require('./common/connection');
const keywords = require("./keywords/keywords");
const operators = require("./operators/operators");
const junctions = require("./junctions/junctions");

var mockTable = {name: "Site", synonyms: ["plant", "building"]};
var mockJunction = {name: "OR", synonyms: []};
var mockOperator = {name: "EQUAL", synonyms: ["is", "equals"]};

keywords.addEntity(mockTable.name, mockTable.synonyms);
junctions.addJunction(mockJunction.name, mockJunction.synonyms);
operators.addOperator(mockOperator.name, mockOperator.synonyms);

module.exports = function (app) {

    app.get('/tables', (req, res) => {
        res.send(keywords.getEntities());
    });

    app.post('/tables/add', (req, res) => {
        var table = keywords.addEntity(req.body.name, req.body.synonyms);
        res.send(table);
    });

    app.post('/tables/:name/add', (req, res) => {
        var table = keywords.addAttribute(req.params.name, req.body.name, req.body.synonyms);
        res.send(table);
    });

    app.post('/tables/:name/remove', (req, res) => {
        var table = keywords.removeAttribute(req.params.name, req.body.name);
        res.send(table);
    });

    app.post('/tables/export', (req, res) => {
        connection.exportTables(keywords.getEntities());
        res.sendStatus(200);
    });

    app.get('/junctions', (req, res) => {
        res.send(junctions.getJunctions());
    });

    app.post('/junctions/add', (req, res) => {
        var junction = junctions.addJunction(req.body.name, req.body.synonyms);
        res.send(junction);
    });

    app.post('/junctions/export', (req, res) => {
        connection.exportJunctions(junctions.getJunctions());
        res.sendStatus(200);
    });

    app.get('/operators', (req, res) => {
        res.send(operators.getOperators());
    });

    app.post('/operators/add', (req, res) => {
        var operator = operators.addOperator(req.body.name, req.body.synonyms);
        res.send(operator);
    });

    app.post('/operators/export', (req, res) => {
        connection.exportOperators(operators.getOperators());
        res.sendStatus(200);
    });
};