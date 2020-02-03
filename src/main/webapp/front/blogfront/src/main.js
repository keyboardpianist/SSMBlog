// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Root from './components/Root';
import List from "./components/List";
import Paper from "./components/Paper";
import VueRouter from 'vue-router'

Vue.config.productionTip = false
Vue.use(VueRouter)
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

let router = new VueRouter({
  mode: 'history',
  base: '/SSMBlog/',/*前端路由和后端一致，用于覆盖后端路由被访问的情况*/
  routes: [
    {
      path:'/list',
      component: List
    },
    {
      path:'/detail/:pid',
      component: Paper
    },
    {
      path: '/',
      redirect: 'list'
    }
  ]
})

/* eslint-disable no-new */
new Vue({
  el: '#root',
  router,
  render: h => h(Root)
})
