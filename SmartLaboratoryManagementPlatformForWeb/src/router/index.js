import {createRouter, createWebHashHistory} from 'vue-router'
import RouteConfig from './config'
import { useRouterStore } from '../store/useRouterStore'
import { useUserStore } from '../store/useUserStore'

const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/mainbox',
        name: 'mainbox',
        component: () => import('../views/MainBox.vue')
    }
]

const router = createRouter({
    history: createWebHashHistory(),//#/login
    routes
})
// 路由守卫
router.beforeEach((to, from, next) => {

    const { isGetterRouter } = useRouterStore()
    const {user} = useUserStore()
    // next()
    if (to.name === "login") {
        next()
    } else {
        if (!user.roles) {
            //跳转登录
            next({
                path: "/login"
            })
        } else {
            if (!isGetterRouter) {
                //remove mainbox
                router.removeRoute("mainbox")
                ConfigRouter()
                next({
                    path:to.fullPath
                })
            }else{
                next()
            }
            
        }
    }
})

// 配置路由
const ConfigRouter = () => {
    router.addRoute({
        path: "/mainbox",
        name: "mainbox",
        component: () => import('../views/MainBox.vue'),
    })
    let {changeRouter} = useRouterStore()
    RouteConfig.forEach(item => {
        checkPermission(item.path) && router.addRoute("mainbox", item)
    })
    //重定向
    router.addRoute("mainbox", {
        path: "/",
        redirect: "/index"
    })

    //404
    router.addRoute("mainbox", {
        path: '/:pathMatch(.*)*',
        name: 'not found',
        component: () => import('../views/404.vue')
    })


    changeRouter(true)
}

//添加路由
// router.addRoute("mainbox", {
//     path: '/index',
//     component: () => import('../views/home/Home.vue')
// })

const checkPermission = (path)=>{
    const {user} = useUserStore()

    return user.roles.rights.includes(path)
}

export default router
