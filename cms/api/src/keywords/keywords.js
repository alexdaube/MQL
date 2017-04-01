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
};

exports.addAttribute = function (entity_name, name, synonyms) {
    var table = _.findWhere(tables, {name: entity_name});

    if (typeof table !== "undefined") {
        var column = new Column(name, synonyms);
        table.addColumn(column);
    }
};


exports.getEntities = function () {
    return tables;
};