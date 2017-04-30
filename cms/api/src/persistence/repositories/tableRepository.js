const MongoRepository = require("./mongoRepository");

class TableRepository extends MongoRepository {
    getAll(callback) {
        var tables = [];
        var cursor = db.collection('entities').find();
        cursor.each(function (err, doc) {
            if (err) throw err;
            if (doc != null) {
                tables.push(doc);
            } else {
                callback(tables);
            }
        });
    }
    save(table, callback) {
        db.collection('entities').insertOne(table, function (err, doc) {
            if (err) throw err;
            callback(doc);
        });
    }
    destroy(name, callback) {
        db.collection('entities').findOneAndDelete({name: name}, function (err, doc) {
            if (err) throw err;
            callback();
        });
    }
    update(table, callback) {
        db.collection('entities').update({name: table.getName()}, {
                $set: {
                    keywords: table.getKeywords(),
                    columns: table.getColumns(),
                    foreignKeys: table.getForeignKeys()
                }
            },
            function (err, doc) {
                if (err) throw err;
                callback();
            });
    }
}

module.exports = TableRepository;
