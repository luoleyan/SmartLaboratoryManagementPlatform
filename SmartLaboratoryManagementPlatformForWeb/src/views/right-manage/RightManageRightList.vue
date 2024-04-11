<template>
    <div>
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="title" label="权限名称" width="180" />
            <el-table-column label="图标" width="180">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <el-icon size="22px">
                            <component :is="mapIcons[scope.row.icon]"></component>
                        </el-icon>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button  round type="warning">更新</el-button>
                    <el-button  type="danger" round>删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
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
import { onMounted, ref } from 'vue'
import axios from 'axios'

// 菜单图标
const mapIcons = {
    HomeFilled,
    User,
    Key,
    OfficeBuilding,
    UploadFilled,
    List
}

// 权限列表
const tableData = ref([])

onMounted(() => {
    getList()
})

const getList = async () => {
    await axios.get('/adminapi/rights')
    tableData.value = res.data
}


</script>
