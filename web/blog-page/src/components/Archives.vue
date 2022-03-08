<template>
  <div>

  <div class="archives" v-for="archive in archives" :key="archive.year">
     <span class="year">{{archive.year}}</span>
    <ul class="articles" >
      <li class="article" v-for="article in archive.archives" :key="article.id" @click="$router.push(`article/${article.id}`);">
        <a class="archiveLink">
          <article>
            <time class="datetime">{{article.updateTime| dateformat('DD MMM')}}</time>
            <h2 class="title" >{{article.title}}</h2>
          </article>
        </a>
      </li> 
    </ul>
  </div>
  </div>
  

  
</template>

<script>
  export default {
    data() {
      return{
        archives:{   
        },

      }
    },
    mounted(){
      this.getArticles();
    },
    methods:{
     async getArticles(){
        const {data:res} = await this.$http.get(`api/archives`)
        this.archives = res.data.archivesList
        console.log(this.archives)

      }
    },
 
  }
</script>

<style scoped>

.archives{
  margin: 10px 0px 0px 10px;
}

.articleTime{
  color: #858585;
  font-size: 95%;
}

.articleTime time{
  padding-left: 0.3rem;
}

.year{
   color:#34495e;
  font-size: 2em;
}

.articles{
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
  .datetime{
    color: rgba(88,110,117,1);
    font-size: .875rem;
    line-height: 1.25rem;
    margin-bottom: 0.25rem;
  }
  .article{
    margin-bottom: 1rem;
    transition-duration: .2s;
    transition-timing-function: cubic-bezier(.4,0,.2,1);
    transition: opacity  0.5s;
    transition-duration: 1s;
    transition-timing-function: ease-out;
    padding-left :10px;
  }
  .article:hover{
    background-color: rgba(147, 161, 161, 0.4);
    opacity: 1;
    transition-delay: .2s;
    border-left: 6px solid #e91e63;

  }
  .title{
    color: rgba(7,54,66,1);
    font-size: 1.25rem;
    line-height: 1.75rem;
    margin-bottom: 0.25rem;
  }

.archiveLink:hover{
     color: #141414!important;
    background-color: #f2f2f2;
    position: relative;
  border-top: 1px solid #c4c4c4;
  height: calc(66 / 1024 * 100vw);
  align-items: center;
  flex-direction: row;
  height: 86px;
  justify-content: space-between;
  padding-bottom: 0;
  padding-top: 0;
}


 




</style>