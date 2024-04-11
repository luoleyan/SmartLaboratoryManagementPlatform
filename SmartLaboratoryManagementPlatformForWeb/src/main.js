import { createApp } from 'vue'
import App from './App.vue'
// 引入路由
import router from './router'
// 引入pinia
import {createPinia} from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
// 引入element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import './assets/css/global.css'
// 引入粒子效果
import Particles from "@tsparticles/vue3";
import { loadFull } from "tsparticles";


const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
createApp(App)
.use(ElementPlus, {
    locale: zhCn,
})
.use(pinia)
.use(router)
.use(Particles, {
    init: async engine => {
        await loadFull(engine); // you can load the full tsParticles library from "tsparticles" if you need it
    },
})
.mount('#app')

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
