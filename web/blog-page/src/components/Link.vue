<template>
  <div >
    <v-row class=" d-flex flex-column flex-sm-row" >
      <v-col align="center" cols-3 v-for="item in linkList" :key="item.id">
         <v-avatar
        size="150"
        class="ml-3 mb-3"
       >
        <img :src="item.avatar">  
        </v-avatar> 
        <button class="button" @click="buttonClick(item.url)" >

        {{item.name}}
       </button>
      </v-col>
   
    </v-row>
      <v-row class="mt-10 d-flex flex-column flex-sm-row">
        <v-col cols-7 class="d-flex flex-row">
      <div class="intro">
      友链 LINK
      </div>
      <p>一些个人喜欢的网站链接，方便博主日后访问。博客也欢迎友链的申请。博主会在后台视情况通过申请，然后把链接显示在此页面。</p>
        </v-col>
          <v-col cols-5>
      <div class="title">
        <h3>Apply now</h3>
      </div>
      <div class="applyBox">
        <div class="inner"> 
          <form>
            <div >
              <label>Website name <span> *</span></label>
              
              <input  class="textInput"  v-model="inputForm.name" name="email">
            </div>
             <div >
              <label>Website url<span> *</span></label>
              <input  class="textInput" v-model="inputForm.url" name="url">
            </div>
            <div>
              <label>Avatar<span> *</span></label>
              <input class="textInput" v-model="inputForm.avatar" name="" >
            </div>
            <div>
              <v-btn class="btn white--text" color="#e52c76" medium @click="submitForm">Submit</v-btn>
            </div>
          </form>
        </div>
  
      </div>
      </v-col>
    </v-row>
  </div>
</template>


<script>
export default{
  data(){
    return{
      linkList:{},
      inputForm:{
        name:'',
        url:'',
        avatar:'',
      }
    }
  },
  created(){
    this.getPublishedLink();
  },
  methods:{
    buttonClick(e){
       window.open(e)
    },

    async submitForm(){
      const {data: res} = await this.$http.post(`api/link`,this.inputForm)
      if (res.code!=200){
        return this.$message.error(res.message)
      }
      this.$message.success(res.message)
    },

    async getPublishedLink(){
      const {data: res} = await this.$http.get('api/link/published')
      console.log(res)
      this.linkList = res.data.linkList
    }
  }

}
</script>
<style scoped>


.button{
  background: none;
  color: #34495e;
  border: 2px solid #34495e;
  font-family: "montserrat", sans-serif;
  text-transform: uppercase;
  padding: 5px 5px;
  min-width: 150px;
  margin: 10px;
  cursor: pointer;
  transition: color 0.4s linear;
  position: relative;
  z-index: 1;

}

.button:hover{
  color: #fff;
}

.button::before{
  content:'';
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  width: 100%;
  height: 100%;
  background: #34495e;
  z-index: -1;
  transform: scaleX(0);
  transition: transform 0.5s;
  transform-origin: 0 0;
  transition-timing-function: cubic-bezier(0.5,1.6,0.4,0.7);
}


.button:hover::before{
  transform: scaleX(1);
}

.intro{
  margin-right: 20px;
  writing-mode: vertical-lr;
  font-size: 40px;
  text-decoration: overline;
  color: #34495e;
}

p{
  font-size: 17px;
  line-height: 28px;
  color: #34495e;
}

.title{
  color: #34495e;
  margin-left: 1.6em;
}

.applyBox{
  background-color: #fff;

}

.textInput{
  border: 2px solid #ccc;
  background: #fff;
  width: 20em;
  height: 2em;
  color: #272727;
  padding: 1em;
  border-radius: 5px;
}


label span{
  color:#e52c76
}

/* .textInput:hover{
  border-color: #e91e63;
} */

.textInput:focus{
  outline: 0;
  box-shadow: inset 0 1px 1px rgb(233 30 99 / 8%), 0 0 8px rgb(233 30 99 / 60%);
}

input{
  caret-color: #e91e63;
}

.applyBox label{
  display: block;
  padding-bottom: 0.2em;
}
.inner{
  color: #34495e;
  position: relative;
  padding: 1em 2em;
  /* backdrop-filter: blur(6px) saturate(.3); */
}

form div{
  margin: 2em 0 2em;
}


</style>