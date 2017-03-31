var exports = module.exports = {};
var Table = require('./table.js');
var Column = require('./column.js');
var _ = require('underscore');

var tables = [];

exports.addEntity = function (name, synonyms) {
    var table = new Table(name, synonyms);
    tables.push(table);
};

exports.addAttribute = function (entity_name, name, synonyms) {
    var column = new Column(name, synonyms);
    var table = _.findWhere(tables, {name: entity_name});

    if (typeof table !== "undefined") {
        table.addColumn(column);
    }
};


exports.getEntities = function () {
    var keywords = {entities: tables};
    return keywords;
};