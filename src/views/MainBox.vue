<template>
    <div>Mainbox
        <button @click="handelExit">退出登录</button>
        <router-view></router-view>
    </div>
</template>

<script setup>
import { useUserStore } from '../store/useUserStore.js '
import {useRouterStore} from '../store/useRouterStore'
import {useRouter} from 'vue-router'

const userStore = useUserStore()
const router = useRouter()
const {changeRouter} = useRouterStore()
const { changeUser } = userStore
const handelExit = () => {
    changeUser({})
    // 清除用户信息
    changeRouter(false)

    // 退出登录后跳转到登录页面
    router.push('/login')
}
</script>
