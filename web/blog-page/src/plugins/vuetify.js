import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import 'font-awesome/css/font-awesome.min.css'
import Message from 'vue-multiple-message'

Message.config({
  top: 60,
  duration: 3000,
  zIndex: 2000
})
Vue.prototype.$message = Message
Vue.use(Vuetify);

export default new Vuetify({
  icons:{
    iconfont: 'fa'
  },
  breakpoint:{
    mobileBreakpoint:'sm'
  }
});
