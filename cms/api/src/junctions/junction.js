"use strict";

class Junction {
    constructor(type, keywords = []) {
        this.keywords = keywords;
        this.type = type;
    }

    addKeyword(keyword) {
        if (this.keywords.indexOf(keyword) == -1) {
            this.keywords.push(keyword);
        }
    }

    removeKeyword(keyword) {
        const index = this.keywords.indexOf(keyword);
        if (index > -1) {
            this.keywords.splice(index, 1);
        }
    }

    getType() {
        return this.type;
    }

    getKeywords() {
        return this.keywords;
    }
}

module.exports = Junction;