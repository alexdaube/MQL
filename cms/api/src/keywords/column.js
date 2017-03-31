"use strict";

class Column {
    constructor(name, synonyms) {
        this.synonyms = synonyms;
        this.name = name;
        this.addSynonym(name);
    }

    addSynonym(keyword) {
        if (this.synonyms.indexOf(keyword) == -1) {
            this.synonyms.push(keyword);
        }
    }
}

module.exports = Column;