<template>


  <div>
    <div class="drawer">
      <div class="setbox" :class="{show: isshow}">
        <div class="container">
          <div class="comment" v-for="item in commentList" :key="item.id">
            <div class="parent">
              <img class="avatar" :src="item.avatar" width="60" height="60"/>
                <div class="right">
                  <div class="name">{{item.nickname}}   
                      <v-icon v-show="item.isAdmin" color="#e91e63" size="17px">{{'mdi-feather'}}</v-icon>
                      
                  </div>
                    <div class="date">{{item.createTime| dateformat('YYYY/MM/DD')}}   
                 </div>
                  </div>
                </div>

        <p class="content">{{item.content}}</p>
        <div class="control">
        <!-- <span class="like" >
          <v-icon class="mr-1" color="#b3b3cc" size="16">{{'fa-heart'}}</v-icon>
          <span class="like-num">25</span>
        </span> -->
        <span class="comment-reply" @click="replyComment(item)">
          <v-icon class="iconfont icon-comment" color="#b3b3cc">{{'mdi-reply'}}</v-icon>
          Reply
        </span>
      </div>
      <div class="reply" v-for="reply in item.childComment" :key="reply.id">
        <div class="item" >
          <div class="reply-content" >
            <span class="from-name">{{reply.nickname}} </span><span>: </span>
            <span class="to-name">@{{reply.parentNickname}}</span>
                  
          </div>
              <p class="reply-content">{{reply.content}}</p>
          <div class="reply-bottom">
            <span>{{reply.createTime| dateformat('YYYY/MM/DD')}}</span>
            <span class="reply-text" >
              <span @click="replyComment(reply)" style="">| Reply</span>
            </span>
          </div>
        </div>
        
        
      </div>
    </div>
  </div>

      </div>
       <div class="commentbox" :class="{commentshow: isshow}">
          <button class="off mt-2"  @click="close">
            <v-icon  color="#b3b3cc" size="33">{{'fa-times'}}</v-icon>
          </button>
          <div class="write-reply">
         <v-icon class="edit" color="#b3b3cc"  size="24">{{'mdi-feather'}}</v-icon>
          <span class="add-comment">Leave a comment</span>
        </div>
          <transition name="fade">
            <div class="input-wrapper" >

       <v-form
         ref="form"
        v-model="valid"
        lazy-validation
      >
          <v-textarea class="mr-4" outlined :rules="commentRules"  :placeholder="placeholder" v-model="comment.content"></v-textarea>
  
     
          <v-row class="mr-1">
        <v-col
          cols="12"
          md="6"
        >
          <v-text-field
            v-model="comment.nickname"
            :rules="nameRules"
            :counter="10"
            label="Nickname"
            required
          ></v-text-field>
        </v-col>

        <v-col
          cols="12"
          md="6"
        >
          <v-text-field
            v-model="comment.email"
            :rules="emailRules"
            label="E-mail"
            required
          ></v-text-field>
        </v-col>
        
        </v-row>
       </v-form>
          <div class="btn-control mt-7" > 
              <v-btn class="cancel mb-1" outlined color="#909399" small @click="reset">Cancel</v-btn>
              <v-btn class="confirm mr-2 mb-1"  outlined color="#909399" small @click="saveComment">Send</v-btn>
          </div>
           </div>
          </transition>
      </div>

    
    </div>

 
    <div class="pt-7 mb-4 text-h4 font-weight-bold">{{ postInfo.title }}</div>
      <div class>
        <span class="mr-4" style="font-size:14px">{{ postInfo.createTime| dateformat('YYYY年MM月DD日')}}</span>
        <span style="font-size:14px"> {{postInfo.view}} views</span>
      
      </div>
      

     <div class="mt-4">
        <markdown-it-vue-light class="md-body" :content="postInfo.content" v-viewer="{movable: false}" />
     </div>

     <div class="nav">
       <div class="nav-previous" v-if="prevArticle !== null">
          <a class="nav-articleTitle"  :href="prevArticle.id">
          <v-icon class="prevIcon" color=" #9499a0" size="27">{{'fa-arrow-circle-left'}}</v-icon>
          {{prevArticle.title}}</a>
       </div>
       <div class="nav-next" v-if="nextArticle !== null">
          <a class="nav-articleTitle" :href="nextArticle.id">
          <v-icon color=" #9499a0" size="27">{{'fa-arrow-circle-right'}}</v-icon>
          {{nextArticle.title}} </a>
       </div>
      
     </div>
     

      <div> 
      <div @click="display" style="position: fixed; bottom:80px; right:0px; z-index:1; padding-right:10px">
          <v-badge
            :value="hover"
             color="#ff4d4d"
             :content="commentNum" 
             style="opacity:0.8"
             overlap
            left
            transition="slide-x-transition"
             >
            <v-hover v-model="hover">
              <button>
              <v-icon color="#b3b3cc" size="40">{{'fa-comments'}}</v-icon>
              </button>
            </v-hover>
          </v-badge>
      </div>

    </div> 
  </div>

