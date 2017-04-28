var _ = require('underscore');
var Column = require('./column');
"use strict";

class Table {

    constructor(name, keywords = [], columns = [], foreignKeys = []) {
        this.keywords = keywords;
        this.columns = columns;
        this.foreignKeys = foreignKeys;
        this.name = name;
    }

    addKeyword(keyword) {
        if (this.keywords.indexOf(keyword) == -1)
            this.keywords = this.keywords.concat(keyword);
    }

    removeKeyword(keyword) {
        this.keywords = this.keywords.filter(k => k !== keyword);
    }

    addColumn(column) {
        //var col = _.findWhere(this.columns, {name: column.name});
        if (!this.columns.find(c => c.getName() === column.getName())) {
            this.columns.push(column);
        }
    }

    removeColumn(colummName) {
        //var column = _.findWhere(this.columns, {name: colummName});
        this.columns = this.columns.filter(c => c.getName() !== colummName);
        this.foreignKeys = this.foreignKeys.filter(f => f.getFromColumn() !== colummName && f.getToColumn() !== colummName);
    }

    addForeignKey(foreignKey) {
        if (!this.foreignKeys.find(f => f.isEqualTo(foreignKey))) {
            this.foreignKeys = this.foreignKeys.concat(foreignKey)
        }
    }

    removeForeignKey(foreignKey) {
        this.foreignKeys = this.foreignKeys.filter(f => !f.isEqualTo(foreignKey));
        console.log(foreignKey);
    }

    addColumnKeyword(columnName, keyword) {
        this.columns = this.columns.map(c => {
            if (c.getName() === columnName) {
                c.addKeyword(keyword);
            }
            return c;
        });
    }

    removeColumnKeyword(columnName, keyword) {
        console.log(columnName, keyword);
        this.columns = this.columns.map(c => {
            if (c.getName() === columnName) {
                return new Column(columnName, c.getKeywords().filter(k => k !== keyword));
            }
            return c;
        });
    }

    getName() {
        return this.name;
    }

    getForeignKeys() {
        return this.foreignKeys;
    }

    getColumns() {
        return this.columns;
    }

    getKeywords() {
        return this.keywords;
    }
}

module.exports = Table;