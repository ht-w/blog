<template>
  <div>
    <v-row  justify='center' align='center' class="search-row">
    <div class="search">
      <input type="checkbox" id="checkbox">
      <label for="checkbox"></label>
      <input type="text" v-model="keyword" class="input" id="input"> 
      <div class="fonts">
        <span style="--i:1">S</span>
        <span style="--i:2">e</span>
        <span style="--i:3">a</span>
        <span style="--i:4">r</span>
        <span style="--i:5">c</span>
        <span style="--i:6">h</span>
        <span style="--i:7">.</span>        
        <span style="--i:8">.</span>
        <span style="--i:9">.</span>
        <span style="--i:10">.</span>
        <span style="--i:11">.</span>
        <span style="--i:12">.</span> 
      </div>  
    </div> 
    </v-row>
    <v-row  justify='center' align='center'>
     <div class="archivesContent">
    <ul class="articles" >
      <li class="article" v-for="item in searchData" :key="item.id" @click="toArticle(item.id)">
        <a>
          <article>
            <time class="datetime">{{cutDateTime(item.updateTime)| dateformat('DD MMM YYYY')}}</time>
            <h2 class="title" v-html="item.title"></h2>
            <p class="summary" v-html="item.description"></p>
          </article>
        </a>
      </li> 
   
    </ul>
     <span v-show="hasNoData">Couldn't find any related articles matching "{{this.keyword}}"</span>
  </div>
    </v-row>


  
  </div>

  
</template>
<script>
export default{
  data(){
    return{
      keyword: '',
      searchData: [],
      timer: null
    }
  },
  computed:{
    hasNoData(){
      return !this.searchData.length
    }
  },
  methods:{
    async searchArticles () {
      const { data: res } = await this.$http.get('/api/search', {
        params: {
          keyword: this.keyword,
        }
      })
      this.searchData = res.data.searchData
    },
     cutDateTime (time) {
      return time.substr(0, 10)
    },
    toArticle(id){
      this.$router.push(`article/${id}`);
    }
  },
  watch:{
    keyword(){
      if (this.timer){
        clearTimeout(this.timer)
      }
      if (!this.keyword){
        this.searchData = []
        return 
      }
      this.timer = setTimeout(()=>{
        this.searchArticles();
      }, 100)
    }
  }



}
</script>

<style scoped>

  *{
    margin: 0;
    padding: 0;
    background-size: cover;
  }

  .search-row {
    margin-top: 100px;
  }
  .search{
    position: relative;
  }
  .input{
    padding: 10px;
    width: 80px;
    height: 80px;
    background: none;
    border: 4px solid#666666;
    border-radius: 20px;
    box-sizing: border-box;
    font: 400 26px Comic Sans Ms;
    color:  #cfcfdf;
    outline: none;
    transition: .5s;
  }

  #checkbox{
    display:none;
  }
  [for=checkbox]{
    display: block;
    width: 70px;
    height: 70px;
    border-radius: 20px;
    position: absolute;
    right: 0;
    margin: 4px;
    transition: .4s;
    background-image: url("../assets/search.png");
  }

  #checkbox:checked~label{
    border-radius: 10px;
    border: 4px solid #666666;
    margin: 0 -5px; 
    background-image: url("../assets/cancel.png");
  }

  #checkbox:checked~input{
    width:400px;
    border-radius: 10px;
    margin-right: 80px; 
    background-color: #F5F5F5;
    color: #808A87
  }

  .fonts{
    position: absolute;
    top: 0;
    left: 10px;
    color:  #666666;
    letter-spacing: 5px;
    font: 400 26px Comic Sans Ms;  
    display: flex;
  }
  .fonts span{
    transition: .3s;
    opacity: 0;
  }
  #checkbox:checked ~.fonts span{
    transition: calc(var(--i)*0.1s) .3s;
    transform: translateY(-40px);
    opacity: 1;
  }
  .articles{
    margin-bottom: 1.5rem;
  }
  ul{
    margin-top: 20px;
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
    padding-left : 10px;
    
  }
  .article:hover{
    background-color: rgba(147, 161, 161, 0.4);
    border-left: 6px solid #e91e63;
  }

  article{
    display: block;
  }

  .title{
    color: rgba(7,54,66,1);
    font-size: 1.25rem;
    line-height: 1.75rem;
    margin-bottom: 0.25rem;
  }

  h2{
    margin: 0;
  }

  p{
    margin: 0;
  }
  .summary{
    font-size: 1rem;
    line-height: 1.5rem;
    color: rgba(88,110,117,1);
  }



</style>