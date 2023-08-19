import { createApp } from 'vue'
// 从一个单文件组件中导入根组件
import App from './App.vue'

import axios from 'axios'

// import MathJax from "mathjax-vue3";
// import { initMathJax, renderByMathjax } from "mathjax-vue3";

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// initMathJax({ url: 'http://localhost:8081/mj/get' }, () => {
//     const el = document.getElementsByClassName("markdown-body");
//     renderByMathjax(el);
// });

const app = createApp(App)

app.config.globalProperties.$axios = axios


// app.use(MathJax)

app.use(ElementPlus)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.mount('#app')