</template>
<script>

import Drawer from 'vue-simple-drawer'

export default{
  components: { Drawer },
  props:['id'],
  data(){
    return{
      parentId: '',
      placeholder: "。。。",
      commentList:{},
      user:{},
      postInfo:{
        content:''
      },
      comment:{
        parentId:'',
        content:'',
        nickname:'',
        email:''
      },
      isshow: false,
      status: 1,
      commentNum:0,
      hover: false,
      entity:{},
      nextArticle:{},
      prevArticle:{},
      valid: false,
   
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 10 || 'Name must be less than 10 characters',
      ],

      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid',
      ],
      commentRules:[
           v => !!v || 'Comment is required',
      ]
    }
  },
  mounted(){
    this.getPostInfo()
    this.loadComment()
    this.getNextArticle()
    this.getPrevArticle()
  },

  methods:{
    async getPostInfo(){
      const {data: res} = await this.$http.get(`api/post/${this.id}`)
      this.postInfo= res.data.post
      window.sessionStorage.setItem('title', this.postInfo.title)
    },
  
    async loadComment(){
       const {data: res} = await this.$http.get(`api/comment/foreign/${this.id}`)
       this.commentList = res.data.data.commentList
       this.commentNum = res.data.data.total
  
    },
    display(){
      this.isshow =!this.isshow
    },
    close(){
      this.isshow = false
    },
    
 
    reset(){
      this.$refs.form.reset()
      this.placeholder =""
    },
    replyComment(reply){
      this.placeholder = "@" + reply.nickname + " " 
      this.comment.parentId = reply.id;
      console.log(this.comment.parentId);
    },

    
   async saveComment(){
        if(!this.$refs.form.validate()) return this.$message.error('Please resend the comment')
        const {data: res } = await this.$http.post('/api/comment', {
        parentId: this.comment.parentId,
        foreignId : parseInt(this.id),
        content: this.comment.content,
        nickname: this.comment.nickname,
        email: this.comment.email
        })
        if (res.code !== 200) return this.$message.error(res.message)
        this.$message.success('Thank you!')
        this.$router.go(0)
       
    },
    
    async getNextArticle(){
      const nid  = parseInt(this.id);
      const {data: res} = await this.$http.get(`/api/post/next/${nid}`)
      if (res.code === 200) this.nextArticle = res.data.post
 
    },
    
    async getPrevArticle(){
      const pid = parseInt(this.id)
      const {data: res} = await this.$http.get(`/api/post/prev/${pid}`)
      if (res.code === 200) this.prevArticle = res.data.post
    }
  }, 
}
</script>

<style scoped lang="scss">


  @import "../assets/css/demo.scss";
  

  .container {
    padding: 0 10px;
    box-sizing: border-box;
    .comment {
      display: flex;
      flex-direction: column;
      padding: 10px;
      border-bottom: 1px solid $border-fourth;
      .parent {
        display: flex;
        align-items: center;
        .avatar {
          border-radius: 50%;
        }
        .right {
          display: flex;
          flex-direction: column;
          margin-left: 10px;
          .name {
            font-size: 16px;
            color: $text-main;
            margin-bottom: 5px;
            font-weight: 500;
          }
          .date {
            font-size: 12px;
            color: $text-minor;
          }
        }
      }
      .content {
        font-size: 15.5px;
        color: $text-main;
        line-height: 20px;
        padding: 10px 0;
      }
      .control {
        display: flex;
        align-items: center;
        font-size: 14px;
        color: $text-minor;
        .like {
          display: flex;
          align-items: center;
          margin-right: 20px;
          cursor: pointer;
          &.active, &:hover {
            color: $color-main;
          }
          .icon {
            font-size: 14px;
            margin-right: 5px;
          }
        }
        .comment-reply {
          display: flex;
          align-items: center;
          cursor: pointer;
          &:hover {
            color: $text-333;
          }
          .iconfont {
            font-size: 24px;
            margin-right: 5px;
          }
        }

      }
      .reply {
        margin: 10px 0;
        border-left: 2px solid $border-first;
        .item {
          margin: 0 10px;
          padding: 10px 0;
          border-bottom: 1px dashed $border-third;
          .reply-content {
            display: flex;
            align-items: center;
            font-size: 14px;
            color: $text-main;
            .from-name {
              color: $color-main;
            }
            .to-name {
              color: $color-main;
              margin-left: 5px;
              margin-right: 5px;
            }
          }
          .reply-bottom {
            display: flex;
            align-items: center;
            margin-top: 6px;
            font-size: 12px;
            color: $text-minor;
            .reply-text {
              display: flex;
              align-items: center;
              margin-left: 10px;
              cursor: pointer;
              &:hover {
                color: $text-333;
              }
              .icon-comment {
                margin-right: 5px;
              }
            }
          }
        }
      }
    }
  }
  .write-reply {
          display: flex;
          align-items: center;
          font-size: 14px;
          color: $text-minor;
          padding: 10px;
          cursor: pointer;
          &:hover {
            color: $text-main;
          }
          .v-icon-edit {
            margin-right: 5px;
          }
        }
        .fade-enter-active, fade-leave-active {
          transition: opacity 0.5s;
        }
        .fade-enter, .fade-leave-to {
          opacity: 0;
        }
        .input-wrapper {
          padding: 10px;         
          .btn-control {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            padding-top: 10px;
            padding-right: 10px;
            .cancel {
              font-size: 12px;
              color: $text-normal;
              margin-right: 20px;
              cursor: pointer;
              &:hover {
                color: $text-333;
              }
            }
            .confirm {
              font-size: 12px;
            }
          }
        }

