module.exports = {
    entry: './src/Index.tsx',
    output: {
        filename: './dist/js/bundle.js'
    },
    resolve: {
        extensions: ['', '.webpack.js', '.web.js', '.ts', '.tsx', '.js']
    },
    module: {
        loaders: [
            {test: /\.tsx?$/, loader: 'ts-loader'}
        ]
    }
};