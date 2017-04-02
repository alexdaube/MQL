var _ = require('underscore');
"use strict";

class Table {
    constructor(name, synonyms) {
        this.synonyms = synonyms;
        this.columns = [];
        this.foreign_keys = [];
        this.name = name;
        this.addSynonym(name);
    }

    addSynonym(keyword) {
        if (this.synonyms.indexOf(keyword) == -1)
            this.synonyms.push(keyword);
    }

    addColumn(column) {
        var col = _.findWhere(this.columns, {name: column.name});

        if (typeof col == "undefined") {
            this.columns.push(column);
        } else {
            console.log("Columns exists");
        }
    }

    removeColumn(columm_name) {
        var column = _.findWhere(this.columns, {name: columm_name});

        if (typeof column == "undefined") {
            console.log("Cant delete undefined column");
        } else {
            debugger;
            var index = this.columns.indexOf(column);
            if (index > -1) {
                this.columns.splice(index, 1);
                console.log("Column removed");
            }
        }
    }

    addForeignKey(foreign_key) {
        if (this.foreign_keys.indexOf(foreign_key) == -1) {
            this.foreign_keys.push(foreign_key);
        }
    }
}

module.exports = Table;