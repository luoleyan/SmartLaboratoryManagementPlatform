<template>
    <div>
        login
        <button @click="handleLogin1">login-admin</button>
        <button @click="handleLogin2">login-teacher</button>
        </div>
</template>

<script setup>
import {useUserStore} from '../store/useRouterStore'
import { useRouter } from 'vue-router'

    const userStore = useUserStore()
    const router = useRouter()
    const handleLogin1 = () => {
        
    }

    const handleLogin2 = () => {

    }
</script>