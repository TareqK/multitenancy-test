const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
module.exports = (env) => {
    let copyConfiguration = [
        {from: './src/WEB-INF', to: 'WEB-INF'},
        {from: './src/META-INF', to: 'META-INF'}
    ];
    if (!env.production) {
        copyConfiguration.push({from: './src/swagger-ui', to: 'swagger-ui'});
        copyConfiguration.push({from: './src/swagger.html', to: 'swagger.html'});
    }
    return{
        mode: env.production ? 'production' : 'development',
        entry: './src/app.js',
        output: {
            filename: 'app.js'
        },
        plugins: [new HtmlWebpackPlugin({
                title: 'Hello World'
            }),
            new CopyWebpackPlugin(copyConfiguration),
            new CleanWebpackPlugin()
        ],
        optimization: {
            minimize: env.production
        },
        watchOptions: {
            aggregateTimeout: 50,
            poll: 100
        },
        module: {
            rules: [
                {
                    test: /\.css$/,
                    use: ['style-loader', 'css-loader']
                }
            ]
        }
    };
};

