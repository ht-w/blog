<template>
  <v-app app>
    
    <Nav></Nav>
 
    <v-main class="white">
      <v-container fluid >
        <v-row>
          <v-col cols="3"></v-col>
          <!-- <v-col cols="4">
            <Side > </Side>
          </v-col> -->
          <v-col cols="12" md="6">
              <router-view/>
          </v-col>
           <v-col cols="2">
              
          </v-col>
        </v-row>
      </v-container>
    </v-main>

    <Footer></Footer>
  </v-app>

</template>

<script>
import Footer from '../components/Footer.vue'
import Nav from '../components/Nav.vue'
  export default {
    components: { 
     Footer,  Nav
    },
  };
</script>

<style scoped>


</style>
