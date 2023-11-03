<script lang="ts">
import {defineComponent, getCurrentInstance} from 'vue'
import {login, signup} from "@/api/user";
import {ElMessage, MessageParamsWithType} from "element-plus";
import type {UploadProps} from 'element-plus'

export default defineComponent({
  name: "LoginView",
  data() {
    return {
      status: 'login',   //login,signup
      name: '',
      password: '',
      user: {
        "name": "",
        "phone_number": "",
        "email": "",
        "password": "",
        "type": 0,
        "avator": "",
        "address_code": "",
        "address_detail": "",
        "unitnum": "",
        "category": []
      },
      accountTypes: [
        {
          value: 1,
          label: 'Customer',
        },
        {
          value: 2,
          label: 'Merchant',
        }],
      accountType: '',
      imageUrl: ''
    }
  },
  beforeCreate() {
    //创建前设置{body}元素的属性
    (document.querySelector('body') as Element).setAttribute('style', 'display:block;margin:0')
  },
  methods: {
    showSignupComponent() {
      this.status = 'signup'
    },
    showLoginComponent() {
      this.status = 'login'
    },
    toLogin() {
      login(this.name, this.password).then((res: any) => {
        console.log(res)
        if (res.code == 200) {
          localStorage.setItem('Reviewlah.id',res.list.user_id)
          localStorage.setItem('Reviewlah.name',res.list.name)
          localStorage.setItem('Reviewlah.phone',res.list.phone_number)
          localStorage.setItem('Reviewlah.email',res.list.email)
          localStorage.setItem('Reviewlah.password',res.list.password)
          localStorage.setItem('Reviewlah.type',res.list.type)
          localStorage.setItem('Reviewlah.avatar',res.list.avator)
          this.$router.push('/home')
          ElMessage({
            message: 'login success',
            type: 'success',
          })
        } else {
          ElMessage.error(res.msg)
        }
      })
    },
    handleAvatarSuccess(response: any, uploadFile: any) {
      this.imageUrl = URL.createObjectURL(uploadFile.raw)
    },
    beforeAvatarUpload(rawFile: { type: string; size: number; }) {
      if (rawFile.type !== 'image/jpeg') {
        ElMessage.error('Avatar picture must be JPG format!')
        return false
      } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('Avatar picture size can not exceed 2MB!')
        return false
      }
      return true
    },
    toSignup() {
      signup(this.user.name, this.user.password, this.user.phone_number, this.user.email, this.user.type, this.user.avator, this.user.address_code, this.user.address_detail, this.user.unitnum, this.user.category)
          .then((res: any) => {
            console.log(res)
            if (res.code == 200) {
              location.reload()
              ElMessage({
                message: 'signup success',
                type: 'success',
              })
            } else {
              ElMessage.error(res.msg)
            }
          })
    }
  }
})
</script>

