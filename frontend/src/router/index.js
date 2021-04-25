import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Blogs from '../views/Blogs'
import BlogEdit from '../views/BlogEdit'
import BlogDetail from '../views/BlogDetail'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Blogs',
    component: Blogs
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/',
    name: 'BlogEdit',
    component: BlogEdit
  },
  {
    path: '/',
    name: 'BlogDetail',
    component: BlogDetail
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
