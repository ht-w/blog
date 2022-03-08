<!--<template>
  <div class="about">
    <h1>关于博客</h1>
    <p>开发这个个人博客主要目的为了以写作的形式满足自身的表达欲，记录自己的思想。除此之外，在写博客的过程中对所学的知识进行重构并深入理解，且锻炼写作与语言表达能力。</p>
     <h1>关于我</h1>
    <p>INFP，向往散漫的生活，喜欢阅读，喜欢运动，喜欢思考，喜欢美，喜欢幻想，喜欢很多东西，却好像什么都做不好，缺乏实现目的的决心与意志力。
      <br>想要保持精神上的高贵和特立独行，却又无法无视社会和他人对自己的看法，个人的人生追求是做到知行合一。
    </p>
    <h1>联系方式</h1>
    <li  v-for="(icon,index) in icons" :key="index">
      <v-btn
          class="mx-2"
          icon
          :title="icon.title"
          target="_blank"
          :href="icon.href"
        >
          <v-icon size="30px" color="#4c4948">{{ icon.name }}</v-icon>
        </v-btn>
        <span>{{icon.word}}</span>
    </li>
    <h1 class="english">Open to work</h1>
    <p>I am seeking for Back-end Software Engineer working opportunities. Feel free to check out my <a>Résumé</a> for more information.
    </p>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dialog: false,
      icons: [
        {
          name: "mdi-email",
          href: "mailto:hongtignwong828@gmail.com",
          title: "Mail", 
          word: "hongtingwong828@gmail.com"
        },
        {
          name: "mdi-github",
          href: "https://github.com/ht-w",
          title: "Github" ,
          word: "https://github.com/ht-w"
        },
      ]
    };
  },
  methods: {
   
  }
};
</script>

<style scoped>

.about{
  margin-left:10px;
}


h1{
  margin-bottom: 15px;
  margin-top: 15px;
  font-size: 30px;
  color: #34495e;
}

p{
  color: #34495e;
  font-size: 1.1rem;
}

span{
    color: #34495e;
}

.english{
  font-size: 25px;
}

</style>
!-->


<template>
  <div>
  <v-row class="welcome"> 
    <div>
      HELLO
    </div>
    <div class="dot">.</div>
  </v-row>
  <v-row>
    <p data-end="。">I'm Hong Ting Wong, a self-taught programmer from Malaysia. This's my blog, a place for me to write about my life and  what I've learnt along the way. I hope you like it and enjoy my posts. 
     <br> I'm seeking for Back-end Software Engineer working opportunities. Feel free to check out my <a href="https://docs.google.com/document/d/1Ltr7ae6ujY-ZY5g7RTVlYm3D_9ydorfa6uE9WT5-p1k/edit#heading=h.m29ks73wqe9o">Résumé</a> for more information.
      <br>黄康庭，INFP, 向往散漫的生活，喜欢阅读，喜欢运动，喜欢思考，喜欢美，喜欢幻想，喜欢很多东西，却好像什么都做不好，缺乏实现目的的决心与意志力。
      <br>想要保持精神上的高贵和特立独行，却又无法无视社会和他人对自己的看法，个人的人生追求是做到知行合一</p>
  </v-row>
  <v-row> 
    <div class="contact">  
     <v-btn
      v-for="(icon,index) in icons"
      :key="index"
      class="ml-5 "
      icon
      :title="icon.title"
      target="_blank"
      :href="icon.href"
      @click="showDialog(icon)"
      >
      <v-icon size="42px" color="pink">{{ icon.name }}</v-icon>
    </v-btn>
    </div>
  </v-row>
    
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
      icons: [
        {
          name: "mdi-email",
          href: "mailto:hongtignwong828@gmail.com",
          title: "Mail", 
          word: "hongtingwong828@gmail.com"
        },
        {
          name: "mdi-github",
          href: "https://github.com/ht-w",
          title: "Github" ,
          word: "https://github.com/ht-w"
        },
      ]
    };
  },
  methods: {
   
  }
};
</script>


<style scoped>

.welcome{
  margin-top: 69px;
  font-size: 140px;
  margin-left: 20px ;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}
.dot{
  font-size: 140px;
  color: #e91e63;
}

p{
     color:#34495e;
  margin-left: 38px;
  font-size: 1.22em;
  line-height: 1.6;
  font-weight: 370;
  font-family: 'Rubik', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

p a{
  color: #e91e63;
  text-decoration: none;
}

p::after {
    content: attr(data-end) ;
    color:  #e91e63;
}

.contact{
  position:absolute; 
  bottom: 0;
  left: 76%;
  right: 0;
  height: 100px; 
}

@media screen and (max-width: 768px){
.contact{
  margin-top: 20px;
  position: relative;
  bottom: 0;
  right: 0;
  height: 100px; 
}
}
</style>

