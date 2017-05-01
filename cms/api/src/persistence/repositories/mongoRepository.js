class MongoRepository {
    constructor(db, collectionName) {
        this.connection = db;
        this.collectionName = collectionName;
        this.getAll = this.getAll.bind(this);
        this.save = this.save.bind(this);
        this.destroy = this.destroy.bind(this);
        this.update = this.update.bind(this);
    }

    getAll() {
        let collection = [];
        let cursor = this.connection.collection(this.collectionName).find();
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
    }

    save(itemToSave) {
        this.connection.collection(this.collectionName).insertOne(itemToSave, function (err, doc) {
            if (err) {
                return {hasError: true, error: err};
            }
            return doc;
        });
    }

    // Pass either {name: name} or {type: type}
    destroy(itemToDestroy) {
        this.connection.collection('entities').findOneAndDelete(this.collectionName, function (err, doc) {
            if (err) {
                return {hasError: true, error: err};
            }
        });
    }

    // Pass either {name: name} or {type: type}
    update(itemToUpdate, fieldsToUpdate) {
        this.connection.collection(this.collectionName).update(itemToUpdate, {
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
