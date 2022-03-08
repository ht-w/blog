import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugin/http.js'
import './plugin/antui.js'
import './assets/css/style.css'
import './plugin/editor.js'
import './plugin/hljs.js'
import 'highlight.js/styles/googlecode.css'
import hljs from 'highlight.js'
import qs from 'querystring'



hljs.highlightCode = function () {
     
	//自定义highlightCode方法，将只执行一次的逻辑去掉
	let blocks = document.querySelectorAll('pre code');
	[].forEach.call(blocks, hljs.highlightBlock);
};
//在组件中
export default {
    
  mounted(){
    
     hljs.highlightCode()
  }    
}



Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
