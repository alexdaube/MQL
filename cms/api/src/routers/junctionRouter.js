module.exports = function (app, controller) {
    // app.get('/junctions', (req, res) => {
    //     connection.getExistingJunctions(function (result) {
    //         res.send(result);
    //     });
    // });


    app.get('/junctions', controller.getAll);

    app.post('/junctions', controller.addJunction);

    app.delete('/junctions/:type', controller.removeJunction);

    app.post('/junctions/:type/keywords', controller.addKeyword);

    app.delete('/junctions/:type/keywords/:keyword', controller.removeKeyword);
};

