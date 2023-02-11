import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import Cloudinary from 'cloudinary-vue'
// import { createApp } from 'vue'

Vue.config.productionTip = false

Vue.use(Cloudinary, {
  configuration: {
    cloudname: 'dmdwwopva', apiKey: '357499955813181'
  }
})

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
