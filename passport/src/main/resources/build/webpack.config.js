/*!
 * is-number <https://github.com/jonschlinkert/is-number>
 *
 * Copyright (c) 2014-2015, Jon Schlinkert.
 * Licensed under the MIT License.
 */

'use strict';

const path = require("path")
const webpack = require("webpack")
const isDev = process.env.NODE_ENV === "development"

const config = {
    target: "web",
    entry: {
      signin: path.join(__dirname, "src/signin/index.js"),
      signup: path.join(__dirname, "src/signup/index.js"),
      jquery: "jquery",
      bootstrap: "bootstrap"
    },
    output: {
      filename: "[name].js",
    },
    exclude: [
      {
        test: /jquery/,
        exclude: [
          resolve('node_modules/XXXXX/bootstrap.min.css')
        ]
      }
    ],
    module: {
      rules: [
        {
          test: /\.css$/,
          use: [
            "style-loader",
            {
              loader: "css-loader",
              options: {
                importLoaders: 1
              }
            }
          ]
        },
        {
          test: /\.styl(us)?$/,
          use: [
            "style-loader",
            "css-loader",
            {
              loader: "postcss-loader",
              options: {
                sourceMap: true
              }
            },
            "stylus-loader",
          ]
        },
        {
          test: /\.(jpg|jpeg|svg|png|bmp|gif|ico)$/,
          use: [
            {
              loader: "url-loader",
              options: {
                limit: 1024,
                name: "[name].[ext]"
              }
            }
          ],
        },
        {
          test: /\.jsx$/,
          loader: "babel-loader"
        }
      ]
    },
    plugins: [
      new webpack.DefinePlugin({
        "process.env": {
          NODE_ENV: isDev ? "'development'" : "'production'"
        }
      }),
      new webpack.ProvidePlugin({
        $: "jquery",
        jQuery: "jquery",
        "window.jQuery": "jquery"
      })
    ],
  }
  
  if (isDev) {
    config.devtool = "#cheap-module-eval-source-map",
    config.devServer = {
      port: 9000,
      host: "0.0.0.0",
      overlay: {
        errors: true
      },
      hot: true
    }
    config.push(
      new webpack.HotModuleReplacementPlugin(),
      new webpack.NoEmitOnErrorsPlugin()
    )
  }
  
  module.exports = config