.drawer{
  width: 100%;
  display: flex;
  display: -webkit-flex;
  flex-direction: column;
  .commentbox{
    position: fixed;
    z-index: 1100;
    top: 0px;
    bottom:0px;
    width:18.5%;
    height: 60%;
    background: #FFFFFF;
    border-left: 1px solid #CFD8DC !important;
    box-shadow: 0px 3px 12px rgba (0,0,0,0,12);
    -webkit-transition: all 1s ease;
    transition: opacity 0.3s;
    -webkit-transition: opacity 0.3s;
    transition: all 1s ease;
    right: -42%;
    padding: 0px 0px 0px 20px
  }

  .setbox{
    overflow-y: scroll;
    position: fixed;
    z-index: 1100;
    top: 0px;
    bottom:0px;
    width:22%;
    height: 100%;
    background: #FFFFFF;
    border-left: 1px solid #CFD8DC !important;
    box-shadow: 0px 3px 12px rgba (0,0,0,0,12);
    -webkit-transition: all 1s ease;
    transition: opacity 0.3s;
    -webkit-transition: opacity 0.3s;
    opacity: 1;
    transition: all 1s ease;
    right: -32%;
    padding: 0px 0px 0px 20px
  }

  .show{
    right: 0;
  }
  .commentshow{
    right: 22%;
  }


}

.nav{
  margin-top:20px;
  height: 150px;
}

.nav-previous, .nav-next{
  padding-top: 15px;

  position: relative;
}

.nav-articleTitle{
  color: #484b4e;
  display: block;
  font-size: 1.22em;
  font-weight: 700;
  line-height: 135%;
  margin-top: 11px;
  min-height: 20px;
  padding: 1px 0 1px 1px;
}

a{
  text-decoration: none;
}

a:hover{
  color:#e91e63;
}

@media (max-width: 768px) {
.drawer{
  width: 100%;
  display: flex;
  display: -webkit-flex;
  flex-direction: column;
  .commentbox{
    position: fixed;
    z-index: 1100;
    top: 0px;
    bottom:0px;
    width:18.5%;
    height: 60%;
    background: #FFFFFF;
    border-left: 1px solid #CFD8DC !important;
    box-shadow: 0px 3px 12px rgba (0,0,0,0,12);
    -webkit-transition: all 1s ease;
    transition:      0.3s;
    -webkit-transition: opacity 0.3s;
    transition: all 1s ease;
    right: -42%;
    padding: 0px 0px 0px 20px
  }

  .setbox{
    overflow-y: scroll;
    position: fixed;
    z-index: 1100;
    top: 0px;
    bottom:0px;
    width:22%;
    height: 100%;
    background: #FFFFFF;
    border-left: 1px solid #CFD8DC !important;
    box-shadow: 0px 3px 12px rgba (0,0,0,0,12);
    -webkit-transition: all 1s ease;
    transition: opacity 0.3s;
    -webkit-transition: opacity 0.3s;
    opacity: 1;
    transition: all 1s ease;
    right: -32%;
    padding: 0px 0px 0px 20px
  }
}

}
  
 
</style>
