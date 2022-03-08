<template>
  <div>
    <a-card>
         <a-form-model :model="postInfo"  ref="postInfoRef"
        :rules="postInfoRules" layout="vertical" hide-required-mark>
       <a-row :gutter="16">
         <a-col :span="8">
        <a-form-model-item label ="Title" prop="title">
          <a-input v-model="postInfo.title" placeholder="Please enter the post title"></a-input>
        </a-form-model-item>
         </a-col>
          <a-col :span="8">
          <a-form-model-item label ="Category" prop="category">
           <a-select v-model="postInfo.categoryId" defaultValue="Please select category"  @change="handleChange">
            <a-select-option v-for="item in categoryList" :key="item.id" :value="item.id">
              {{item.name}}
          </a-select-option> 
           </a-select>  
          </a-form-model-item>
         </a-col>

        
        </a-row>


         <a-row :gutter="16">
        <a-col :span="8">
          <a-form-model-item label ="Cover Image" prop="image">
            <a-upload
                :beforeUpload="beforeImageUpload"
                :maxCount=1
                :multiple= "false"
                :remove="handleImageRemove"
                @change = "handleImageChange"
                @preview= "handleImagePreview"
                :action = "imageUrl"
                :headers = "headers"
                listType="picture-card"
                style="width: 60%"
              >
                <a-button>
                  <a-icon type="cloud-upload" />
                </a-button>

                <template v-if="id">
                  <img :src="postInfo.image" style="width: 120px; height: 100px; margin-left: 15px" />
                </template>
              </a-upload>

              <a-modal :footer="null" :visible="previewVisible" @cancel="handleImageCancel">
                <img :src="previewImage" alt="example" style="width: 100%"/>
              </a-modal>
          </a-form-model-item>
        </a-col>
          
        </a-row>
  
        <a-row :gutter="16">
        <a-col :span="16">
            <a-form-model-item label="Description" prop="description">
              <a-textarea
                 v-model="postInfo.description"
                :rows="5"
                placeholder="Please enter description"
              />
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-form-model-item label="Content" prop="content">
           <mavon-editor
	         v-model="postInfo.content"
	        :placeholder="'Edit ···'"
	        ref="md"
	        @imgAdd="imgAdd"
	        @imgDel="imgDel"
	        @change="change"
	        :toolbars="toolbars"
	        :toolbarsBackground="'#f9f9f9'"
	        style="height: calc(100vh - 50px)"
	        />
  
        </a-form-model-item>
        <a-row :gutter="16">
         <a-form-model-item>
          <a-button type="danger" style="margin-right: 15px" @click="submit(postInfo.id)">
            {{
            postInfo.id ? 'Update' : 'Submit'
            }}
          </a-button>
          <a-button type="primary" @click="cancelAdd">Cancel</a-button>
        </a-form-model-item>
          </a-row>
      </a-form-model>
      



    </a-card>
  </div>
</template>

<script>
import {Url} from '../../plugin/http.js'
import {addCodeBtn } from '../../plugin/editor.js'

export default {
  props: ['id'],
  data(){
    return{
      imageUrl : Url + 'api/oss/file/upload',
      imageLoading: false,
      previewVisible: false,
      previewImage: '',
      postInfo:{
        id: 0,
        title:'',
        categoryId:undefined,
        description:'',
        content:'',        
        image: '',
      },
      headers:{},
      categoryList:[],
      postInfoRules: {
        title: [{ required: true, message: 'Please enter the title', trigger: 'change' }],
        categoryId: [{ required: true, message: 'Please select the category', trigger: 'change' }],
        description: [
          { required: true, message: 'Please enter the description', trigger: 'change' },
          { max: 400, message: 'Description length must not larger than 400', trigger: 'change' },
        ],
        // image: [{ required: true, message: 'Please select the cover image', trigger: 'change' }],
        content: [{ required: true, message: 'Please input the content', trigger: 'change' }],
      }, 
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: false, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: false // 预览
      },
    }
  },
  mounted(){
    this.getCategoryList()
   this.headers = { Authorization: `Bearer ${window.sessionStorage.getItem('token')}` }
    if(this.id){ 
      this.getPostInfo(this.id)
    }
  }, 
  methods: {
    async getPostInfo(id){
      const {data: res} = await this.$http.get(`api/post/${id}`)
      if (res.code!== 200){
          return this.$message.error(res.message)
      }
      this.postInfo = res.data.post
      this.postInfo.id = res.data.post.id

    },
    async getCategoryList(){
      const {data : res} = await this.$http.get(`api/category`)
      if(res.code!=200){
        this.$message.error(res.message)
      }
      this.categoryList = res.data.list
    },

    handleChange(value){
     this.postInfo.categoryId=value
    },

submit(id) {
  this.$refs.postInfoRef.validate(async (valid) => {
    if (!valid) return this.$message.error('Parameters do not match, please re-enter')
    if (id === 0) {
    const { data: res } = await this.$http.post('api/post', this.postInfo)
    if (res.code !== 200) return this.$message.error(res.message)
    this.$router.push('/articles')
    this.$message.success(res.message)
    } else {
    const { data: res } = await this.$http.put('api/post', this.postInfo)
    if (res.code !== 200) return this.$message.error(res.message)
    this.$router.push('/articles')
    this.$message.success(res.message)
    }
  })
},

  cancelAdd() {
   this.$refs.postInfoRef.resetFields()
  },

  handleImageChange(info){
    const file = info.file
    const status = info.file.status
    if (status === 'uploading'){
      this.imageLoading = true
      return
    }
    if (status === 'done'){
      this.$message.success('Successfully uploaded the image')
      this.imageLoading = false
      console.log(file.response)
      this.postInfo.image = info.file.response.data.url
      console.log(this.postInfo.image)
    }else if (status === 'error'){
      this.$messaage.error(`Fail to upload ${info.file.name} `)
    }
  },

  beforeImageUpload (file){
    return new Promise ((resolve, reject) => {
      const isJPG = file.type === 'image/jpeg'
      if (!isJPG){
        this.$message.error ('Only jpg format is acceptable')
        return reject(new Eroor ('Only jpg format is acceptable'))
      }
      const isLt2M = file.size /1024 /1024 < 2
      if (!isLt2M){
        this.$message.error ('The size of image cannot larger than 2MB')
        return reject (new Error ('The size of image cannot larger thatn 2MB'))
      }
      this.$message.info('Uploading file.....')
      return resolve (true)
    })
  },

  handleImagePreview(file){
    this.previewImage = file.url || file.thumbUrl
    this.previewVisible = true
  },

  handleImageCancel(){
    this.previewVisible = false
  },

  async handleImageRemove(file){
    const name = file.name
    const {data: res} = await this.$http.delete('/api/oss/file', 
    {params:
        {'url': this.postInfo.image
        }
      }, )
    if (res.code !== 200) return this.$message.error(res.message)
    this.postInfo.image = ''
    this.$message.success(res.message)
  },

  async imgAdd(pos, $file){
    var_this = this
    var formdata = new FormData();
    formdata.append('image', $file);
    const {data:res} = await this.$http.post('api/oss/file', formdata,{
      headers:{
        'Content-Type': 'multipart/form/data'
      }
    })
    if (res.code !== 200){
      return this.$message(res.message)
    }
    var url = res.data.url;
    _this.$refs.md.$img2Url(pos,url)

  },

  imgDel(pos){
    
  }



 }
}
</script>