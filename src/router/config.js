const routes = [
    {
        path: '/index',
        name: 'index',
        component: () => import('../views/home/Home.vue') // 懒加载
    },
    //用户管理
    {
        path: '/user-manage/list',
        name: 'user-manage-list',
        component: () => import('../views/user-manage/UserManageList.vue') // 懒加载
    },
    //权限管理
    {
        path: '/right-manage/rolelist',
        name: 'right-manage-rolelist',
        component: () => import('../views/right-manage/UserManageRoleList.vue') // 懒加载
    },
    {
        path: '/right-manage/rightlist',
        name: 'right-manage-rightlist',
        component: () => import('../views/right-manage/RightManageRightList.vue') // 懒加载
    },
    //实验室管理
    {
        path: '/lab-manage/lablist',
        name: 'lab-manage-lablist',
        component: () => import('../views/lab-manage/LabManageLabList.vue') // 懒加载
    },
    {
        path: '/lab-manage/addlab',
        name: 'lab-manage-addlab',
        component: () => import('../views/lab-manage/LabManageAddLab.vue') // 懒加载
    },
    //预约管理
    {
        path: '/book-manage/auditlist',
        name: 'book-manage-auditlist',
        component: () => import('../views/book-manage/BookManageAuditList.vue') // 懒加载
    },
    {
        path: '/book-manage/booklist',
        name: 'book-manage-booklist',
        component: () => import('../views/book-manage/BookManageBookList.vue') // 懒加载
    },
    {
        path: '/book-manage/addbook',
        name: 'book-manage-addbook',
        component: () => import('../views/book-manage/BookManageAddBook.vue') // 懒加载
    }
]

export default routes