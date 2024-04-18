<template>
    <div>
        <el-button type="primary" @click="handleAdd">添加用户</el-button>
        <el-table :data="tableData" style="width: 100%">
            <!-- <el-table-column prop="roleName" label="角色名称" width="180" /> -->
            <el-table-column label="角色名称">
                <template #default="scope">
                    <div>{{ scope.row.roles.roleName }}</div>
                </template>
            </el-table-column>
            <el-table-column prop="username" label="用户名" width="180px"></el-table-column>

            <el-table-column label="操作">
                <template #default="scope">
                    <el-button round type="warning" @click="handleUpdate(scope.row)"
                        :disabled="scope.row.isDefault ? true : false">更新</el-button>
                    <el-popconfirm title="你确定?" @confirm="handleDelete(scope.row)" confirm-button-text="确定"
                        cancel-button-text="取消">
                        <template #reference>
                            <el-button type="danger" round :disabled="scope.row.isDefault ? true : false">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog v-model="dialogVisible" title="添加用户" width="500">

            <el-form ref="addFormRef" style="max-width: 600px" :model="addForm" :rules="rules" label-width="auto"
                class="demo-ruleForm" :size="formSize" status-icon>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="addForm.username" placeholder="username" />
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="addForm.password" placeholder="password" type="password" />
                </el-form-item>
                <el-form-item label="角色" prop="roleId">
                    <el-select v-model="addForm.roleId" placeholder="请选择角色" size="large" style="width: 240px">
                        <el-option v-for="item in roleList" :key="item.id" :label="item.roleName" :value="item.id" />
                    </el-select>
                </el-form-item>
            </el-form>

            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleAddConfirm()">
                        确定
                    </el-button>
                </div>
            </template>
        </el-dialog>

        <el-dialog v-model="dialogUpdateVisible" title="更新用户" width="500">

            <el-form ref="updateFormRef" style="max-width: 600px" :model="updateForm" :rules="rules" label-width="auto"
                class="demo-ruleForm" :size="formSize" status-icon>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="updateForm.username" placeholder="username" />
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="updateForm.password" placeholder="password" type="password" />
                </el-form-item>
                <el-form-item label="角色" prop="roleId">
                    <el-select v-model="updateForm.roleId" placeholder="请选择角色" size="large" style="width: 240px">
                        <el-option v-for="item in roleList" :key="item.id" :label="item.roleName" :value="item.id" />
                    </el-select>
                </el-form-item>
            </el-form>

            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="dialogUpdateVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleUpdateConfirm()">
                        更新
                    </el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { onMounted, reactive, ref, nextTick } from 'vue'
import request from '../../util/request';

const tableData = ref([])
const roleList = ref([])

const defaultProps = {
    children: 'children',
    label: 'title'
}

onMounted(() => {
    getList()
    getRoleList()
})

const getList = () => {
    request.get('/adminapi/users').then(res => {
        tableData.value = res
    })

}

const getRoleList = () => {
    request.get('/adminapi/roles').then((res) => {
        roleList.value = res
    })

}

const dialogVisible = ref(false)

const handleAdd = () => {
    dialogVisible.value = true
}

const addFormRef = ref()

const addForm = reactive({
    username: "",
    password: "",
    roleId: ""
})

const rules = reactive({
    username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
    ],
    roleId: [
        { required: true, message: '请选择角色', trigger: 'blur' },
    ]
})

const handleAddConfirm = () => {
    addFormRef.value.validate((valid) => {
        if (valid) {
            dialogVisible.value = false
            request.post(`/adminapi/users`, addForm)
            getList()
            addForm.username = ""
            addForm.password = ""
            addForm.roleId = ""
        } else {
            console.log(valid);
        }
    })
}

const dialogUpdateVisible = ref(false)
const updateFormRef = ref()
const updateForm = reactive({
    username: "",
    password: "",
    roleId: ""
})
const currentItem = ref({})
const handleUpdate = (item) => {
    dialogUpdateVisible.value = true

    // console.log(item.username);
    updateForm.username = item.username
    updateForm.password = item.password
    updateForm.roleId = item.roleId
    // console.log(updateForm);

    currentItem.value = item
}

const handleUpdateConfirm = ()=>{
    dialogUpdateVisible.value = false
    request.put(`/adminapi/users/${currentItem.value.id}`, updateForm)

    getList()
}

const handleDelete = ({id}) =>{
    request.delete(`/adminapi/users/${id}`)
    getList()
}
</script>

