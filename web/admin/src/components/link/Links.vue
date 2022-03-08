<template>

  <div style class="categoryCard">
    <a-card>

         <a-row  :gutter="15"   class="UtilRow" >

        <a-col span="1.5">
            <a-button type="dashed" shape="circle"  style="margin-right:5px" @click="showAddModal"><a-icon type="plus"/></a-button>
        </a-col>
      </a-row>
     
      <a-table rowKey="id" :columns="columns" :pagination="false" :dataSource="linkList" >
          <span class="avatar" slot="avatar" slot-scope="avatar">
          <a-avatar shape="square" :size="75" :src="avatar"/>
          </span>
            <template  slot="status" slot-scope="status,data">
          <a-switch :checked="Boolean(data.status)" @change="editStatus($event, data)"/>
          </template>
         
        <template slot="action" slot-scope="data">
         <div class="actionSlot">
           <a-button type="primary" size="small" shape="circle"  style="margin-right:5px" @click="showEditModal(data.id)"><a-icon type="edit"/></a-button>
          <a-button type="danger" size="small" shape="circle"  @click="deleteLink(data.id)" ><a-icon type="delete"/></a-button>
         </div>  
        </template> 
      </a-table>

       <div id="components-pagination-demo-mini">
          <a-pagination size="small" :total="total" :show-total="total => `Total ${total} items`" @change="pageChange" @showSizeChange="showSizeChange" show-size-changer />
      </div>
    </a-card>
  
<!-- // -->   
      <a-modal
        title="Add new link"
        :visible = "addLinkVisible"
        @ok = "confirmAdd"
        @cancel = "cancelAdd"
      >

      <a-form-model :model="linkInfo" :rules="addLinkRules" ref="addLinkRef" layout="vertical" hide-required-mark>
          <a-form-model-item label ="Name" prop="name">
          <a-input v-model="linkInfo.name" placeholder="Please enter the name"></a-input>
        </a-form-model-item>
         <a-form-model-item label ="Url" prop="url">
          <a-input v-model="linkInfo.url" placeholder="Please enter the url"></a-input>
        </a-form-model-item>
           <a-form-model-item label ="Avatar url" prop="avatar">
          <a-input v-model="linkInfo.avatar" placeholder="Please enter the avatar url"></a-input>
        </a-form-model-item>
        </a-form-model>
           
      </a-modal>


<!-- //EDIT -->
       <a-modal
        title="Update link"
        :visible = "updateLinkVisible"
        @ok = "confirmUpdate"
        @cancel = "cancelUpdate"
      >

      <a-form-model :model="updateLinkInfo" :rules="updateLinkRules" ref="updateLinkRef" layout="vertical" hide-required-mark>
        <a-form-model-item label ="Name" prop="name">
          <a-input v-model="updateLinkInfo.name" placeholder="Please enter the name"></a-input>
        </a-form-model-item>
         <a-form-model-item label ="Url" prop="url">
          <a-input v-model="updateLinkInfo.url" placeholder="Please enter the url"></a-input>
        </a-form-model-item>
           <a-form-model-item label ="Avatar url" prop="avatar">
          <a-input v-model="updateLinkInfo.avatar" placeholder="Please enter the avatar url"></a-input>
        </a-form-model-item>
        </a-form-model>
           
      </a-modal>
  </div>
</template>


<script>


