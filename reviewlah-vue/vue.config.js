const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    lintOnSave:false,
    transpileDependencies: true,
    devServer: {
        port: 8182,
        // proxy: {
        //     '/api': {
        //         target: "http://localhost:8080/",
        //         changeOrigin: true,
        //         pathRewrite: {
        //             '^/api': ''
        //         }
        //     }
        // }
    },
})
