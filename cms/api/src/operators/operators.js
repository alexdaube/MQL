var exports = module.exports = {};
var Operator = require('./operator.js');
var _ = require('underscore');

var operators = [];

exports.addOperator = function (type, keywords) {
    var operator = _.findWhere(operators, {type: type});

    if (typeof operator == 'undefined') {
        operator = new Operator(type, keywords);
        operators.push(operator);
    } else {
        console.log("Operator exists");
    }
    return operator;
};

exports.getOperators = function () {
    return operators;
};