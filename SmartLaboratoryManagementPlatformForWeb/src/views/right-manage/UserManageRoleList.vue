<template>
    <div>
        <el-table :data="tableData" style="width: 100%">
            <!-- <el-table-column prop="roleName" label="角色名称" width="180" /> -->
            <el-table-column label="角色名称">
                <template #default="scope">
                    <el-popover placement="right" title="权限详情" :width="200" trigger="hover"
                        @before-enter="handleHover(scope.row)">
                        <template #reference>
                            <el-button class="m-2">{{ scope.row.roleName }}</el-button>
                        </template>
                        <template #default>
                            <el-tree style="max-width: 600px" :data="getRightList" :props="defaultProps"
                                default-expand-all :render-content="render" />
                        </template>
                    </el-popover>

                </template>
            </el-table-column>

            <el-table-column label="操作">
                <template #default="scope">
                    <el-button round type="warning" @click="handleUpdate(scope.row)">更新</el-button>
                    <el-popconfirm title="你确定?" @confirm="handleDelete(scope.row)" confirm-button-text="确定"
                        cancel-button-text="取消">
                        <template #reference>
                            <el-button type="danger" round>删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog v-model="dialogVisible" title="更新角色" width="500">

            <el-form ref="updateFormRef" style="max-width: 600px" :model="updateForm" :rules="rules" label-width="auto"
                class="demo-ruleForm" :size="formSize" status-icon>
                <el-form-item label="角色名称" prop="roleName">
                    <el-input v-model="updateFormRef.roleName" placeholder="roleName" />
                </el-form-item>
                <el-form-item label="角色权限" prop="rights">
                    <el-tree style="max-width: 600px" :data="getRightList" :props="defaultProps" show-checkbox node-key="path"  ref="treeRef" :check-strictly="true"/>
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
import { onMounted, reactive, ref, nextTick } from 'vue'
import axios from 'axios'

const tableData = ref([])
const rightList = ref([])
const currentRights = ref([])

const defaultProps = {
    children: 'children',
    label: 'title'
}

onMounted(() => {
    getList()
    getRightList()
})

const getList = async () => {
    await axios.get('/adminapi/roles')
    tableData.value = res.data
}

const getRightList = async () => {
    await axios.get('/adminapi/rights')
    rightList.value = res.data
}

const renderContent = (
    h,
    {
        node,
        data,
        store,
    }
) => {
    return h(
        'span',
        {
            class: `custom-tree-node ${currentRights.value.includes(data.path) ? 'active' : ''}`,
        },
        h('span', null, node.label),
    )
}

const handleHover = ({ rights }) => {
    currentRights.value = rights
}

const dialogVisible = ref(false)
const currentItem = ref({})
const updateForm = reactive({
    roleName:"",
});
//表单验证规则
const rules = reactive({
    title: [{ required: true, message: "请输入角色名", trigger: "blur" }],
});

const handleUpdate = (item) => {
    // console.log(item);
    dialogVisible = true
    updateForm.roleName = item.roleName
    currentItem.value = item

    nextTick(() =>{
        treeRef.value.setCheckedKeys(JSON.parse(item.rights)) 
    })
}

const handleConfirm = async () =>{
    await axios.put(`/adminapi/roles/${currentItem.value.id}`,{
        roleName:updateForm.roleName,
        rights:JSON.stringify(treeRef.value.getCheckedKeys(true))

    })
    await getList()
    dialogVisible.value = false
} 


const handleDelete = async ({ id }) => {
    // console.log(id);
    await axios.delete(`adminapi/roles/${id}`)
    await getList() 
}

const treeRef = ref()

</script>


<style lang="scss" scoped>
::v-deep .active {
    color: aquamarine;

}
</style>