var exports = module.exports = {};
var Junction = require('./junction.js');
var _ = require('underscore');

var junctions = [];

exports.addJunction = function (type, keywords) {
    var junction = _.findWhere(junctions, {type: type});

    if (typeof junction == 'undefined') {
        junction = new Junction(type, keywords);
        junctions.push(junction);
    } else {
        console.log("Junction exists");
    }
    return junction;
};

exports.getJunctions = function () {
    return junctions;
};