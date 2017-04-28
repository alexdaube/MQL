const Operators = require("../operators/operators");
const Operator = require("../operators/operator");

"use strict";
class OperatorController {

    constructor(connection) {
        this.connection = connection;
    }

    addOperator(type, res) {
        this.connection.getExistingOperators((dbOperators) => {
            let operators = mapToOperator(dbOperators);
            operators.addOperator(type);
            let operator = operators.getOperatorFromType(type);
            this.connection.saveOperator(operator, function () {
                res.sendStatus(200);
            });
        });
    }

    removeOperator(type, res) {
        this.connection.getExistingOperators((dbOperators) => {
            let operators = mapToOperator(dbOperators);
            operators.remove(type);
            this.connection.deleteOperator(type, function () {
                res.sendStatus(200);
            });
        });
    }

    addKeyword(req, res) {
        this.connection.getExistingOperators((dbOperators) => {
            let operators = mapToOperator(dbOperators);
            operators.addKeyword(req.params.type, req.body.keyword);
            let operator = operators.getOperatorFromType(req.params.type);
            this.connection.updateOperator(operator, function () {
                res.sendStatus(200);
            });
        });
    };

    removeKeyword(req, res) {
        this.connection.getExistingOperators((dbOperators) => {
            let operators = mapToOperator(dbOperators);
            let operator = operators.getOperatorFromType(req.params.type);
            operators.removeKeyword(req.params.type, req.params.keyword);
            this.connection.updateOperator(operator, function () {
                res.sendStatus(200);
            });
        });
    };
}

function mapToOperator(dbOperators) {
    return new Operators(dbOperators.map(o => new Operator(o.type, o.keywords)));
}

module.exports = OperatorController;