<template>
<div>
  <a  v-show="showIcon" class="goTop" title="To Top" @click="backToTop">
     <span class="icon-middlefinger"></span>
    <span class="icon-yeah"></span>
  </a>
</div>
</template>
<script>
export default {
  data() {
    return {

      showIcon: false
    };
  },
  mounted(){
    window.addEventListener("scroll", this.handleScroll)
  },
  methods: {
    
    handleScroll(){
       let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
       this.scrollTop = scrollTop;
       if (this.scrollTop > 60) {
           this.showIcon = true;
       } else {
          this.showIcon = false;
       }

    },

    backToTop(){
      let timer = setInterval(() =>{
        let ispeed = Math.floor(-this.scrollTop/5);
        document.documentElement.scrollTop = document.body.scrollTop = this.scrollTop + ispeed;
        if (this.scrollTop === 0){
          clearInterval(timer);
        }
      },16);
    
    }
   
  }
};

</script>
<style scoped>


.icon-middlefinger:before {
  content: "\e901";
}

.icon-middlefinger{
  transition:all 0.2s ease;
  font-size: 100px;
  left: -1px;
}



.icon-yeah{
  font-size: 91px;
  position:absolute;
  bottom: -100px;
  transition: all 0.4ease;
  display: block;
}

.icon-yeah:before {
  content: "\e900";
}

.goTop:hover span.icon-yeah{
  bottom: -0px;
}

.goTop:hover span.icon-middlefinger{
  bottom: -100px;
}

.goTop{
  position: relative;
  left: -100px;
  width: 100px;
  height: 100px;
  overflow: hidden;
  float: right;
}

span{
  color: rgb(39, 39, 39);
  position: absolute;
}
</style>