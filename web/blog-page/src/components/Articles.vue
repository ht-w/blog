<template>


<div  id="main">
<v-row dense  >
    <v-col cols="12"  v-for="(article, i) in postList" :key="i" >
          <article class="test d-flex flex-column flex-sm-row mb-10"  >
            <v-row dense>
              <v-col cols="12" class="pt-5">
                <h2 class="pl-3">
                  <a  class="articleTitle"   @click="$router.push(`article/${article.id}`);">{{article.title}}</a>
                </h2>
                <time class="datetime pl-3">{{article.createTime| dateformat('DD MMM YYYY')}}</time>
                <a class="category" @click="$router.push(`category/${article.categoryId}`)">#{{article.categoryName}}</a>
                <p class="articleDesc pl-3">{{article.description}}</p>
              </v-col>
            </v-row>

              <v-col cols="3" class="pt-5" >    
            <v-avatar class="articleCover" :width="imageWidth" :height="imageHeight" tile>
              <v-img :src="article.image"></v-img>
            </v-avatar>
              </v-col>
       
          </article>
    </v-col>
  </v-row>

   <v-row dense>
       <v-col cols="12">
            <div class="text-center">
             <v-pagination
             color="rgb(68,68,68)"
             total-visible="7"
             v-model="pageNum"
             :length="Math.ceil(total /pageSize)"
             @input="getPostList()"
            prev-icon="fa-angle-left"
            next-icon="fa-angle-right"
            ></v-pagination>
          </div>
      </v-col>
  </v-row>
</div>
     



  
</template>

<script>
import Article from './Article.vue'
export default{
  data(){
    return{
  
      postList:[],
      pageSize:5,
      pageNum: 1,
      total: 0,
    }
  },
  mounted(){
    this.getPostList()
  },
    computed: {
    imageHeight() {
      switch (this.$vuetify.breakpoint.name) {
        case "xs":
          return "190";
      }
      return "170";
    },
    imageWidth() {
      switch (this.$vuetify.breakpoint.name) {
        case "xs":
          return "420";
      }
      return "250";
    }
  },

  methods:{
    async getPostList(){
      const {data:res} = await this.$http.get(`/api/post/${this.pageNum}/${this.pageSize}`)
      this.postList = res.data.postList
      this.total = res.data.total
    }
  }

}
</script>

<style scoped>

*{
    font-family: "Helvetica Neue","Helvetica", "Arial" ,"Microsoft Yahei","Hiragino Sans GB","Heiti SC","WenQuanYi Micro Hei", "sans-serif";
}
.articleTitle{
  font-size: 1.8rem; 
  color: #34495e;

}

time, .category{
  font-size: 0.85rem;
  color: rgba(88,110,117,1);
  margin-right: 10px;
}

.category:hover{
  color: #e91e63;
}


.articleDesc{
    margin-top: 10px;
    font-size: 1.08rem;
    line-height: 1.7rem;
    margin-right: 10px;
    word-break:normal;
    color: #34495e;
}

#main>article.test:hover{
  opacity: 0.8;
  transition-delay: .2s;
} 

#main:hover>article.test{
  opacity: .5;
  transition-delay: 2s;
}

article.test{
  transition: all 1s ease-out;
} 

.articleCover{

box-shadow: rgb(38, 57, 77) 0px 20px 30px -10px;
}


</style>

