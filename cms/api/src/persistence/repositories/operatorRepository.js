const MongoRepository = require("./mongoRepository");

class OperatorRepository extends MongoRepository{
    getAll(callback) {
        let operators = [];
        let cursor = db.collection('operators').find();
        cursor.each(function (err, doc) {
            if (err) throw err;
            if (doc != null) {
                operators.push(doc);
            } else {
                callback(operators);
            }
        });
    }
    save(operator, callback) {
        db.collection('operators').insertOne(operator, function (err, doc) {
            if (err) throw err;
            callback(doc);
        });
    }
    destroy(name, callback) {
        db.collection('operators').findOneAndDelete({type: name}, function (err, doc) {
            if (err) throw err;
            callback();
        });
    }
    update(operator, callback) {
        db.collection('operators').update({type: operator.getType()}, {
                $set: {
                    keywords: operator.getKeywords()
                }
            },
            function (err, doc) {
                if (err) throw err;
                callback();
            });
    }
}

module.exports = OperatorRepository;
