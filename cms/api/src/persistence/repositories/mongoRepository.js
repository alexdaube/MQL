class MongoRepository {
    constructor(connection, collectionName) {
        this.connection = connection;
        this.collectionName = collectionName;
    }

    getAll() {
        let collection = [];
        let cursor = db.collection(this.collectionName).find();
        cursor.each(function (err, doc) {
            if (err) {
                return {hasError: true, error: err};
            }
            if (doc != null) {
                collection.push(doc);
            } else {
                return collection;
            }
        });
        return collection;
    }

    save(itemToSave) {
        db.collection(this.collectionName).insertOne(itemToSave, function (err, doc) {
            if (err) {
                return {hasError: true, error: err};
            }
            return doc;
        });
    }

    // Pass either {name: name} or {type: type}
    destroy(itemToDestroy) {
        db.collection('entities').findOneAndDelete(this.collectionName, function (err, doc) {
            if (err) {
                return {hasError: true, error: err};
            }
        });
    }

    // Pass either {name: name} or {type: type}
    update(itemToUpdate, fieldsToUpdate) {
        db.collection(this.collectionName).update(itemToUpdate, {
                $set: fieldsToUpdate
            },
            function (err, doc) {
                if (err) {
                    return {hasError: true, error: err};
                }
            });
    }
}

module.exports = MongoRepository;
