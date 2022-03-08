import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Admin from '../views/Admin.vue'
import Write from '../components/article/Write.vue'
import Articles from '../components/article/Articles.vue'
import Users from '../components/user/Users.vue'
import Categories from  '../components/category/Categories.vue'
import Comments from '../components/comment/Comments.vue'
import Auth from '../components/auth/Auth.vue'
import Links from '../components/link/Links.vue'

Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: 'login',
    name: 'login',
    component: Login
  },
  {
    path: '/',
    name: 'admin',
    component: Admin,
    children: [
      {
        path: 'write',
        component: Write,
        meta: {
          requiresAuth: true,
          title: '编写文章'
        } 
      },
      {
        path: 'articles',
        component: Articles,
        meta: {
          requiresAuth: true,
          title: '文章列表'
        } 
      },
      {
        path: 'users',
        component: Users,
        meta: {
          requiresAuth: true,
          title: '用户列表'
        } 
      },
      {
        path: 'auth',
        component: Auth,
        meta: {
          requiresAuth: true,
          title: '权限管理'
        } 
      },
      {
        path: 'categories',
        component: Categories,
        meta: {
          requiresAuth: true,
          title: '分类列表'
        }
      },
      {
        path: 'comments',
        component: Comments,
        meta: {
          requiresAuth: true,
          title: '评论管理'
        }
      },
      {
        path: 'links',
        component: Links,
        meta: {
          requiresAuth: true,
          title: '链接管理'
        }
      },
      {
        path: 'addpost/:id',
        component: Write,
        props: true,
        meta: {
          requiresAuth: true,
          title: '编辑文章'
        } 
      },


    ]

  }
]

const router = new VueRouter({
  mode: 'history',
  base: 'admin',
  scrollBehavior: ()=> ({y:0}),
  routes
})



// Request interceptor
router.beforeEach((to, from, next) => {
  const token = window.sessionStorage.getItem('token')
  if (to.matched.some(record=> record.meta.requiresAuth)){
    if (!token){
      next({
        name: 'login',
        query: {redirect: to.fullPath}
      })
    }else{
      next()
    }
  }else{
    next()
  }
})


export default router

