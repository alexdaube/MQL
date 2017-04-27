const operators = require("../operators/operators");

"use strict";
class OperatorController {

    constructor(connection) {
        this.connection = connection;
    }

    getAll(req, res) {

    }

    addOperator(req, res) {
        const type = req.body.type;
        operators.addOperator(type);
        let operator = operators.getOperatorFromType(type);
        this.connection.save(operator, function () {
            res.sendStatus(200);
        });
    }

    removeOperator(req, res) {
        const type = req.params.type;
        operators.remove(type);
        let operator = operators.getOperatorFromType(type);
        this.connection.destroy(operator, function () {
            res.sendStatus(200);
        });
    }

    addKeyword(req, res) {
        operators.addKeyword(req.params.type, req.body.keyword);
        let operator = operators.getOperatorFromType(req.params.type);
        this.connection.update(operator, function () {
            res.sendStatus(200);
        });
    };

    removeKeyword(req, res) {
        operators.removeKeyword(req.params.type, req.params.keyword);
        let operator = operators.getOperatorFromType(req.params.type);
        this.connection.update(operator, function () {
            res.sendStatus(200);
        });
    };
}

module.exports = OperatorController;