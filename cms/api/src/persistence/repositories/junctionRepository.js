const MongoRepository = require("./mongoRepository");

class JunctionRepository extends MongoRepository{
    getAll(callback) {
        let junctions = [];
        let cursor = db.collection('junctions').find();
        cursor.each(function (err, doc) {
            if (err) throw err;
            if (doc != null) {
                junctions.push(doc);
            } else {
                callback(junctions);
            }
        });
    }

    save(junction, callback) {
        db.collection('junctions').insertOne(junction, function (err, doc) {
            if (err) throw err;
            callback(doc);
        });
    }
    destroy(name, callback) {
        db.collection('junctions').findOneAndDelete({type: name}, function (err, doc) {
            if (err) throw err;
            callback();
        });
    }
    update(junction, callback) {
        db.collection('junctions').update({type: junction.getType()}, {
                $set: {
                    keywords: junction.getKeywords()
                }
            },
            function (err, doc) {
                if (err) throw err;
                callback();
            });
    }
}

module.exports = JunctionRepository;