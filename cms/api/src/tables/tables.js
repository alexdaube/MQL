var exports = module.exports = {};
var Table = require('./table.js');
var Column = require('./column.js');
var ForeignKey = require('./foreignKey');
const connection = require('../common/connection');
var _ = require('underscore');

class Tables {
    constructor(tables = []) {
        this.tables = tables;
    }

    addTable(name) {
        if (!this.tables.find(t => t.getName() === name)) {
            this.tables.push(new Table(name));
        }
    }

    removeTable(name) {
        const index = this.tables.indexOf(this.tables.find(t => t.getName() === name));
        if (index > -1) {
            this.tables.splice(index, 1);
            this.tables.forEach(t => {
                const i = t.getForeignKeys().indexOf(t.getForeignKeys().find(f => f.getToTable() === name));
                if (i > -1) {
                    t.getForeignKeys().splice(i, 1);
                }
            });
        }
    }

    addKeyword(name, keyword) {
        if (this.tables.find(t => t.getName() === name)) {
            this.tables.find(t => t.getName() === name).addKeyword(keyword);
        }
    }

    removeKeyword(name, keyword) {
        if (this.tables.find(t => t.getName() === name)) {
            this.tables.find(t => t.getName() === name).removeKeyword(keyword);
        }
    }

    addColumn(tableName, name) {
        //var table = _.findWhere(operators, {name: target_table_name});
        if (this.tables.find(t => t.getName() === tableName)) {
            this.tables.find(t => t.getName() === tableName).addColumn(new Column(name));
        }
    }


    removeColumn(tableName, name) {
        //var table = _.findWhere(operators, {name: target_table_name});
        if (this.tables.find(t => t.getName() === tableName)) {
            this.tables.find(t => t.getName() === tableName).removeColumn(name);
            this.tables.forEach(t => {
                const i = t.getForeignKeys().indexOf(t.getForeignKeys().find(f => f.getFromColumn() === name || f.getToColumn() === name));
                if (i > -1) {
                    t.getForeignKeys().splice(i, 1);
                }
            });
        }
    }

    addForeignKey(tableName, foreignKey) {
        if (this.tables.find(t => t.getName() === tableName)) {
            this.tables.find(t => t.getName() === tableName)
        .addForeignKey(new ForeignKey(foreignKey.fromColumn, foreignKey.toTable, foreignKey.toColumn));
        }
    }

    removeForeignKey(tableName, foreignKey) {
        if (this.tables.find(t => t.getName() === tableName)) {
            this.tables.find(t => t.getName() === tableName)
        .removeForeignKey(new ForeignKey(foreignKey.fromColumn, foreignKey.toTable, foreignKey.toColumn));
        }
    }

    addColumnKeyword(tableName, columnName, keyword) {
        if (this.tables.find(t => t.getName() === tableName)) {
            this.tables.find(t => t.getName() === tableName).addColumnKeyword(columnName, keyword);
        }
    }

    removeColumnKeyword(tableName, columnName, keyword) {
        if (this.tables.find(t => t.getName() === tableName)) {
            this.tables.find(t => t.getName() === tableName).removeColumnKeyword(columnName, keyword);
        }
    }

    getTables() {
        return this.tables;
    }

    getTableFromName(tableName) {
        return this.tables.find(t => t.getName() === tableName);
    }
}

module.exports = Tables;
