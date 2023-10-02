const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

//spring boot run on 8080, proxying requests with the '/api' to a Spring Boot application running on 8080
// vue.config.js
module.exports = {
  // https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true
      }
    }
  }
}