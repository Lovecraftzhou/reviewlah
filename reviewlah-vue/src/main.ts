/* eslint-disable @typescript-eslint/no-unused-vars */
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import CooieTool from './assets/cookie'

const app = createApp(App)
app.config.globalProperties.$CooieTool = CooieTool;// cookie处理
app.use(router).use(ElementPlus).provide('$axios', axios).mount('#app')