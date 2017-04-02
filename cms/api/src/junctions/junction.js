"use strict";

class Junction {
    constructor(type, keywords) {
        this.keywords = keywords;
        this.type = type;
        this.addKeyword(type);
    }

    addKeyword(keyword) {
        if (this.keywords.indexOf(keyword) == -1) {
            this.keywords.push(keyword);
        }
    }
}

module.exports = Junction;