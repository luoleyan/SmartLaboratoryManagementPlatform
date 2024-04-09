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
    history: createWebHashHistory(),
    routes // short for `routes: routes`
})

// 路由守卫
router.beforeEach((to, from, next) => {
    const {isGetterRouter} = useRouterStore()
    const {user} = useUserStore()
    if (to.path === '/login') {
        next()
    } else {
        if (!user.role) {
            next({
                path:'/login'
            })
            
            }else{
                if(!isGetterRouter){
                    router.removeRoute("mainbox")
                    // 判断是否已经添加路由
                    configRouter()
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
const configRouter = () => {
    router.addRoute({
        path: '/mainbox',
        name: 'mainbox',
        component: () => import('../views/MainBox.vue'),
    })
    let {changeRouter} = useRouterStore()
    RouteConfig.forEach(item => {
        checkPermison(item.path) && router.addRoute("mainbox", item)
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

const checkPermison = (path) => {
    const {user} = useUserStore()

    return user.role.rigths.includes(path)
}

export default router
