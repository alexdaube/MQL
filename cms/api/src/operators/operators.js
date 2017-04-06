var exports = module.exports = {};
var Operator = require('./operator.js');
var _ = require('underscore');

var operators = [];

exports.addOperator = function (type) {
    if (!operators.find(o => o.getType() === type)) {
        operators.push(new Operator(type));
    }
};

exports.addKeyword = function (type, keyword) {
    operators.find(o => o.getType() === type).addKeyword(keyword);
};

exports.remove = function (type) {
    const index = operators.indexOf(operators.find(o => o.getType() === type));
    if (index > -1) {
        operators.splice(index, 1);
    }
};

exports.removeKeyword = function (type, keyword) {
    operators.find(o => o.getType() === type).removeKeyword(keyword);
};

exports.getOperators = function () {
    return operators;
};

exports.getOperatorFromType = function (type) {
    return operators.find(o => o.getType() === type);
};