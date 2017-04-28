const Junction = require('./junction.js');
const _ = require('underscore');

class Junctions {
    constructor() {
        this.junctions = [];
    }

    addJunction(type) {
        if (!this.junctions.find(j => j.getType() === type)) {
            this.junctions.push(new Junction(type));
        }
    }

    addKeyword(type, keyword) {
        this.junctions.find(j => j.getType() === type).addKeyword(keyword);
    }

    remove(type) {
        const index = this.junctions.indexOf(this.junctions.find(j => j.getType() === type));
        if (index > -1) {
            this.junctions.splice(index, 1);
        }
    }

    removeKeyword(type, keyword) {
        this.junctions.find(j => j.getType() === type).removeKeyword(keyword);
    }

    getJunctions() {
        return this.junctions;
    }

    getJunctionFromType(type) {
        return this.junctions.find(j => j.getType() === type);
    }
}

module.exports = Junctions;