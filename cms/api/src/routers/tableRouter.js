module.exports = function (app, controller) {
    // app.get('/tables', (req, res) => {
    //     connection.getExistingTables(function (result) {
    //         res.send(result);
    //     });
    // });

    app.get('/tables', controller.getAll);

    app.post('/tables', controller.addTable);

    app.delete('/tables/:name', controller.removeTable);

    app.post('/tables/:name/columns', controller.addColumn);

    app.delete('/tables/:name/columns/:column', controller.removeColumn);

    app.post('/tables/:name/keywords', controller.addKeyword);

    app.delete('/tables/:name/keywords/:keyword', controller.removeKeyword);

    app.post('/tables/:name/foreign_keys', controller.addForeignKey);

    app.post('/tables/:name/foreign_keys/remove', controller.removeForeignKey);

    app.post('/tables/:name/columns/:column/keywords', controller.addColumnKeyword);

    app.delete('/tables/:name/columns/:column/keywords/:keyword', controller.removeColumnKeyword);
};

