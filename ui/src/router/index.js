import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import(/* webpackChunkName: "home" */ '@/views/Home')
  },
  {
    path: '/upload/:id',
    name: 'Upload',
    props: true,
    component: () => import(/* webpackChunkName: "upload" */ '@/views/Upload')
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router