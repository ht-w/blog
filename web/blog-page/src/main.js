import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import '@/plugins/http'
import '@/assets/css/style.css'
import 'font-awesome/css/font-awesome.min.css'
import '@/assets/css/demo.scss'
import './common/stylus/icon.styl'
import MarkdownItVueLight from 'markdown-it-vue/dist/markdown-it-vue-light.umd.min.js'
import 'markdown-it-vue/dist/markdown-it-vue-light.css'
import 'viewerjs/dist/viewer.css'
import Viewer from 'v-viewer'
import day from 'dayjs'


Vue.filter('dateformat', function(indate, outdate) {
  return day(indate).format(outdate)
})

 

Vue.use(Viewer)
Vue.use(MarkdownItVueLight)
Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
