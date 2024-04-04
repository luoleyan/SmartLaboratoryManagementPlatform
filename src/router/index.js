import {createRouter, createWebHashHistory} from 'vue-router'
import RouteConfig from './config'
import { useRouterStore } from '../store/useRouterStore'

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
    history: createWebHashHistory(),
    routes // short for `routes: routes`
})

// 路由守卫
router.beforeEach((to, from, next) => {
    const {isGetterRouter} = useRouterStore()
    const token = localStorage.getItem('token')
    if (to.path === '/login') {
        next()
    } else {
        if (token) {
            if(!isGetterRouter){
                configRouter()
                next({
                    path:to.fullPath
                })
            }else{
                next()
            }
        } else {
            next('/login')
        }
    }
})

// 配置路由
const configRouter = () => {
    let {changeRouter} = useRouterStore()
    RouteConfig.forEach(item => {
        router.addRoute("mainbox", item)
    })

    //重定向
    router.addRoute("mainbox", {
        path: '/',
        redirect: '/index'
    })

    //404
    router.addRoute("mainbox", {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: () => import('../views/404.vue')
    })

    console.log(router.getRoutes());

    changeRouter(true)
}

//添加路由
// router.addRoute("mainbox", {
//     path: '/index',
//     component: () => import('../views/home/Home.vue')
// })


export default router
