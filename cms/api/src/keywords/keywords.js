var exports = module.exports = {};
var Table = require('./table.js');
var Attribute = require('./column.js');
var _ = require('underscore');

var entities = [];

exports.addEntity = function (value, synonyms) {
    var entity = new Table(value, synonyms);
    entities.push(entity);
};

exports.addAttribute = function (entity_name, value, synonyms) {
    var attribute = new Attribute(value, synonyms);
    var entity = _.findWhere(entities, {name: entity_name});

    if (typeof entity !== "undefined") {
        entity.addColumn(attribute);
    }
};


exports.getEntities = function () {
    return entities;
};