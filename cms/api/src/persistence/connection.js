class Connection {
    constructor(dbConnection) {
        this.db = dbConnection;
        this.open = this.open.bind(this);
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