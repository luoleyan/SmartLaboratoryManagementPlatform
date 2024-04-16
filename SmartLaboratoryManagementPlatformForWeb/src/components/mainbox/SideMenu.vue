<template>
    <el-aside width="200px">
        <el-scrollbar>
            <el-menu :default-active="route.fullPath" class="el-menu-vertical-demo" style="height:100vh;" :router="true">

                <template v-for="data in dataList" :key="data.path">
                    <el-sub-menu :index="data.path" v-if="data.children.length && checkAuth(data.path)">
                        <template #title>
                            <el-icon>
                                <component :is="mapIcons[data.icon]"></component>
                            </el-icon>
                            <span>{{ data.title }}</span>
                        </template>
                        <template v-for="item in data.children" :key="item.path">
                            <el-menu-item :index="item.path" v-if="checkAuth(item.path)">
                                <el-icon>

                                    <component :is="mapIcons[item.icon]"></component>
                                </el-icon>
                                <span>{{ item.title }}</span>
                            </el-menu-item>

                        </template>
                    </el-sub-menu>
                    <el-menu-item :index="data.path" v-else-if="checkAuth(data.path)">
                        <el-icon>
                            <component :is="mapIcons[data.icon]"></component>
                        </el-icon>
                        <span>{{ data.title }}</span>
                    </el-menu-item>
                </template>


            </el-menu>
        </el-scrollbar>
    </el-aside>
</template>

<script setup>
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
    HomeFilled,
    User,
    Key,
    OfficeBuilding,
    UploadFilled,
    List
} from '@element-plus/icons-vue'
import { onMounted, ref } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
import { useUserStore } from '../../store/useUserStore'
import request from '../../util/request';

// 获取路由
const route = useRoute()

// 生命周期
onMounted(() => {
    getList()
})

// 获取列表
const dataList = ref([])
const getList =  () => {
    // 获取列表
    request.get('/adminapi/rights').then(res => {
        // console.log(res);
        
        dataList.value = res
        // console.log(dataList.value);
        
    })
}

// 菜单图标
const mapIcons = {
    HomeFilled,
    User,
    Key,
    OfficeBuilding,
    UploadFilled,
    List
}


const { user } = useUserStore() // 获取用户信息

// 权限控制
const checkAuth = (path) => {
    return user.roles.rights.includes(path)
}
</script>