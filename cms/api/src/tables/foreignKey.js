"use strict";

class ForeignKey {
    constructor(fromColumn, toTable, toColumn) {
        this.fromColumn = fromColumn;
        this.toTable = toTable;
        this.toColumn = toColumn;
    }
    
    isEqualTo(foreignKey) {
        return this.fromColumn === foreignKey.getFromColumn() &&
            this.toTable === foreignKey.getToTable() &&
            this.toColumn === foreignKey.getToColumn();
    }

    getFromColumn() {
        return this.fromColumn;
    }

    getToTable() {
        return this.toTable;
    }

    getToColumn() {
        return this.toColumn;
    }
}

module.exports = ForeignKey;