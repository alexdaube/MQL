var exports = module.exports = {};
var Operator = require('./operator.js');
var _ = require('underscore');

class Operators {
    constructor(operators = []) {
        this.operators = operators;
    }

    addOperator(type) {
        if (!this.operators.find(o => o.getType() === type)) {
            this.operators.push(new Operator(type));
        }
    }

    addKeyword(type, keyword) {
        this.operators.find(o => o.getType() === type).addKeyword(keyword);
    }

    remove(type) {
        const index = this.operators.indexOf(this.operators.find(o => o.getType() === type));
        if (index > -1) {
            this.operators.splice(index, 1);
        }
    }

    removeKeyword(type, keyword) {
        this.operators.find(o => o.getType() === type).removeKeyword(keyword);
    }

    getOperators() {
        return this.operators;
    }
    
    getOperatorFromType(type) {
        return this.operators.find(o => o.getType() === type);
    }
}

module.exports = Operators;