<template>

  <div style class="userCard">
    <a-card>
      <a-row  :gutter="15"   class="UtilRow" >
        <a-col span="4.5" >
          <a-input-search  allowClear placeholder="input search text" style="width: 200px"  />
        </a-col>
        <a-col span="1.5">
            <a-button type="dashed" shape="circle" @click="showAddDrawer"><a-icon type="user-add" /></a-button>
        </a-col>
      </a-row>
      <a-table rowKey="id" :columns="columns" :pagination="false" :dataSource="userList" >
        <span slot="role" slot-scope="role">{{role == 1? 'Admin': 'Visitor'}} </span>
        <template slot="action" slot-scope="data">
         <div class="actionSlot">
           <a-button type="primary" size="small" shape="circle"  style="margin-right:5px" @click="showEditDrawer(data.id)"><a-icon type="edit"/></a-button>
          <a-button type="danger" size="small" shape="circle"  @click="deleteUser(data.id)" ><a-icon type="delete"/></a-button>
         </div>  
        </template> 
      </a-table>

       <div id="components-pagination-demo-mini">
          <a-pagination size="small" :total="pagination.total" :show-total="total => `Total ${total} items`" @change="pageChange" @showSizeChange="showSizeChange" show-size-changer />
      </div>
    </a-card>
     
     <!-- ADD USER DRAWER -->
     <a-drawer
      title="Create new account"
      :width="600"
      :visible="addUserVisible"
      :body-style="{ paddingBottom: '80px' }"
      @close="onClose"
    >
      <a-form-model :model="userInfo" :rules="userRules" ref="addUserRef" layout="vertical" hide-required-mark>
       <a-row :gutter="16">
         <a-col :span="12">
        <a-form-model-item label ="Username" prop="username">
          <a-input v-model="userInfo.username" placeholder="Please enter username"></a-input>
        </a-form-model-item>
         </a-col>
          <a-col :span="12">
        <a-form-model-item label ="Nickname" prop="nickname">
          <a-input v-model="userInfo.nickname" placeholder="Please enter nickname"></a-input>
        </a-form-model-item>
         </a-col>
        </a-row>
       <a-row :gutter="16">
         <a-col :span="12">
        <a-form-model-item label ="Password" prop="password" has-feedback>
          <a-input-password  v-model="userInfo.password" placeholder="Please enter password"></a-input-password>
        </a-form-model-item>
         </a-col>
          <a-col :span="12">
        <a-form-model-item label ="Confirm Password" prop="checkpassword" has-feedback>
          <a-input-password  v-model="userInfo.checkpassword" placeholder="Please enter password"></a-input-password>
        </a-form-model-item>
         </a-col>
        </a-row>
        <a-row :gutter="16">
         <a-col :span="12">
        <a-form-model-item label ="E-mail" prop="email">
          <a-input v-model="userInfo.email" placeholder="Please enter e-mail"></a-input>
        </a-form-model-item>
         </a-col>
          <a-col :span="6">
        <a-form-model-item label ="Avatar" prop="avatar">
        <!-- upload avatar --> 
                 <a-upload
        accept="image/*"
        :beforeUpload="beforeImageUpload"
        :customRequest="customImageRequest"
      >
        <a-button type="primary" ghost>
          <a-icon type="upload" />Upload
        </a-button>
      </a-upload>
           
        </a-form-model-item>
         </a-col>
         </a-row>       
        <a-row :gutter="16">

           <a-col :span="12">
            <a-form-model-item label="Authority" prop=role>
              <a-select  style="120px" @change="selectRole">
                <a-select-option v-for="item in roleList" :key="item.id" :value="item.id">
                  {{item.name}}
                </a-select-option> 
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Personal Description" prop="description">  
            
              <a-textarea
                 v-model="userInfo.description"
                :rows="5"
                placeholder="Please enter personal description"
              />
            </a-form-model-item>
          </a-col>
         
        </a-row>
      </a-form-model>
      <div
        :style="{
          position: 'absolute',
          right: 0,
          bottom: 0,
          width: '100%',
          borderTop: '1px solid #e9e9e9',
          padding: '10px 16px',
          background: '#fff',
          textAlign: 'right',
          zIndex: 1,
        }"
      >
        <a-button :style="{ marginRight: '8px' }" @click="cancelAdd">
          Cancel
        </a-button>
        <a-button type="primary" @click="addUser">
          Submit
        </a-button>
      </div>
    </a-drawer>



     <!-- EDIT USER DRAWER -->
     <a-drawer
      title="Edit User"
      :width="600"
      :visible="editUserVisible"
      :body-style="{ paddingBottom: '80px' }"
      @close="onClose"
    >
      <a-form-model :model="editUserInfo" :rules="editUserRules" ref="editUserRef" layout="vertical" hide-required-mark>
       <a-row :gutter="16">
         <a-col :span="12">
        <a-form-model-item label ="Username" prop="username">
          <a-input v-model="editUserInfo.username" placeholder="Please enter username"></a-input>
        </a-form-model-item>
         </a-col>
          <a-col :span="12">
        <a-form-model-item label ="Nickname" prop="nickname">
          <a-input v-model="editUserInfo.nickname" placeholder="Please enter nickname"></a-input>
        </a-form-model-item>
         </a-col>
        </a-row>
  
        <a-row :gutter="16">
         <a-col :span="12">
        <a-form-model-item label ="E-mail" prop="email">
          <a-input v-model="editUserInfo.email" placeholder="Please enter e-mail"></a-input>
        </a-form-model-item>
         </a-col>
          <a-col :span="6">
        <a-form-model-item label ="Avatar" prop="avatar">
        <!-- upload avatar --> 
                 <a-upload
        accept="image/*"
        :beforeUpload="beforeImageUpload"
        :customRequest="customImageRequest"
      >
        <a-button type="primary" ghost>
          <a-icon type="upload" />Upload
        </a-button>
      </a-upload>
           
        </a-form-model-item>
         </a-col>
         </a-row>       
        <a-row :gutter="16">

           <a-col :span="12">
            <a-form-model-item label="Assign Authority?" prop=role>
              <a-switch :checked="IsAdmin" @change="editRole"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Personal Description" prop="description">
              <a-textarea
                 v-model="editUserInfo.description"
                :rows="5"
                placeholder="Please enter personal description"
              />
            </a-form-model-item>
          </a-col>
         
        </a-row>
      </a-form-model>
      <div
        :style="{
          position: 'absolute',
          right: 0,
          bottom: 0,
          width: '100%',
          borderTop: '1px solid #e9e9e9',
          padding: '10px 16px',
          background: '#fff',
          textAlign: 'right',
          zIndex: 1,
        }"
      >
        <a-button :style="{ marginRight: '8px' }" @click="cancelEdit">
          Cancel
        </a-button>
        <a-button type="primary" @click="editUser">
          Submit
        </a-button>
      </div>
    </a-drawer>
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
    title: 'USERNAME',
    dataIndex: 'username',
    width: '10%',
    key: 'username',
    
  },
    {
    title: 'NICKNAME',
    dataIndex: 'nickname',
    width: '10%',
    key: 'nickname',

  },
   {
    title: 'E-MAIL',
    dataIndex: 'email',
    width: '10%',
    key: 'email',
  
  },
  {
    title: 'AUTHORITY',
    dataIndex: 'role',
    width: '10%',
    key: 'role',
    scopedSlots: { customRender: 'role'},
  
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
      pagination:{
        current:1,
        total:0,
        pageSize:10,
      },
      roleList:[],
      userList:[],
      columns,
      pageChange:(page, pageSize)=>{
        this.pagination.current = page
        this.pagination.pageSize= pageSize
        this.getUserList()
      },
      showSizeChange:(current, size)=>{
        this.pagination.current = current
        this.pagination.pageSize= size
        this.getUserList()
      },
      addUserVisible: false,
      editUserVisible: false,
      userInfo:{
        id: 0,
        username: '',
        nickname: '',
        password:'',
        checkpass:'',
        email:'',
        role:0,
        description:''
      },
      editUserInfo:{
        id: 0,
        username: '',
        nickname: '',
        email:'',
        role:0,
        description:''
      },
      userRules:{
        username: [
          {
            validator: (rule, value, callback)=>{
              if (this.userInfo.username == ''){
                  callback(new Error('Please enter the username'))
              }
              if([...this.userInfo.username].length < 4 || [...this.userInfo.username].length >12){
                 callback (new Error('The username must be between 4 and 12 characters'))
              }else{
                  callback()
              }
            }, 
          trigger: 'blur',
          }
        ],
       password: [
          {
            validator: (rule, value, callback)=>{
              if (this.userInfo.password == ''){
                  callback(new Error('Please enter the password'))
              }
              if([...this.userInfo.password].length < 6 || [...this.userInfo.password].length >20){
                 callback (new Error('The username must be between 6 and 20 characters'))
              }else{
                  callback()
              }
            }, 
          trigger: 'blur',
          }
        ],
        checkpassword: [
          {
            validator: (rule, value, callback)=>{
              if (this.userInfo.password == ''){
                  callback(new Error('Please enter the password'))
              }
              if(this.userInfo.password !== this.userInfo.checkpassword){
                 callback (new Error('Your confirmation password does not match the new password'))
              }else{
                  callback()
              }
            }, 
          trigger: 'blur',
          }
        ],
        email: [
          { email: true , message: 'Invalid email format', trigger: 'blur' }
        ],
      },
      editUserRules:{
         username: [
          {
            validator: (rule, value, callback)=>{
              if (this.editUserInfo.username == ''){
                  callback(new Error('Please enter the username'))
              }
              if([...this.editUserInfo.username].length < 4 || [...this.editUserInfo.username].length >12){
                 callback (new Error('The username must be between 4 and 12 characters'))
              }else{
                  callback()
              }
            }, 
          trigger: 'blur',
          }
        ],
         email: [
          { email: true , message: 'Invalid email format', trigger: 'blur' }
        ],
      }
    
    }
  },
  created(){
    this.getUserList()
    this.getRoleList()
  },
  computed:{
    IsAdmin: function(){
      if (this.editUserInfo.role ===1 ){
        return true
      }else{
        return false
      }
    }
  },
  methods: {
    // get the users list
    async getUserList(){
      const {data:res} = await this.$http.get(`api/user/${this.pagination.current}/${this.pagination.pageSize}`)
      if(res.code!==200){
        this.$message.error(res.message)
      }
      this.userList = res.data.userList
      this.pagination.total = res.data.total
    
    },

    // delete user
    deleteUser(id){
      this.$confirm({
        title: 'Do you want to delete this user?',
        content: 'When clicked the OK button, this dialog will be closed after 1 second',
        onOk: async () => {
          const {data:res} = await this.$http.delete(`api/user/${id}`)
          if (res.code!==200){
           return this.$message.error(res.message)
          }
          this.$message.success(res.message)
          this.getUserList()
        },
        onCancel: ()=> {
          this.$message.info("Cancel deleting")
        },
      });
   },
   showAddDrawer(){
     this.addUserVisible = true
   },

   async showEditDrawer (id){
       this.editUserVisible = true
       const {data: res} = await this.$http.get(`api/user/${id}`)
      this.editUserInfo = res.data.record
   },

   editUser(){
      this.$refs.editUserRef.validate( async (valid) => {
       if (!valid) return this.$message.error('Parameters do not match, Please re-enter')
       const{data:res} = await this.$http.put(`api/user`, this.editUserInfo)
       if (res.code!==200){
         return this.$message.error(res.message)
       }
      this.$message.success(res.message)
      this.editUserVisible=false
      this.getUserList()
     })
   },

   onClose(){
     this.addUserVisible = false
     this.editUserVisible = false
   },

   async addUser(){
     this.$refs.addUserRef.validate( async (valid) => {
       if (!valid) return this.$message.error('Parameters do not match, Please re-enter')
       const{data:res} = await this.$http.post(`api/user/`, this.userInfo)
       if (res.code!==200){
         return this.$message.error(res.message)
       }
      this.$message.success(res.message)
      this.getUserList()
      this.addUserVisible=false
      this.refs.addUserRef.resetFields()
     })
   },

   cancelAdd(){
     this.$refs.addUserRef.resetFields()
     this.addUserVisible = false
     this.$message.info ('Cancel creating')
   },

  cancelEdit(){
     this.$refs.editUserRef.resetFields()
     this.editUserVisible = false
     this.$message.info ('Cancel editing')
  },

  editRole(checked){
    if(checked){
      this.editUserInfo.role =1
    }else{
      this.editUserInfo.role=2
    }
  },


  selectRole(value){
     this.userInfo.role = parseInt(value)
  },

   beforeImageUpload (file, fileList){
     return new Promise(async(resolve,reject)=>{
       if (!file.type.include('images')){
        this.$message.warning('Please upload the image')
        reject (new Error('Please upload the image'))
        return 
       }
        resolve (newFile)
     })
   },

   async customImageRequest (info){
     const {file} = info
     this.imageUrl = window.URL.createObjectURL(file)
     const formData = new FormData()
     console.log(formData)
     const res = await this.$http.post(`api/oss/file/upload`, 
     {params:
        {'file': file, 
        'module': 'avatar'
        }
      }, 
      {headers:
      {'Content-Type': 'multipart/form-data'}
      });

      if (res.status!==200){
          return this.$message.error(res.data.message)
      }
      this.$message.success(res.data.message)
   },

   async getRoleList(){
      const {data:res} = await this.$http.get('api/role')
      if (res.code!==200){
        return this.$message.error(res.message)
      }
      this.roleList = res.data.roleList

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