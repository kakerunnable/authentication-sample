<template>
  <div>
    <v-container class="mt-10" fill-height fluid>
      <v-layout align-center justify-center>
        <v-flex>
          <div class="headline">Login</div>
          <input-form :afterValidation="login" :formItems="items" class="pt-3">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn dark type="submit">Login</v-btn>
            </v-card-actions>
          </input-form>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';
  import AuthService from '@/infrastructure/auth/authService';
  import FormItem from "@/infrastructure/form/formItem";

  @Component
  export default class Login extends Vue {

    private loginId = new FormItem('loginId', 'Email or Username')
            // .withIcon('email')
            .withRequired(true) // TODO Not Working
            .withType('text');

    private password = new FormItem('password', 'Password')
            // .withIcon('lock')
            .withRequired(true) // TODO Not Working
            .withType('password');

    items = [this.loginId, this.password];

    public beforeMount() {
      if (AuthService.loggedIn()) {
        this.$router.push('/');
      }
    }

    public login() {
      AuthService
              .login(this.loginId.value, this.password.value)
              .then((success) => {
                if (success) {
                  this.$router.push('/');
                }
              });
    }
  }
</script>