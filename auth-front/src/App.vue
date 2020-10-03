<template>
  <v-app>
    <v-app-bar
      app
      dark
    >
      Authentication Sample
      <v-spacer></v-spacer>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
  import HelloWorld from './components/HelloWorld';

  export default {
  name: 'App',

  components: {
    HelloWorld,
  },

  data: () => ({
    //
  }),
};
</script>
