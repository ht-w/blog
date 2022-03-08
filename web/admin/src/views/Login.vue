<template>
  <div class="container">
    <div class ="loginBox">
      <a-form-model ref="loginFormRef" :rules="rules" :model="formdata" class="loginForm">
      <a-form-model-item prop="username"> 
        <a-input v-model="formdata.username" placeholder="Username" >
           <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
        </a-input>
      </a-form-model-item>
      <a-form-model-item prop="password">
        <a-input v-model="formdata.password" placeholder="Password" type="password" v-on:keyup.enter="login">
          <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
        </a-input>
      </a-form-model-item>
       <a-form-model-item class="loginBtn">
        <a-button type="primary" style="margin:10px" @click="login">Login</a-button>
        <a-button type="info" style="margin:10px" @click="resetForm">Cancel</a-button>
      </a-form-model-item>
      </a-form-model>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      formdata: {
        username: "",
        password: ""
      },
      rules: {
         username: [
          { required: true, message: 'Please input the username', trigger: 'blur' },
          { min: 4, max: 12, message: 'Username length must between 4 and 12', trigger: 'blur' },
        ],
          password: [
          { required: true, message: 'Please input the password', trigger: 'blur' },
          { min: 6, max: 20, message: 'Password length must between 6 and 20', trigger: 'blur' },
        ],
      },
    }   
  },
  methods: {
    resetForm(){
      this.$refs.loginFormRef.resetFields();
    },
    login(){
      this.$refs.loginFormRef.validate( async valid=>{
        if(!valid){
        return this.$message.error("Invalid username or password!")
        }
        const {data:res} = await this.$http.post(`api/auth/login`,this.formdata)
        if (res.code!=200){
          return this.$message.error(res.message);
        }
        window.sessionStorage.setItem('token', res.data.token)
        this.$router.push('/')
      })
    }
  }
}
</script>

<style scoped>
.container{
  height: 100%;
  background-image: url("../assets/images/b.jpg");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

.loginBox{
  width: 300px;
  height: 280px;
  background-color: #ffffff;
  opacity: 0.7;
  position: absolute;
  top: 60%;
  left:75%;
  transform: translate(-50%, -50%);
  border-radius: 9px;
}

.loginForm{
  width: 100%;
  position:absolute;
  bottom:10%;
  padding: 0 20px;
  box-sizing: border-box;
}

.loginBtn{
  display:flex;
  justify-content: flex-end;
}
</style>