<template>
  <div id="super-container" style="display: flex;flex-direction:column;height: 100vh;width: 100vw;align-items: center">
    <div id="header-container" style="height: 87px;width:inherit;border-bottom: 1px solid #eaeaea;">
      <div id="header" style="padding: 28px 0 26px;background: #fff;position: relative;z-index: 1012;">
        <img alt="Vue logo" src="@/assets/reviewlah.png">
      </div>
    </div>
    <div id="main-content" style="display: flex;flex-grow:1;width:inherit;align-items: center">
      <div id="login-container" style="display: flex;width:990px;height:646px;padding:15px 0 36px 0;margin: 0 auto">
        <div class="login" v-if="status==='login'"
             style="display:flex;justify-content:center;width: 465px;margin:0 15px" data-component-bound="true">
          <div class="signup-form-container" style="width: 360px">
            <div class="header" style="margin-bottom: 30px;">
              <h2 style="color: #d32323;font-size: 22px">Log in to Reviewlah</h2>
              <p class="subheading" style="font-weight: bold;">
                New to Reviewlah?
                <el-link type="primary" @click="showSignupComponent">Sign up</el-link>
              </p>
              <p class="legal-copy" style="margin-top: 10px; font-size: 12px;line-height: 1.5em;">
                By continuing, you agree to Reviewlah’s <a class="legal-link"
                                                           href="https://www.reviewlah.com/static?p=tos">Terms
                of Service</a> and acknowledge Reviewlah’s <a class="legal-link"
                                                              href="https://www.reviewlah.com/tos/privacy_policy">Privacy
                Policy</a>.
              </p>
            </div>
            <div class="login-form-container">

              <el-input
                  style="margin-bottom: 9px;"
                  v-model="name"
                  placeholder="Name"
              />
              <el-input
                  style="margin-bottom: 9px;"
                  v-model="password"
                  type="password"
                  placeholder="Password"
                  show-password
              />
              <el-button type="danger" style="width: 100%" @click="toLogin">Submit</el-button>
            </div>
          </div>
        </div>
        <div class="signup" v-if="status==='signup'"
             style="display:flex;justify-content:center;width: 465px;margin:0 15px" data-component-bound="true">
          <div class="signup-form-container" style="width: 360px">
            <div class="header" style="margin-bottom: 30px;">
              <h2 style="color: #d32323;font-size: 22px">Sign up for Reviewlah</h2>
              <p class="subheading" style="font-weight: bold;">
                Already on reviewlah?
                <el-link type="primary" @click="showLoginComponent">Login</el-link>
              </p>
              <p class="legal-copy" style="margin-top: 10px; font-size: 12px;line-height: 1.5em;">
                By continuing, you agree to Reviewlah’s <a class="legal-link"
                                                           href="https://www.reviewlah.com/static?p=tos">Terms
                of Service</a> and acknowledge Reviewlah’s <a class="legal-link"
                                                              href="https://www.reviewlah.com/tos/privacy_policy">Privacy
                Policy</a>.
              </p>
            </div>
            <div class="login-form-container">
              <el-select v-model="user.type" placeholder="Select" style="width: 100%;margin-bottom: 9px">
                <el-option
                    v-for="item in accountTypes"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
              <el-input
                  v-if="user.type"
                  style="margin-bottom: 9px;"
                  v-model="user.name"
                  placeholder="Name"
              />
              <el-input
                  v-if="user.type"
                  style="margin-bottom: 9px;"
                  v-model="user.password"
                  type="password"
                  placeholder="Password"
                  show-password
              />
              <el-input
                  v-if="user.type"
                  style="margin-bottom: 9px;"
                  v-model="user.phone_number"
                  placeholder="Phone Number"
              />
              <el-input
                  v-if="user.type"
                  style="margin-bottom: 9px;"
                  v-model="user.email"
                  placeholder="Email"
              />
              <el-upload
                  v-if="user.type"
                  class="avatar-uploader"
                  action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess"
                  :before-upload="beforeAvatarUpload"
              >
                <img v-if="imageUrl" :src="imageUrl" class="avatar"/>
                <el-icon v-else class="avatar-uploader-icon">
                  <Plus/>
                </el-icon>
              </el-upload>
              <el-input
                  v-if="user.type==2"
                  style="margin-bottom: 9px;margin-top:9px"
                  v-model="user.address_code"
                  placeholder="Post Code"
              />
              <el-input
                  v-if="user.type==2"
                  style="margin-bottom: 9px"
                  v-model="user.address_detail"
                  placeholder="Address Detail"
              />
              <el-input
                  v-if="user.type==2"
                  style="margin-bottom: 9px"
                  v-model="user.unitnum"
                  placeholder="Unit Number"
              />
              <el-checkbox-group v-model="user.category" v-if="user.type==2">
                <el-checkbox label="BBQ"/>
                <el-checkbox label="ChineseFood"/>
                <el-checkbox label="FrenchFood"/>
                <el-checkbox label="HotPot"/>
              </el-checkbox-group>
              <el-button type="danger" style="width: 100%" @click="toSignup">Submit</el-button>
            </div>
          </div>
        </div>
        <div id="right" style="width: 465px;margin:0 15px">
          <img alt="Vue logo" src="@/assets/reviewlah.png">
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.header p {
  margin-bottom: 12px;
  text-align: center;
  font-size: 14px;
  line-height: 1.28571em;
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
  color: #333;
}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>