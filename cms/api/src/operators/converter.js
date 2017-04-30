const Operators = require("./operators");
const Operator = require("./operator");

class OperatorsConverter {
    mapToOperator(dbOperators) {
        return new Operators(dbOperators.map(o => new Operator(o.type, o.keywords)));
    }
}

module.exports = OperatorsConverter;
