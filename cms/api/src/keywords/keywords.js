var exports = module.exports = {};
var Table = require('./table.js');
var Column = require('./column.js');
var _ = require('underscore');

var tables = [];

exports.addEntity = function (name, synonyms) {
    var table = _.findWhere(tables, {name: name});

    if (typeof table == 'undefined') {
        var table = new Table(name, synonyms);
        tables.push(table);
    } else {
        console.log("Table exists");
    }
    return table;
};

exports.addAttribute = function (target_table_name, name, synonyms) {
    var table = _.findWhere(tables, {name: target_table_name});

    if (typeof table !== "undefined") {
        var column = new Column(name, synonyms);
        debugger;
        table.addColumn(column);
    }
    return table;
};


exports.removeAttribute = function (target_table_name, name) {
    var table = _.findWhere(tables, {name: target_table_name});

    if (typeof table !== "undefined") {
        table.removeColumn(name);
    }
    return table;
};

exports.getEntities = function () {
    return tables;
};