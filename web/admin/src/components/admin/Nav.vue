<template>
    <a-layout-sider class="sider" breakpoint="lg">
      <div class="logo">
        </div>
      <a-menu mode="inline" theme="light" @click="goToPage ">
        <a-sub-menu>
          <span slot="title"><a-icon type="project" /><span>文章管理</span></span>
            <a-menu-item key="write">
             <a-icon type="edit" spin/><span>编写文章</span>
            </a-menu-item>
               <a-menu-item key="articles" >
            <a-icon type="snippets" /><span>文章列表</span>
            </a-menu-item>
        </a-sub-menu>
         <a-menu-item key="categories">
          <a-icon type="tags"/><span>分类管理</span>
        </a-menu-item>
        <a-menu-item key="comments">
          <a-icon type="message"/><span>评论管理</span>
        </a-menu-item>
         <a-menu-item key="users">
          <a-icon type="contacts"/><span>用户列表</span>
        </a-menu-item>
        <a-menu-item key="auth">
          <a-icon type="team"/><span>权限管理</span>
        </a-menu-item>
          <a-menu-item key="links">
          <a-icon type="link"/><span>链接管理</span>
        </a-menu-item>
      
      
        
      </a-menu>
    </a-layout-sider>
</template>


<script>
export default {
  methods: {
    goToPage(item){
      this.$router.push(item.key)
    }
  }
}
</script>

<style scoped>
.logo{
  height:64px;
}
</style>