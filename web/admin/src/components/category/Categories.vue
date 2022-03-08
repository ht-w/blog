<template>

  <div style class="categoryCard">
    <a-card>

         <a-row  :gutter="15"   class="UtilRow" >

        <a-col span="1.5">
            <a-button type="dashed" shape="circle"  style="margin-right:5px" @click="showAddModal"><a-icon type="plus"/></a-button>
        </a-col>
      </a-row>
     
      <a-table rowKey="id" :columns="columns" :pagination="false" :dataSource="categoryList" >
        <template slot="action" slot-scope="data">
         <div class="actionSlot">
           <a-button type="primary" size="small" shape="circle"  style="margin-right:5px" @click="showEditModal(data.id)"><a-icon type="edit"/></a-button>
          <a-button type="danger" size="small" shape="circle"  @click="deleteCategory(data.id)" ><a-icon type="delete"/></a-button>
         </div>  
        </template> 
      </a-table>

       <div id="components-pagination-demo-mini">
          <a-pagination size="small" :total="total" :show-total="total => `Total ${total} items`" @change="pageChange" @showSizeChange="showSizeChange" show-size-changer />
      </div>
    </a-card>
  
<!-- // -->   
      <a-modal
        title="Create new category"
        :visible = "addCategoryVisible"
        @ok = "confirmAdd"
        @cancel = "cancelAdd"
      >

      <a-form-model :model="categoryInfo" :rules="addCategoryRules" ref="addCategoryRef" layout="vertical" hide-required-mark>
        <a-form-model-item label ="Category Name" prop="name">
          <a-input v-model="categoryInfo.name" placeholder="Please enter the category name"></a-input>
        </a-form-model-item>
        </a-form-model>
           
      </a-modal>


<!-- //EDIT -->
       <a-modal
        title="Edit category"
        :visible = "editCategoryVisible"
        @ok = "confirmEdit"
        @cancel = "cancelEdit"
      >

      <a-form-model :model="editCategoryInfo" :rules="editCategoryRules" ref="editCategoryRef" layout="vertical" hide-required-mark>
        <a-form-model-item label ="Category Name" prop="name">
          <a-input v-model="editCategoryInfo.name" placeholder="Please enter the category name"></a-input>
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
      categoryList: [],
      addCategoryVisible: false,
      editCategoryVisible: false,
      columns,
      pageChange:(page, pageSize)=>{
        this.pageIndex=page;
        this.pageSize = pageSize
        this.getCategoryList()
      },
      showSizeChange:(current, size)=>{
        this.pageIndex = current
        this.pageSize= size
        this.getCategoryList()
      },
    
     
      categoryInfo:{
        id: 0,
        name: '',
      },
      editCategoryInfo:{
        id: 0,
        name: '',
      },
      addCategoryRules:{
        name: [
          {
            validator: (rule, value, callback)=>{
              if (this.categoryInfo.name === ''){
                  callback(new Error('Please enter the category name'))
              }else{
               callback()
              }
            }, 
          trigger: 'blur',
          }
        ],
      },
      editCategoryRules:{
         name: [
          {
            validator: (rule, value, callback)=>{
              if (this.editCategoryInfo.name === ''){
                  callback(new Error('Please enter the category name'))
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
    this.getCategoryList()
  },
  methods: {
    // get the categories list
    async getCategoryList(){
      const {data:res} = await this.$http.get(`api/category/${this.pageIndex}/${this.pageSize}`)
      console.log(res)
      if(res.code!==200){
        this.$message.error(res.message)
      }
      this.categoryList = res.data.categories
      this.total = res.data.total
    },

    // delete category
    deleteCategory(id){
      this.$confirm({
        title: 'Do you want to delete this category?',
        content: 'When clicked the OK button, this dialog will be closed after 1 second',
        onOk: async () => {
          const {data:res} = await this.$http.delete(`api/category/${id}`)
          if (res.code!==200){
            return this.$message.error(res.message)
          }
          this.$message.success(res.message)
          this.getCategoryList()
        },
        onCancel: ()=> {
          this.$message.info("Cancel deleting")
        },
      });
   },
  showAddModal(){
     this.addCategoryVisible = true
   },

   async showEditModal (id){
       this.editCategoryVisible = true
       const {data: res} = await this.$http.get(`api/category/${id}`)
      this.editCategoryInfo = res.data.category
   },

   confirmEdit(){
      this.$refs.editCategoryRef.validate( async (valid) => {
       if (!valid) return this.$message.error('Parameters do not match, Please re-enter')
       const{data:res} = await this.$http.put(`api/category`, this.editCategoryInfo)
       if (res.code!=200){
         return this.$message.error(res.message)
       }
      this.$message.success(res.message)
      this.editCategoryVisible=false
      this.getCategoryList()
     })
   },

   onClose(){
     this.addCategoryVisible = false
     this.editCategoryVisible = false
   },

   confirmAdd(){
     this.$refs.addCategoryRef.validate( async (valid) => {
       if (!valid) return this.$message.error('Parameters do not match, Please re-enter')
       const{data:res} = await this.$http.post(`api/category`, this.categoryInfo)
       if (res.code!==200){
         return this.$message.error(res.message)
       }
      this.$message.success(res.message)
      this.addCategoryVisible=false
      this.getCategoryList()
     })
   },

   cancelAdd(){
     this.$refs.addCategoryRef.resetFields()
     this.addCategoryVisible = false
     this.$message.info ('Cancel creating')
   },

  cancelEdit(){
     this.$refs.editCategoryRef.resetFields()
     this.editCategoryVisible = false
     this.$message.info ('Cancel editing')
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