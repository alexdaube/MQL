"use strict";

class Table {
    constructor(name, synonyms) {
        this.synonyms = synonyms;
        this.attributes = [];
        this.foreign_keys = [];
        this.name = name;
        this.synonyms.push(name);
    }

    addSynonym(keyword) {
        if (!this.synonyms.contains(keyword)) {
            this.synonyms.push(keyword);
        }
    }

    addColumn(attribute) {
        if (this.attributes.indexOf(attribute) == -1) {
            this.attributes.push(attribute);
        }else{
            console.log("Column already exists");
        }
    }

    addForeignKey(foreign_key) {
        if (!this.foreign_keys.contains(foreign_key)) {
            this.foreign_keys.push(foreign_key);
        }
    }
}

module.exports = Table;