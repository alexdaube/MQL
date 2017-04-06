const operators = require("../operators/operators");

"use strict";
class OperatorController {

    constructor(connection) {
        this.connection = connection;
    }

    addOperator(type, res) {
        operators.addOperator(type);
        let operator = operators.getOperatorFromType(type);
        this.connection.saveOperator(operator, function () {
            res.sendStatus(200);
        });
    }

    removeOperator(type, res) {
        operators.remove(type);
        let operator = operators.getOperatorFromType(type);
        this.connection.deleteOperator(operator, function () {
            res.sendStatus(200);
        });
    }

    addKeyword(req, res) {
        operators.addKeyword(req.params.type, req.body.keyword);
        let operator = operators.getOperatorFromType(req.params.type);
        this.connection.updateOperator(operator, function () {
            res.sendStatus(200);
        });
    };

    removeKeyword(req, res) {
        operators.removeKeyword(req.params.type, req.params.keyword);
        let operator = operators.getOperatorFromType(req.params.type);
        this.connection.updateOperator(operator, function () {
            res.sendStatus(200);
        });
    };
}

module.exports = OperatorController;