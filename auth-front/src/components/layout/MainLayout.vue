<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script lang="ts">

  import {Component, Vue} from "vue-property-decorator";
  import AuthService from "@/infrastructure/auth/authService";

  @Component
  export default class MainLayout extends Vue {

    public beforeMount() {
      if (!AuthService.loggedIn()) {
        this.$router.push('/login');
      }
    }
  }
</script>

<style></style>
