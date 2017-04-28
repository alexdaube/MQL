const MongoRepository = require("./mongoRepository");

class JunctionRepository extends MongoRepository{
    get() {}
    save(junction, callback) {}
    destroy() {}
    update() {}
}

module.exports = JunctionRepository;