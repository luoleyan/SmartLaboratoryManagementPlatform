import {defineStore} from 'pinia'
import {ref} from 'vue'

export const useRouterStore = defineStore('router', () =>{
    const isGetterRouter = ref(false) // 是否是获取路由

    const changeRouter = (value) =>{
        isGetterRouter.value = value
    }

    return {
        isGetterRouter,
        changeRouter
    }
})