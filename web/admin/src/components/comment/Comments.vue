<template>

  <div style class="commentCard">
    <a-card>
      <a-row  :gutter="15" class="UtilRow" >
        <a-col span="4.5" >
          <a-select defaultValue="Please select blogpost" style="width: 200px" @change="handleChange">
          <a-select-option v-for="item in postList" :key="item.id" :value="item.id">
              {{item.title}}
          </a-select-option> 
          </a-select>
        </a-col>
      </a-row>
      <a-table rowKey="id" :columns="columns" :pagination="false" :dataSource="commentList" >
         <!-- <span class="PostImg" slot="coverImg" slot-scope="image">
          <a-avatar shape="square" :size="75" icon="user" :src="image"/>
      
        </span> -->
        <template slot="action" slot-scope="data">
         <div class="actionSlot">
          <a-button type="danger" size="small" shape="circle"  @click="deleteComment(data.id)" ><a-icon type="delete"/></a-button>
         </div>  
        </template>  
      
      </a-table>

       <div id="components-pagination-demo-mini">
          <a-pagination size="small" :total="total" :show-total="total => `Total ${total} items`" @change="pageChange" @showSizeChange="showSizeChange" show-size-changer />
      </div>
    </a-card>
      


     
  </div>
</template>


<script>

const columns = [  
  {
    title: 'INDEX',
    dataIndex: 'index',
    width: '5%',
    key: 'index',
    customRender: (text,record,index) => `${index+1}`,
  },
  {
    title: 'NICKNAME',
    dataIndex: 'nickname',
    width: '7%',
    key: 'nickname',
    
  },
  
  {
    title: 'EMAIL',
    dataIndex: 'email',
    width: '10%',
    key: 'email',

  },
  //  {
  //   title: 'COVER IMAGE',
  //   dataIndex: 'image',
  //   width: '10%',
  //   key: 'image',
  //   scopedSlots: { customRender: 'coverImg'},

  // },
  {
    title: 'CONTENT',
    dataIndex: 'content',
    width: '15%',
    key: 'content',
  
  },
  {
    title: 'POST_TITLE',
    dataIndex: 'postTitle',
    width: '10%',
    key: 'postTitle ',
  
  },
    {
    title: 'CREATE TIME',
    dataIndex: 'createTime',
    width: '10%',
    key: 'createTime',
  
  },
   {
    title: 'ACTION',
    width: '10%',
    key: 'action',
    scopedSlots: { customRender: 'action'},

  },
];

export default {
  data(){
    return{
      total:0,
      pageIndex:1,
      pageSize:10,
      commentList:[],
      postList:[],
      columns,
      pageChange:(page, pageSize)=>{
        this.pageIndex=page;
        this.pageSize = pageSize
        this.getCommentList()
      },
      showSizeChange:(current, size)=>{
        this.pageIndex = current
        this.pageSize= size
        this.getCommentList()
      },
    
    }
  },
  created(){
    this.getCommentList()
    this.getPostList()
  },
  methods: {
    async getPostList(){
      const {data:res} = await this.$http.get(`api/post`)
      console.log(res)
      if(res.code!=200){
        this.$message.error(res.message)
      }
      this.postList = res.data.list
    },
    async getCommentList(){
      const {data:res} = await this.$http.get(`api/comment/${this.pageIndex}/${this.pageSize}`)
      if(res.code!=200){
        this.$message.error(res.message)
      }
      this.commentList = res.data.commentList
      this.total = res.data.total
 
    },


    
    deleteComment(id){
      this.$confirm({
        title: 'Do you want to delete this comment?',
        content: 'When clicked the OK button, this dialog will be closed after 1 second',
        onOk: async () => {
          const {data:res} = await this.$http.delete(`api/comment/${id}`)
          if (res.code!=200){
            return this.$message.error(res.message)
          }
          this.$message.success(res.message)
          this.getCommentList()
        },
        onCancel: ()=> {
          this.$message.info("Cancel deleting")
        },
      });
   },

   handleChange(value){
     this.getCommentByPost(value)
   },

   async getCommentByPost(id){
     const {data:res} = await this.$http.get(`api/comment/${this.pageIndex}/${this.pageSize}/${id}`)
      if(res.code!=200){
        this.$message.error(res.message)
      }
      this.commentList = res.data.commentList
      this.total = res.data.total
   }


  }, 
}
</script>





<style scoped>
.UtilRow{
  display:flex;
  justify-content: flex-end;
}
.ant-pagination{
  margin-top: 20px;
  display:flex;
  justify-content: flex-end;
}

#components-pagination-demo-mini .ant-pagination:not(:last-child) {
  margin-bottom: 24px;
} 


</style>