<template>
<vue-particles
            id="tsparticles"
            :particlesLoaded="particlesLoaded"
            :options = "config"
        />

    <div class="bgd">
        <div class="login">
            <div class="box">
                <p class="table">登 录</p>
                <el-form ref="ruleFormRef" style="max-width: 600px" :model="ruleForm" :rules="rules" label-width="auto"
                    class="demo-ruleForm" :size="formSize" status-icon>
                    <el-form-item label="用户名：" prop="username">
                        <el-input v-model="ruleForm.username" placeholder="username" />
                    </el-form-item>
                    <el-form-item label="密码：" prop="password">
                        <el-input v-model="ruleForm.password" type="password" placeholder="password" />
                    </el-form-item>
                    <el-form-item>
                        <el-button type="login" @click="submitForm(ruleFormRef)">
                            登 录
                        </el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
    <!-- <form>
        <div class="login">
            <div class="box">
                <p class="table">Login</p>
                <br />
                <input type="text" placeholder="username" />
                <input type="password" name="password" id="password" placeholder="password" />
                <br />
                <a href="#" class="go">GO!</a>
            </div>
        </div>
    </form> -->
</template>

<style lang="scss"scoped>
.bgd {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    text-decoration: none;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-image: url("../resource/photo/illust_100018879_20221202_164440.png");
    background-size: cover;
    z-index: 99;
}

.login {
    width: 550px;
    height: 400px;
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(to right bottom,
            rgba(255, 255, 255, 0.5),
            rgba(255, 255, 255, 0.8),
            rgba(255, 255, 255, 0.9));
    backdrop-filter: blur(5px);
    box-shadow: 0 0 100px rgba(255, 255, 255, 0.5);
    border-radius: 15px;
    z-index: 100;
}

.table {
    font: 900 40px "";
    text-align: center;
    letter-spacing: 5px;
    color: #3d3d3d;
}

.box {
    overflow: hidden;
    display: flex;
    flex-direction: column;

}

::v-deep .el-form-item__label {
        font: 900 20px "";
        color: #3d3d3d;
        margin-top: 20px;
    }

::v-deep .el-input__wrapper {
    width: 400px;
    height: 100%;
    margin-bottom: 20px;
    outline: none;
    border: 0;
    padding: 10px;
    background-color: transparent !important;
    border-bottom: 3px solid rgb(150, 150, 240);
    font: 900 20px "";
}

::v-deep .el-button--login {
    width: 400px;
    height: 50px;
    text-align: center;
    display: block;
    padding: 12px;
    margin: 0 auto;
    font: 900 20px "";
    border-radius: 10px;
    margin-top: 20px;
    color: #3d3d3d;
    letter-spacing: 3px;
    background-image: linear-gradient(to right, #9933ff, #cc66ff, #9933ff);
}
</style>

<script setup>
import { useRouter } from "vue-router";
import { reactive, ref } from "vue";
import { config } from "../util/config";
import axios from "axios";
import request from "../util/request";
import { useUserStore } from "../store/useUserStore";
import { ElMessage } from 'element-plus';

const {changeUser} = useUserStore()

//粒子特效
const particlesLoaded = async container => {
    console.log("Particles container loaded", container);
};

//获取表单对象
const ruleForm = reactive({
    username: "",
    password: "",
});
const ruleFormRef = ref();

//表单验证规则
const rules = reactive({
    username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
    password: [{ required: true, message: "请输入密码", trigger: "blur" }],
});
const router = useRouter();

const submitForm = async (formEl) => {
    console.log(ruleForm);
    if (!formEl) return
    await formEl.validate( (valid, fields) => {
        if (valid) {
            // console.log('submit!')
            // const res = await axios.post(`/adminapi/users/login`, ruleForm)
            // console.log(res);
            request.post('/adminapi/users/login', ruleForm).then((res) =>{
                console.log(res);
                
                if(res.code === 0){
                    changeUser(res.data)
                    ElMessage.success(res.msg)
                    router.push("/")
                }else{
                    ElMessage.error(res.msg)
                }
            })
        } else {
            console.log('error submit!', fields)
        }
    })
}
</script>
