<template>
    <div>
        <el-container style="height: 100vh;">
            <SideMenu></SideMenu>
            <el-container direction="vertical">
                <TopHeader></TopHeader>
                <el-main>
                    <el-scrollbar>
                        <router-view></router-view>
                    </el-scrollbar>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>

// 引入侧边栏组件
import SideMenu from "../components/mainbox/SideMenu.vue";

// 引入头部组件
import TopHeader from "../components/mainbox/TopHeader.vue";

</script>
