<template>
    <div>
        <el-table :data="tableData" style="width: 100%" row-key="path">
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
                    <el-button round type="warning" @click="handleUpdate(scope.row)">更新</el-button>
                    <el-popconfirm title="你确定?" @confirm="handleDelete(scope.row)" confirm-button-text="确定" cancel-button-text="取消">
                        <template #reference>
                            <el-button type="danger" round>删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog v-model="dialogVisible" title="更新权限" width="500">

            <el-form ref="updateFormRef" style="max-width: 600px" :model="updateForm" :rules="rules" label-width="auto"
                class="demo-ruleForm" :size="formSize" status-icon>
                <el-form-item label="权限名称" prop="title">
                    <el-input v-model="updateForm.title" placeholder="title" />
                </el-form-item>
            </el-form>

            <template #footer>
                <div class="dialog-footer">
                            <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleConfirm()">
                        更新
                    </el-button>
                </div>
            </template>
        </el-dialog>
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
import { onMounted, reactive, ref } from 'vue'
import axios from 'axios'
import request from '../../util/request';

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

const getList =  () => {
    request.get('/adminapi/rights').then(res => {
        tableData.value = res
    })
}

// 更新权限表单
const updateFormRef = ref()

// 更新权限弹窗
const dialogVisible = ref(false)
const currentItem = ref({})
const updateForm = reactive({
    title: ""
});

//表单验证规则
const rules = reactive({
    title: [{ required: true, message: "请输入权限名", trigger: "blur" }],
});

// 更新权限
const handleUpdate = (item) => {
    // console.log(item)
    currentItem.value = item
    updateForm.title = item.title
    dialogVisible.value = true
}

// 更新权限确认
const handleConfirm = () => {
    updateFormRef.value.validate( (valid, fields) => {
        if (valid) {
            request.put(`/adminapi/rights/${currentItem.value.id}`, updateForm)
            getList()
            dialogVisible.value = false
        } else {
            console.log('error submit!', fields)
        }
    })
}

// 更新权限表单
const handleDelete =  ({id}) =>{
    // console.log(id);
    request.delete(`/adminapi/rights/${id}`)
    getList()
    getList()
}


</script>
