const Tables = require("../tables/tables");
const Table = require("../tables/table");
const Column = require("../tables/column");
const ForeignKey = require("../tables/foreignKey");

"use strict";
class TableController {

    constructor(connection) {
        this.connection = connection;
    }

    addTable(table_name, res) {
        this.connection.getExistingTables((fetchTables) => {
            let tables = mapToTables(fetchTables);
            tables.addTable(table_name);
            let table = tables.getTableFromName(table_name);
            this.connection.saveTable(table, function () {
                res.sendStatus(200);
            });
        });
    }

    removeTable(table_name, res) {
        this.connection.getExistingTables((fetchTables) => {
            let tables = mapToTables(fetchTables);
            tables.removeTable(table_name);
            this.connection.deleteTable(table_name, function () {
                res.sendStatus(200);
            });
        });
    }

    addColumn(req, res) {
        this.connection.getExistingTables((fetchTables) => {
            let tables = mapToTables(fetchTables);
            tables.addColumn(req.params.name, req.body.name);
            let table = tables.getTableFromName(req.params.name);
            this.connection.updateTable(table, function () {
                res.sendStatus(200);
            });
        });
    };

    removeColumn(req, res) {
        this.connection.getExistingTables((fetchTables) => {
            let tables = mapToTables(fetchTables);
            tables.removeColumn(req.params.name, req.params.column);
            let table = tables.getTableFromName(req.params.name);
            this.connection.updateTable(table, function () {
                res.sendStatus(200);
            });
        });
    };

    addKeyword(req, res) {
        this.connection.getExistingTables((fetchTables) => {
            let tables = mapToTables(fetchTables);
            tables.addKeyword(req.params.name, req.body.keyword);
            let table = tables.getTableFromName(req.params.name);
            this.connection.updateTable(table, function () {
                res.sendStatus(200);
            });
        });
    };

    removeKeyword(req, res) {
        this.connection.getExistingTables((fetchTables) => {
            let tables = mapToTables(fetchTables);
            tables.removeKeyword(req.params.name, req.params.keyword);
            let table = tables.getTableFromName(req.params.name);
            this.connection.updateTable(table, function () {
                res.sendStatus(200);
            });
        });
    };

    addForeignKey(req, res) {
        this.connection.getExistingTables((fetchTables) => {
            let tables = mapToTables(fetchTables);
            tables.addForeignKey(req.params.name, req.body);
            let table = tables.getTableFromName(req.params.name);
            this.connection.updateTable(table, function () {
                res.sendStatus(200);
            });
        });
    };

    removeForeignKey(req, res) {
        this.connection.getExistingTables((fetchTables) => {
            let tables = mapToTables(fetchTables);
            tables.removeForeignKey(req.params.name, req.body);
            let table = tables.getTableFromName(req.params.name);
            this.connection.updateTable(table, function () {
                res.sendStatus(200);
            });
        });
    };

    addColumnKeyword(req, res) {
        this.connection.getExistingTables((fetchTables) => {
            let tables = mapToTables(fetchTables);
            tables.addColumnKeyword(req.params.name, req.params.column, req.body.keyword);
            let table = tables.getTableFromName(req.params.name);
            this.connection.updateTable(table, function () {
                res.sendStatus(200);
            });
        });
    };

    removeColumnKeyword(req, res) {
        this.connection.getExistingTables((fetchTables) => {
            let tables = mapToTables(fetchTables);
            tables.removeColumnKeyword(req.params.name, req.params.column, req.params.keyword);
            let table = tables.getTableFromName(req.params.name);
            this.connection.updateTable(table, function () {
                res.sendStatus(200);
            });
        });
    };
}

function mapToTables(dbTables) {
    return new Tables(dbTables.map(t => new Table(t.name, t.keywords,
            t.columns.map(c => new Column(c.name, c.keywords)),
            t.foreignKeys.map(f => new ForeignKey(f.fromColumn, f.toTable, f.toColumn)))
    ));
}

module.exports = TableController;