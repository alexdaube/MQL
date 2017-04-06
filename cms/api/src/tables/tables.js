var exports = module.exports = {};
var Table = require('./table.js');
var Column = require('./column.js');
var ForeignKey = require('./foreignKey');
var _ = require('underscore');

var tables = [];

exports.addTable = function (name) {
    //var table = _.findWhere(operators, {name: name});
    if (!tables.find(t => t.getName() === name)) {
        tables.push(new Table(name));
    }
};

exports.removeTable = function (name) {
    const index = tables.indexOf(tables.find(t => t.getName() === name));
    if (index > -1) {
        tables.splice(index, 1);
        tables.forEach(t => {
            const i = t.getForeignKeys().indexOf(t.getForeignKeys().find(f => f.getToTable() === name));
            if (i > -1) {
                t.getForeignKeys().splice(i, 1);
            }
        })
    }
};

exports.addKeyword = function (name, keyword) {
    if (tables.find(t => t.getName() === name)) {
        tables.find(t => t.getName() === name).addKeyword(keyword);
    }
};

exports.removeKeyword = function (name, keyword) {
    if (tables.find(t => t.getName() === name)) {
        tables.find(t => t.getName() === name).removeKeyword(keyword);
    }
};

exports.addColumn = function (tableName, name) {
    //var table = _.findWhere(operators, {name: target_table_name});
    if (tables.find(t => t.getName() === tableName)) {
        tables.find(t => t.getName() === tableName).addColumn(new Column(name));
    }
};


exports.removeColumn = function (tableName, name) {
    //var table = _.findWhere(operators, {name: target_table_name});
    if (tables.find(t => t.getName() === tableName)) {
        tables.find(t => t.getName() === tableName).removeColumn(name);
        tables.forEach(t => {
            const i = t.getForeignKeys().indexOf(t.getForeignKeys().find(f => f.getFromColumn() === name || f.getToColumn() === name));
            if (i > -1) {
                t.getForeignKeys().splice(i, 1);
            }
        })
    }
};

exports.addForeignKey = function (tableName, foreignKey) {
    if (tables.find(t => t.getName() === tableName)) {
        tables.find(t => t.getName() === tableName)
            .addForeignKey(new ForeignKey(foreignKey.fromColumn, foreignKey.toTable, foreignKey.toColumn));
    }
};

exports.removeForeignKey = function (tableName, foreignKey) {
    if (tables.find(t => t.getName() === tableName)) {
        tables.find(t => t.getName() === tableName)
            .removeForeignKey(new ForeignKey(foreignKey.fromColumn, foreignKey.toTable, foreignKey.toColumn));
    }
};

exports.addColumnKeyword = function (tableName, columnName, keyword) {
    if (tables.find(t => t.getName() === tableName)) {
        tables.find(t => t.getName() === tableName).addColumnKeyword(columnName, keyword);
    }
};

exports.removeColumnKeyword = function (tableName, columnName, keyword) {
    if (!tables.find(t => t.getName() === tableName)) {
        tables.find(t => t.getName() === tableName).removeColumnKeyword(columnName, keyword);
    }
};

exports.getTables = function () {
    return tables;
};

exports.getTableFromName = function (tableName) {
    return tables.find(t => t.getName() === tableName);
};