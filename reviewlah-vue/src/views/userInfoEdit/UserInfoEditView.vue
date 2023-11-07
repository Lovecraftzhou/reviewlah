<script lang="ts">
import {defineComponent} from 'vue'
import {getMerchantDeatail} from "@/api/merchant";
import {ElMessage} from "element-plus";
import {getUserInfo, updateUserInfo} from "@/api/user";

export default defineComponent({
  name: "UserInfoEditView",
  data() {
    return {
      userId: "",
      user: {
        user_id: 1,
        name: "wxf",
        phone_number: "12345678",
        email: "123456@qq.com",
        password: "201019",
        type: 2,
        avator:'',

      },
      createAnnouncementVisible: false,
      newAnnouncement: {
        content: ''
      }
    }
  },
  watch: {
    $route() {
      let userId = this.$route.query && this.$route.query.userId
      if (typeof userId === "string") {
        this.userId = userId
      }
    }
  },
  created() {
    let userId = this.$route.query.userId
    if (typeof userId === "string") {
      this.userId = userId
      getUserInfo(parseInt(userId)).then((res: any) => {
        this.user = res.list
      })
    }
  },
  methods: {
    beforeAvatarUpload(rawFile: any) {
      if (rawFile.type !== 'image/jpeg') {
        ElMessage.error('Avatar picture must be JPG format!')
        return false
      } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('Avatar picture size can not exceed 2MB!')
        return false
      }
      return true
    },
    handleFileUpload(rawFile: any) {
      // eslint-disable-next-line @typescript-eslint/no-this-alias
      let that = this
      let reader = new FileReader()
      reader.readAsDataURL(rawFile.file)
      reader.onload = function () {
        if (reader.result)
          that.user.avator = reader.result.toString()
      }
    },
    submitBasicInfo() {
      // eslint-disable-next-line @typescript-eslint/no-this-alias
      let that = this
      let id=parseInt(this.userId)
      updateUserInfo(id,that.user.name,'',that.user.phone_number,that.user.email,that.user.avator).then((res:any)=>{
        if (res.code == 200) {
          getUserInfo(parseInt(this.userId)).then((res: any) => {
            this.user = res.list
          })
          ElMessage({
            message: 'Update success!',
            type: 'success',
          })
        } else {
          ElMessage.error(res.msg)
        }
      })
    },
  },

})
</script>

<template>
  <div class="main_container">
    <div class="basic_info_container">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>Basic Information</span>
          </div>
        </template>
        <el-form label-width="120px">
          <el-form-item label="Picture">
            <div class="item">
              <el-upload
                  class="avatar-uploader"
                  action="#"
                  :show-file-list="false"
                  :before-upload="beforeAvatarUpload"
                  :http-request="handleFileUpload"
              >
                <img v-if="user.avator" :src="user.avator" class="avatar"/>
                <el-icon v-else class="avatar-uploader-icon">
                  <Plus/>
                </el-icon>
              </el-upload>
            </div>
          </el-form-item>
          <el-form-item label="Name">
            <el-input
                style="margin-bottom: 9px;"
                v-model="user.name"
                placeholder="Name"
            />
          </el-form-item>
          <el-form-item label="Phone Number">
            <el-input
                style="margin-bottom: 9px;margin-top:9px"
                v-model="user.phone_number"
                placeholder="Phone Number"
            />
          </el-form-item>
          <el-form-item label="Email">
            <el-input
                style="margin-bottom: 9px"
                v-model="user.email"
                placeholder="Email"
            />
          </el-form-item>
        </el-form>
        <el-button type="primary" style="width: 100%;margin-top: 12px"
                   @click="submitBasicInfo()">
          Submit
        </el-button>
      </el-card>
    </div>
  </div>
</template>

<style scoped>
.main_container {
  box-sizing: border-box;
  min-width: 1144px;
  max-width: 1224px;
  width: 100%;
  margin: auto;
  padding: 0px 40px;
  padding-top: 56px !important;
  padding-bottom: 56px !important;
  border-top: 1px solid rgba(235, 235, 235, 1);
  border-color: rgba(235, 235, 235, 1);
}

.basic_info_container {
  width: 800px;
  margin: 0 auto
}

* {
  margin: 0;
  padding: 0;
}

ul li {
  list-style: none;
}

a {
  text-decoration: none;
}

.banner {
  width: 900px;
  height: 600px;
  margin: 20px auto;
  position: relative;
}

.bannerImg img {
  width: 900px;
  height: 600px;
  z-index: 800;
  position: relative;
}

.bannerItems {
  position: absolute;
  z-index: 1000;
  bottom: 0;
  width: 100%;
  padding: 10px 0;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.bannerItems span {
  width: 30px;
  height: 30px;
  background: rgba(100, 100, 100, .6);
  margin: 0 5px;
  border-radius: 50%;
  font-size: 16px;
  font-weight: bold;
  font-family: Microsoft YaHei;
  line-height: 30px;
  text-align: center;
  color: white;
  cursor: pointer;
}

.bannerItems span.active {
  background-color: red;
}

.basic_info_container .card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.basic_info_container .text {
  font-size: 14px;
}

.basic_info_container .item {
  margin-bottom: 18px;
}

.basic_info_container .box-card {
  margin-bottom: 20px;
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