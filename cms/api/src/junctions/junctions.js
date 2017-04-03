var exports = module.exports = {};
var Junction = require('./junction.js');
var _ = require('underscore');

var junctions = [];

exports.addJunction = function (type) {
    if (!junctions.find(j => j.getType() === type)) {
        junctions.push(new Junction(type));
    }
};

exports.addKeyword = function(type, keyword) {
    junctions.find(j => j.getType() === type).addKeyword(keyword);
};

exports.remove = function (type) {
    const index = junctions.indexOf(junctions.find(j => j.getType() === type));
    if (index > -1) {
        junctions.splice(index, 1);
    }
};

exports.removeKeyword = function (type, keyword) {
    junctions.find(j => j.getType() === type).removeKeyword(keyword);
};

exports.getJunctions = function () {
    return junctions;
};