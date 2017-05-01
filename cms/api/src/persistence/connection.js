class Connection {
    constructor(dbConnection) {
        this.db = dbConnection;
    }

    open() {
        this.db.open((err, db) => {
            if (!err) {
                console.log("Connected to db");
            }
        });
    }
}

module.exports = Connection;