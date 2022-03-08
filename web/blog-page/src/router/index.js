import Vue from 'vue'
import VueRouter from 'vue-router'
const Articles = () =>
  import(/* webpackChunkName: "group-index" */ '../components/Articles.vue')
const Article = () =>
  import(/* webpackChunkName: "group-detail" */ '../components/Article.vue')
const Categories = () =>
  import(/* webpackChunkName: "group-category" */ '../components/Categories.vue')
import About from '../components/About.vue'
import Archives from '../components/Archives.vue'
import Search from '../components/Search.vue'
import ArticleByCategory from '../components/ArticlebyCategory.vue'
import Link from '../components/Link.vue'



Vue.use(VueRouter)


const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}


const routes = [
  { path: '/', component: Articles, meta: { title: "HongTing's Blog" } },
  {
    path: '/article/:id',
    component: Article,
    meta: { title: window.sessionStorage.getItem('title') },
    props: true
  },
  {
    path: '/category/:cid',
    component: ArticleByCategory,
    meta: { title: "HongTing's Blog" },
    props: true
  },
  {
    path: '/about',
    component: About,
    meta: {title: "About | HongTing's Blog"}
  },
  {
    path: '/archives',
    component: Archives,
    meta: {title: "Archives | HongTing's Blog"}
  },
  {
    path: '/categories',
    component: Categories,
    meta: {title: "Categories | HongTing's Blog"}
  },
  {
    path: '/search',
    component: Search,
    meta: {title: "Seach | HongTing's Blog"}
  },
  {
    path: '/link',
    component: Link,
    meta: {title: "Links | HongTing's Blog"}
  },
  
  
      
  
  

]

const router = new VueRouter({
  mode: 'history',
  base:'/',
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title ? to.meta.title : '加载中'
  }
  next()
})
export default router
