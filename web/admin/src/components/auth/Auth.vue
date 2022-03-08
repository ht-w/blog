<template>

  <div style class="categoryCard">
    <a-card>

         <a-row  :gutter="15"   class="UtilRow" >

        <a-col span="1.5">
            <a-button type="dashed" shape="circle"  style="margin-right:5px" @click="showAddModal"><a-icon type="plus"/></a-button>
        </a-col>
      </a-row>
     
      <a-table rowKey="id" :columns="columns" :pagination="false" :dataSource="resourceList" >
         <template slot-scope ="roleId" slot="roleId">
         <a-tag :color="roleId =='1' ?'blue':'yellow'">
           {{roleId == 1? 'Admin': ''}}
        </a-tag>  
        </template>
        <template slot-scope ="status" slot="status">
         <a-tag :color="status ==1 ?'red':'green'">
          {{status == 1? '需要权限': '无需权限'}}
        </a-tag>  
        </template>
        <template slot="action" slot-scope="data">
         <div class="actionSlot">
           <a-button type="primary" size="small" shape="circle"  style="margin-right:5px" @click="showEditModal(data.id)"><a-icon type="edit"/></a-button>
          <a-button type="danger" size="small" shape="circle"  @click="deleteResource(data.id)" ><a-icon type="delete"/></a-button>
         </div>  
        </template> 
      </a-table>

       <div id="components-pagination-demo-mini">
          <a-pagination size="small" :total="total" :show-total="total => `Total ${total} items`" @change="pageChange" @showSizeChange="showSizeChange" show-size-changer />
      </div>
    </a-card>
  
<!-- // -->   
      <a-modal
        title="Grant Uri Permission"
        :visible = "addResourceVisible"
        @ok = "confirmAdd"
        @cancel = "cancelAdd"
      >

      <a-form-model :model="resourceInfo" :rules="addResourceRules" ref="addResourceRef" layout="vertical" hide-required-mark>
        <a-form-model-item label ="URI" prop="uri">
          <a-input v-model="resourceInfo.uri" placeholder="Please enter the uri"></a-input>
        </a-form-model-item> 
        <a-form-model-item label ="Request method" prop="method">
          <a-select v-model="resourceInfo.method">
            <a-select-option v-for="item in methods" :key="item" :value="item">
              {{item}}
            </a-select-option> 
          </a-select>
        </a-form-model-item>
           <a-form-model-item label ="Authorization required" prop="isRequired">
            <a-radio-group   v-model="resourceInfo.status" name="radioGroup" default-value="1" @change = "radioChange">
             <a-radio :value="1">
              Yes
              </a-radio>
              <a-radio :value="9">
              No
            </a-radio>
        </a-radio-group>

        </a-form-model-item>
         <a-form-model-item label ="Role" >
          <a-select :disabled="isDisabled" v-model="resourceInfo.roleId">
            <a-select-option  v-for="item in roleList" :key="item.id" :value="item.id">
              {{item.name}}
            </a-select-option> 
          </a-select>
        
        </a-form-model-item>
      
      </a-form-model>
           
      </a-modal>


