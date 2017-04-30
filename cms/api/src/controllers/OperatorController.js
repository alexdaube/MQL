const Operators = require("../operators/operators");

"use strict";

class OperatorController {

    constructor(repository, converter) {
        this.repository = repository;
        this.converter = converter;
    }

    getAll(req, res) {
        const operators = this.getCurrentOperators();
        if (operators instanceof Operators) {
            return res.send(operators);
        }
        res.sendStatus(404);
    }


    addOperator(req, res) {
        const operators = this.getCurrentOperators();
        if (operators instanceof Operators) {
            const type = req.body.type;
            operators.addOperator(type);
            const operator = operators.getOperatorFromType(type);
            const doc = this.repository.save(operator);
            if (!doc.hasError) {
                return res.sendStatus(200);
            }
        }
        res.sendStatus(404);
    }

    removeOperator(req, res) {
        const operators = this.getCurrentOperators();
        if (operators instanceof Operators) {
            const type = req.params.type;
            operators.remove(type);
            const destroy = this.repository.destroy({type: type});
            if (!destroy.hasError) {
                res.sendStatus(200);
            }
        }
        res.sendStatus(404);
    }

    addKeyword(req, res) {
        const operators = this.getCurrentOperators();
        if (operators instanceof Operators) {
            const type = req.params.type;
            const keyword = req.body.keyword;
            operators.addKeyword(type, keyword);
            return this.updateOperator(operators, type)
        }
        res.sendStatus(404);
    }

    removeKeyword(req, res) {
        const operators = this.getCurrentOperators();
        if (operators instanceof Operators) {
            const type = req.params.type;
            const keyword = req.params.keyword;
            operators.removeKeyword(type, keyword);
            return this.updateOperator(operators, type)
        }
        res.sendStatus(404);
    }

    updateOperator(operators, type) {
        const operator = operators.getOperatorFromType(type);
        const typeObj = {type: operator.getType()};
        const keywords = {keywords: operator.getKeywords()};
        const update = this.repository.update(typeObj, keywords);
        if (!update.hasError) {
            return res.sendStatus(200);
        }
        return res.sendStatus(404);
    }

    getCurrentOperators() {
        const fetchedOperators = this.repository.getAll();
        return this.converter.mapToOperator(fetchedOperators);
    }
}

module.exports = OperatorController;
