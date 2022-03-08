<template>
  <div> 
     <h1 class="title">Category 分类:</h1>
    <ul class="categories" v-for="item in categories" :key="item.id">
      <li class="category">
        <a @click="$router.push(`category/${item.id}`)">
          <h2 class="categoryName">{{item.name}}</h2>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
  export default {
    data() {
      return{
        categories:{   
        },

      }
    },
    mounted(){
      this.getArticles();
    },
    methods:{
     async getArticles(){
        const {data:res} = await this.$http.get(`api/category`)
        this.categories = res.data.list
      }
    },
 
  }
</script>

<style scoped>

  .item-headline{
      margin: 10px 0px 0px 10px;
  }

  .title{
    
    color:#e91e63;
    font-size:2em;
    padding-bottom:20px;
   
  }

  .categories{
    margin-bottom: 1.5rem;
  }

  ul{
    list-style: none;
    scroll-padding-inline-start: 40px;
  }

 
  li{
    display: list-item;
    text-align: -webkit-match-parent;
  }

  .category{
    margin-bottom: 1rem;
    transition-duration: .2s;
    transition-timing-function: cubic-bezier(.4,0,.2,1);
    transition: opacity  0.5s;
    transition-duration: 1s;
    transition-timing-function: ease-out;
  }
  .category:hover{
    background-color: rgba(147, 161, 161, 0.4);
    opacity: 1;
    transition-delay: .2s;

  }
  
  .categoryName{
    color: rgba(7,54,66,1);
    font-size: 1.20rem;
    line-height: 1.75rem;
    margin-bottom: 0.25rem;
  }




</style>