const columns = [  
  {
    title: 'INDEX',
    dataIndex: 'index',
    width: '10%',
    key: 'index',
    customRender: (text,record,index) => `${index+1}`,
  },
  {
    title: 'NAME',
    dataIndex: 'name',
    width: '10%',
    key: 'name',
    
  },
  {
    title: 'URL',
    dataIndex: 'url',
    width: '20%',
    key: 'url',
  },
   {
    title: 'AVATAR',
    dataIndex: 'avatar',
    width: '10%',
    key: 'avatar',
    scopedSlots: { customRender: 'avatar'},
  },
  {
    title: 'PUBLISH ?',
    dataIndex: 'status',
    width: '10%',
    key: 'status',
    scopedSlots: { customRender: 'status'},
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
    width: '20%',
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
      linkList: [],
      addLinkVisible: false,
      updateLinkVisible: false,
      columns,
      pageChange:(page, pageSize)=>{
        this.pageIndex=page;
        this.pageSize = pageSize
        this.getlinkList()
      },
      showSizeChange:(current, size)=>{
        this.pageIndex = current
        this.pageSize= size
        this.getlinkList()
      },

     
      linkInfo:{
        name:'',
        url:'',
        avatar:'',
        status: 0,
      },
      updateLinkInfo:{
        name:'',
        url:'',
        avatar:'',
        status: 0,
      },
      addLinkRules:{
         name: [
          {
            validator: (rule, value, callback)=>{
              if (this.linkInfo.name === ''){
                  callback(new Error('Please enter the website name'))
              }else{
                callback()
              }
            }, 
          trigger: 'blur',
          },
        ],
        url: [
          {
            validator: (rule, value, callback)=>{
              if (this.linkInfo.url === ''){
                  callback(new Error('Please enter the url'))
              }else{
                callback()
              }
            }, 
          trigger: 'blur',
          },
        ],

        avatar:[
          {
            validator: (rule, value, callback)=>{
              if (this.linkInfo.avatar === ''){
                  callback(new Error('Please enter the avatar url'))
              }else{
                callback()
              }
            }, 
          trigger: 'blur',
          }
        ],
      },

      updateLinkRules:{
         name: [
          {
            validator: (rule, value, callback)=>{
              if (this.updateLinkInfo.name === ''){
                  callback(new Error('Please enter the website name'))
              }else{
                callback()
              }
            }, 
          trigger: 'blur',
          },
        ],
        url: [
          {
            validator: (rule, value, callback)=>{
              if (this.updateLinkInfo.url === ''){
                  callback(new Error('Please enter the url'))
              }else{
                callback()
              }
            }, 
          trigger: 'blur',
          },
        ],

        avatar:[
          {
            validator: (rule, value, callback)=>{
              if (this.updateLinkInfo.avatar === ''){
                  callback(new Error('Please enter the avatar url'))
              }else{
                callback()
              }
            }, 
          trigger: 'blur',
          }
        ],
        
      }
    
    } 
  },
  created(){
    this.getlinkList()
  },


  methods: {
    // get the categories list
    async getlinkList(){
      const {data:res} = await this.$http.get(`api/link/${this.pageIndex}/${this.pageSize}`)
      console.log(res)
      if(res.code!==200){
        this.$message.error(res.message)
      }
      this.linkList = res.data.linkList
      this.total = res.data.total
    },


    deleteLink(id){
      this.$confirm({
        title: 'Do you want to delete this link?',
        content: 'When clicked the OK button, this dialog will be closed after 1 second',
        onOk: async () => {
          const {data:res} = await this.$http.delete(`api/link/${id}`)
          if (res.code!==200){
            return this.$message.error(res.message)
          }
          this.$message.success(res.message)
          this.getlinkList()
        },
        onCancel: ()=> {
          this.$message.info("Cancel deleting")
        },
      });
   },
  showAddModal(){
     this.addLinkVisible = true
   },

   async showEditModal (id){
       this.updateLinkVisible = true
       const {data: res} = await this.$http.get(`api/link/${id}`)
      this.updateLinkInfo = res.data.link
   },

   confirmUpdate(){
      this.$refs.updateLinkRef.validate( async (valid) => {
       if (!valid) return this.$message.error('Parameters do not match, Please re-enter')
       const{data:res} = await this.$http.put(`api/link`, this.updateLinkInfo)
       if (res.code!=200){
         return this.$message.error(res.message)
       }
      this.$message.success(res.message)
      this.updateLinkVisible=false
      this.getlinkList()
     })
   },

   onClose(){
     this.addLinkVisible = false
     this.updateLinkVisible = false
   },

   confirmAdd(){
     this.$refs.addLinkRef.validate( async (valid) => {
       if (!valid) return this.$message.error('Parameters do not match, Please re-enter')
       const{data:res} = await this.$http.post(`api/link/admin`, this.linkInfo)
       console.log(res)
       if (res.code!==200){
         return this.$message.error(res.message)
       }
      this.$message.success(res.message)
      this.addLinkVisible=false
      this.getlinkList()
     })
   },

   cancelAdd(){
     this.$refs.addLinkRef.resetFields()
     this.addLinkVisible = false
     this.$message.info ('Cancel creating')
   },

  cancelUpdate(){
     this.$refs.updateLinkRef.resetFields()
     this.updateLinkVisible = false
     this.$message.info ('Cancel editing')
  },

  editStatus(e, record) {
    this.$confirm({
      title: "Do you want to edit the status?",
      okText: "Yes",
      cancelText: "Cancel",
      onOk: async () => {
        let values;
 
        if(e){
          values = {status:1}
        }else{
          values = {status:0}
        }
         
        const {data: res} = await this.$http.put(`api/link/${record.id}`, values)
        if (res.code!==200){
          return this.$message.error(res.message)
        }
        this.$message.success(res.message)
        this.getlinkList()   
      },
      onCancel() {},
    });
  },
  }
  
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