module.exports = function (app, controller) {
    // app.get('/operators', (req, res) => {
    //     connection.getExistingOperators(function (result) {
    //         res.send(result);
    //     });
    // });

    app.get('/operators', controller.getAll);

    app.post('/operators', controller.addOperator);

    app.delete('/operators/:type', controller.removeOperator);

    app.post('/operators/:type/keywords', controller.addKeyword);

    app.delete('/operators/:type/keywords/:keyword', controller.removeKeyword);
};

