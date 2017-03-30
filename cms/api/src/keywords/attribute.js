"use strict";

class Attribute {
    constructor(name, synonyms) {
        this.synonyms = synonyms;
        this.name = name;
        this.synonyms.push(name);
    }

    addSynonym(keyword) {
        if (!this.synonyms.contains(keyword)) {
            this.synonyms.push(keyword);
        }
    }


}

module.exports = Attribute;