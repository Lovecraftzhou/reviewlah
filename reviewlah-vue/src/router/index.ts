import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/home/HomeView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/home/HomeView.vue')
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login/LoginView.vue')
  },
  {
    path: '/merchantDetail',
    name: 'merchantDetail',
    component: () => import('@/views/merchantDetail/MerchantDetailView.vue')
  },
  {
    path: '/merchantList',
    name: 'merchantList',
    component: () => import('@/views/merchantList/MerchantListView.vue')
  },
  {
    path: '/merchantEdit',
    name: 'merchantEdit',
    component: () => import('@/views/merchantEdit/MerchantEditView.vue')
  },
  {
    path: '/userInfoEdit',
    name: 'userInfoEdit',
    component: () => import('@/views/userInfoEdit/UserInfoEditView.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
