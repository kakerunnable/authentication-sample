<template>
  <div>
    <v-card elevation="0" class="pa-10">
      <v-card-title>User Profile</v-card-title>
      <div class="ml-5">
        <ul>
          <li>User ID: {{ user.userId }}</li>
          <li>Username: {{ user.username }}</li>
          <li>E-Mail: {{ user.email }}</li>
          <li>Roles: {{ user.roles }}</li>
        </ul>
      </div>
      <div class="ml-3">
        <v-btn dark tile @click="logout">logout</v-btn>
      </div>
    </v-card>
  </div>
</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';
  import AuthService from "@/infrastructure/auth/authService";
  import {User} from "@/infrastructure/auth/user";

  @Component
  export default class Profile extends Vue {

    private user: User = new User();

    public beforeMount() {
      if (AuthService.loggedIn()) {
        this.user = this.$store.getters.getCurrentUser;
      }
    }

    public logout() {
      AuthService.logout().then(() => {
        this.$router.push('/login');
      });
    }
  }
</script>

<style></style>
