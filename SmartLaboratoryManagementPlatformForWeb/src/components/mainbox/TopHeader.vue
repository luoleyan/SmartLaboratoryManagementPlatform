<template>
    <div>
        <el-header>
            <div>
                实验室管理系统
            </div>

            <div>
                <el-dropdown>
                    <el-avatar :size="44" :src="circleUrl" />
                    <span style="line-height: 40px;">欢迎！ {{ user.username }}</span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item>{{ user.roles.roleName }}</el-dropdown-item>
                            <el-dropdown-item @click="handelExit">退出</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </el-header>
    </div>
</template>

<script setup>
import { useUserStore } from "../../store/useUserStore";
import { useRouterStore } from "../../store/useRouterStore";
import { useRouter } from "vue-router";
// 用户信息
const router = useRouter();
const { changeRouter } = useRouterStore();
const { changeUser, user } = useUserStore();
// console.log(user);
// 头像
const circleUrl = "/avatar.jpg";
// 退出登录
const handelExit = () => {
    changeUser({});
    // 清除用户信息
    changeRouter(false);

    // 退出登录后跳转到登录页面
    router.push("/login");
};
</script>

<style lang="scss" scoped>
.el-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #66ccff;
    color: #545c64;
}
</style>