<!-- //EDIT -->
       <a-modal
        title="Edit uri permission"
        :visible = "editResourceVisible"
        @ok = "confirmEdit"
        @cancel = "cancelEdit"
      >

      <a-form-model :model="editResourceInfo" :rules="editResourceRules" ref="editResourceRef" layout="vertical" hide-required-mark>
         <a-form-model-item label ="URI" prop="uri">
          <a-input v-model="editResourceInfo.uri" placeholder="Please enter the uri"></a-input>
        </a-form-model-item> 
        <a-form-model-item label ="Request method" prop="method">
          <a-select v-model="editResourceInfo.method">
            <a-select-option v-for="item in methods" :key="item" :value="item">
              {{item}}
            </a-select-option> 
          </a-select>
        </a-form-model-item>
           <a-form-model-item label ="Authorization required" prop="isRequired">
            <a-radio-group  v-model="editResourceInfo.status" name="radioGroup" default-value="1" @change = "radioChange">
             <a-radio :value="1" >
              Yes
              </a-radio>
              <a-radio :value="9">
              No
            </a-radio>
        </a-radio-group>

        </a-form-model-item>
         <a-form-model-item label ="Role">
          <a-select :disabled="isDisabled" v-model="editResourceInfo.roleId">
            <a-select-option  v-for="item in roleList" :key="item.id" :value="item.id">
              {{item.name}}
            </a-select-option> 
          </a-select>
        
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
    title: 'URI',
    dataIndex: 'uri',
    width: '10%',
    key: 'uri',
  },
  {
    title: 'METHOD',
    dataIndex: 'method',
    width: '10%',
    key: 'method',
  },
  {
    title: 'ROLE REQUIRED',
    dataIndex: 'roleId',
    width: '10%',
    key: 'roleId',
    scopedSlots: { customRender: 'roleId'},
  },
  {
    title: 'STATUS',
    dataIndex: 'status',
    width: '10%',
    key:'status',
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
      resourceList: [],
      addResourceVisible: false,
      editResourceVisible: false,
      columns,
      pageChange:(page, pageSize)=>{
        this.pageIndex=page;
        this.pageSize = pageSize
        this.getResourceList()
      },
      showSizeChange:(current, size)=>{
        this.pageIndex = current
        this.pageSize= size
        this.getResourceList()
      },
      isDisabled: false,
      resourceInfo:{
        id: 0,
        uri:'',
        method:'',
        status:0,
        roleId:0,
      },
      editResourceInfo:{
        id: 0,
        uri:'',
        method:'',
        status:0,
        roleId:0,
      },
      methods: ['POST', 'GET', 'PUT', 'DELETE'], 
      roleList: [],
      addResourceRules:{
        uri: [
          {
            validator: (rule, value, callback)=>{
              if (this.resourceInfo.uri === ''){
                  callback(new Error('Please enter the uri'))
              }else{
               callback()
              }
            }, 
          trigger: 'blur',
          }
        ],
      },
      editResourceRules:{
         uri: [
          {
            validator: (rule, value, callback)=>{
              if (this.editResourceInfo.uri === ''){
                  callback(new Error('Please enter the uri'))
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
    this.getResourceList()
    this.getRoleList()
  },
  methods: {
    // get the categories list
    async getResourceList(){
      const {data:res} = await this.$http.get(`api/roleResourceBind/${this.pageIndex}/${this.pageSize}`)
      console.log(res)
      if(res.code!==200){
        this.$message.error(res.message)
      }
      this.resourceList = res.data.resourceList
      this.total = res.data.total
    },

    deleteResource(id){    
      this.$confirm({
        title: 'Do you want to delete this resource?',
        content: 'When clicked the OK button, this dialog will be closed after 1 second',
        onOk: async () => {
          const {data:res} = await this.$http.delete(`api/roleResourceBind/${id}`)
          if (res.code!==200){
            return this.$message.error(res.message)
          }
          this.$message.success(res.message)
          this.getResourceList()
        },
        onCancel: ()=> {
          this.$message.info("Cancel deleting")
        },
      });
   },
  showAddModal(){
     this.addResourceVisible = true
   },

   async showEditModal (id){
       this.editResourceVisible = true
       const {data: res} = await this.$http.get(`api/roleResourceBind/${id}`)
      this.editResourceInfo = res.data.data
   },

   confirmEdit(){
      this.$refs.editResourceRef.validate( async (valid) => {
       if (!valid) return this.$message.error('Parameters do not match, Please re-enter')
       const{data:res} = await this.$http.put(`api/roleResourceBind`, this.editResourceInfo)
       if (res.code!=200){
         return this.$message.error(res.message)
       }
      this.$message.success(res.message)
      this.editResourceVisible=false
      this.getResourceList()
     })
   },

   onClose(){
     this.addResourceVisible = false
     this.editResourceVisible = false
   },

   radioChange(e){
     let value = e.target.value
     if (value === 9){
       this.isDisabled = true
     }else if (value===1){
       this.isDisabled = false

     }
   },

   confirmAdd(){
     this.$refs.addResourceRef.validate( async (valid) => {
       if (!valid) return this.$message.error('Parameters do not match, Please re-enter')
       const{data:res} = await this.$http.post(`api/roleResourceBind`, this.resourceInfo)
       console.log(res)
       if (res.code!==200){
         return this.$message.error(res.message)
       }
      this.$message.success(res.message)
      this.addResourceVisible=false
      this.getResourceList()
     })
   },

   cancelAdd(){
     this.$refs.addResourceRef.resetFields()
     this.addResourceVisible = false
     this.$message.info ('Cancel creating')
   },

  cancelEdit(){
     this.$refs.editResourceRef.resetFields()
     this.editResourceVisible = false
     this.$message.info ('Cancel editing')
  },


  async getRoleList(){
    const {data:res} = await this.$http.get('api/role')
    if (res.code!==200){
      return this.$message.error(res.message)
    }
    console.log(res)
    this.roleList = res.data.roleList
  }

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