var exports = module.exports = {};
let Table = require('./table.js');
let Attribute = require('./attribute.js');

var entities = [];

exports.addEntity = function (value, synonyms) {
    console.log(value);
    var entity = new Table(value, synonyms);
    entities.push(entity);

};

exports.addAttribute = function (entity_name, value, synonyms) {
    var attribute = new Attribute(value, synonyms);
    // var entity = _.findWhere(entities, {name: entity_name});
};


exports.getEntities = function () {
    return entities;
};