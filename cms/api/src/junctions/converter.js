const Junctions = require("./junctions");
const Junction = require("./junction");

class JunctionsConverter {
    mapToJunction(dbJunctions) {
        return new Junctions(dbJunctions.map(j => new Junction(j.type, j.keywords)));
    }
}

module.exports = JunctionsConverter;

