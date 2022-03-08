<template>
    <a-layout class="container">
       <Nav></Nav>
      <a-layout>
       <Header></Header>
        <a-layout-content>
          <router-view :key="$route.fullpath"></router-view>
          </a-layout-content>
        <a-layout-footer>
          <Footer></Footer>
        </a-layout-footer>
      </a-layout>
    </a-layout>
</template>

<script>
import Nav from '../components/admin/Nav'
import Footer from '../components/admin/Footer'
import Header from '../components/admin/Header'
export default {
  components: {Nav, Footer, Header}
}
</script>

<style scoped>
.container{
  height: 100%;
}
.header{
  background-color:rgb(207, 223, 221);
}
.sider{
  background-color:rgb(207, 223, 221);
}
</style>