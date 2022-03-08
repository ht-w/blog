<template>

  <div style class="userCard">
    <a-card>
      <a-row  :gutter="15"   class="UtilRow" >
        <a-col span="4.5" >
          <a-select defaultValue="Please select category" style="width: 200px" @change="handleChange">
          <a-select-option v-for="item in categoryList" :key="item.id" :value="item.id">
              {{item.name}}
          </a-select-option> 
          </a-select>
        </a-col>
        <a-col span="1.5">
            <a-button type="dashed" shape="circle"  @click="$router.push('/write')"><a-icon type="plus-circle" /></a-button>
        </a-col>
      </a-row>
      <a-table rowKey="id" :columns="columns" :pagination="false" :dataSource="postList" >
         <span class="PostImg" slot="coverImg" slot-scope="image">
          <a-avatar shape="square" :size="75" icon="user" :src="image"/>
      
        </span>
        <template slot="action" slot-scope="data">
         <div class="actionSlot">
           <a-button type="primary" size="small" shape="circle"  style="margin-right:5px" @click="$router.push(`/addpost/${data.id}`)"><a-icon type="edit"/></a-button>
          <a-button type="danger" size="small" shape="circle"  @click="deletePost(data.id)" ><a-icon type="delete"/></a-button>
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
    width: '7%',
    key: 'index',
    customRender: (text,record,index) => `${index+1}`,
  },
  {
    title: 'TITILE',
    dataIndex: 'title',
    width: '10%',
    key: 'title',
    
  },
  {
    title: 'CATEGORY',
    dataIndex: 'categoryName',
    width: '10%',
    key: 'categoryName',

  },
   {
    title: 'COVER IMAGE',
    dataIndex: 'image',
    width: '10%',
    key: 'image',
    scopedSlots: { customRender: 'coverImg'},
  
  },
  {
    title: 'CREATE TIME',
    dataIndex: 'createTime',
    width: '10%',
    key: 'createTime',
  
  },
    {
    title: 'UPDATE TIME',
    dataIndex: 'updateTime',
    width: '10%',
    key: 'updateTime',
  
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
      postList:[],
      categoryList:[],
      columns,
      pageChange:(page, pageSize)=>{
        this.pageIndex=page;
        this.pageSize = pageSize
        this.getPostList()
      },
      showSizeChange:(current, size)=>{
        this.pageIndex = current
        this.pageSize= size
        this.getPostList()
      },
    
    }
  },
  created(){
    this.getPostList()
    this.getCategoryList()
  },
  methods: {
    // get the users list
    async getPostList(){
      const {data:res} = await this.$http.get(`api/post/${this.pageIndex}/${this.pageSize}`)
      console.log(res)
      if(res.code!=200){
        this.$message.error(res.message)
      }
      this.postList = res.data.postList
      this.total = res.data.total
    
    },
    async getCategoryList(){
      const {data:res} = await this.$http.get(`api/category`)
      if(res.code!=200){
        this.$message.error(res.message)
      }
      this.categoryList = res.data.list
 
    },


    

    // delete user
    deletePost(id){
      this.$confirm({
        title: 'Do you want to delete this post?',
        content: 'When clicked the OK button, this dialog will be closed after 1 second',
        onOk: async () => {
          const {data:res} = await this.$http.delete(`api/post/${id}`)
          console.log(res)
          if (res.code!=200){
            return this.$message.error(res.message)
          }
          this.$message.success(res.message)
          this.getPostList()
        },
        onCancel: ()=> {
          this.$message.info("Cancel deleting")
        },
      });
   },

   handleChange(value){
     this.getPostByCategory(value)
   },

   async getPostByCategory(id){
     const {data:res} = await this.$http.get(`api/post/${this.pageIndex}/${this.pageSize}/${id}`)
      if(res.code!=200){
        this.$message.error(res.message)
      }
      this.postList = res.data.postList
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