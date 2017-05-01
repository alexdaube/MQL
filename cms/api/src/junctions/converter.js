const Junctions = require("./junctions");
const Junction = require("./junction");

class JunctionsConverter {
    mapToJunction(dbJunctions) {
        if (dbJunctions) {
            return new Junctions(dbJunctions.map(j => new Junction(j.type, j.keywords)));
        }
        return new Junctions;
    }
}

module.exports = JunctionsConverter;

