import {defineStore} from 'pinia'
import {ref} from 'vue'

export const useUserStore = defineStore('user', () =>{
    const user = ref({}) // 是否是获取路由

    const changeRouter = (value) =>{
        isGetterRouter.value = value
    }

    return {
        user,
        changeRouter
    }
}, {
    persist: true
}
// 持久化
)