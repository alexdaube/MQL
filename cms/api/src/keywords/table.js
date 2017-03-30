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

    addAttribute(attribute) {
        if (!this.attributes.contains(attribute)) {
            this.attributes.push(attribute);
        }
    }

    addForeignKey(foreign_key) {
        if (!this.foreign_keys.contains(foreign_key)) {
            this.foreign_keys.push(foreign_key);
        }
    }
}

module.exports = Table;