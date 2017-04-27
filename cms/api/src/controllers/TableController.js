const tables = require("../tables/tables");

"use strict";
class TableController {
    constructor(connection) {
        this.connection = connection;
    }

    getAll(req, res) {}

    addTable(req, res) {
        const tableName = req.body.name;
        tables.addTable(tableName);
        let table = tables.getTableFromName(tableName);
        this.connection.save(table, function () {
            res.sendStatus(200);
        });
    }

    removeTable(req, res) {
        const tableName = req.params.name;
        tables.removeTable(tableName);
        this.connection.destroy(tableName, function () {
            res.sendStatus(200);
        });
    }

    addColumn(req, res) {
        tables.addColumn(req.params.name, req.body.name);
        let table = tables.getTableFromName(req.params.name);
        this.connection.update(table, function () {
            res.sendStatus(200);
        });
    };

    removeColumn(req, res) {
        tables.removeColumn(req.params.name, req.params.column);
        let table = tables.getTableFromName(req.params.name);
        this.connection.update(table, function () {
            res.sendStatus(200);
        });
    };

    addKeyword(req, res) {
        tables.addKeyword(req.params.name, req.body.keyword);
        let table = tables.getTableFromName(req.params.name);
        this.connection.update(table, function () {
            res.sendStatus(200);
        });
    };

    removeKeyword(req, res) {
        tables.removeKeyword(req.params.name, req.params.keyword);
        let table = tables.getTableFromName(req.params.name);
        this.connection.updateTable(table, function () {
            res.sendStatus(200);
        });
    };

    addForeignKey(req, res) {
        tables.addForeignKey(req.params.name, req.body);
        let table = tables.getTableFromName(req.params.name);
        this.connection.update(table, function () {
            res.sendStatus(200);
        });
    };

    removeForeignKey(req, res) {
        tables.removeForeignKey(req.params.name, req.body);
        let table = tables.getTableFromName(req.params.name);
        this.connection.update(table, function () {
            res.sendStatus(200);
        });
    };

    addColumnKeyword(req, res) {
        tables.addColumnKeyword(req.params.name, req.params.column, req.body.keyword);
        let table = tables.getTableFromName(req.params.name);
        this.connection.update(table, function () {
            res.sendStatus(200);
        });
    };

    removeColumnKeyword(req, res) {
        tables.removeColumnKeyword(req.params.name, req.params.column, req.params.keyword);
        let table = tables.getTableFromName(req.params.name);
        this.connection.update(table, function () {
            res.sendStatus(200);
        });
    };
}

module.exports = TableController;