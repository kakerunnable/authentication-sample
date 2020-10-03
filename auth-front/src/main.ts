import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import vuetify from './plugins/vuetify';
import InputForm from "@/components/InputForm.vue";
import AuthProcessor from "@/infrastructure/auth/authProcessor";

Vue.use(BootstrapVue);
Vue.config.productionTip = false;

Vue.component('InputForm', InputForm);

async function init() {
  await AuthProcessor.getAuthenticatedUser();
  router.beforeEach(async (to, from, next) => {
    if (to.path !== "/login" && !store.getters.getIsAuthenticated) {
      try {
        const statusCode = await AuthProcessor.refreshToken();
        if (statusCode !== 200) next("/login");
        else next();
      } catch (error) {
        next("/login");
      }
    } else {
      next();
    }
  });

  new Vue({
    router,
    store,
    // @ts-ignore
    vuetify,
    render: h => h(App)
  }).$mount("#app");
}

init();
