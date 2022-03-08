import Vue from 'vue'
import axios from 'axios'

axios.defaults.baseURL= "http://localhost:8110/"
Vue.prototype.$http = axios

