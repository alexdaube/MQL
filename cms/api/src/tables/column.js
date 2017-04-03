"use strict";

class Column {

    constructor(name, keywords = []) {
        this.keywords = keywords;
        this.name = name;
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

    getName() {
        return this.name;
    }

    getKeywords() {
        return this.keywords;
    }
}

module.exports = Column;