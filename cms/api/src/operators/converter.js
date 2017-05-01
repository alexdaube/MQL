const Operators = require("./operators");
const Operator = require("./operator");

class OperatorsConverter {
    mapToOperator(dbOperators) {
        if (dbOperators) {
            return new Operators(dbOperators.map(o => new Operator(o.type, o.keywords)));

        }
        return new Operators;
    }
}

module.exports = OperatorsConverter;
