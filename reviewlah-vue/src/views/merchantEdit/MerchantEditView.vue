<script lang="ts">
import {defineComponent} from 'vue'
import {addAnnouncement, deleteAnnouncement, getMerchantDeatail, updateAddress, updateCategory} from "@/api/merchant";
import {ElMessage} from "element-plus";
import {createNewPost} from "@/api/post";
import {updateUserInfo} from "@/api/user";

export default defineComponent({
  name: "MerchantEditView",
  data() {
    return {
      userId: "",
      merchant: {
        address_detail: '',
        address_code: "119123",
        user_id: 18,
        address_unitnum: "04-03",
        dish: [
          {
            dish_id: 10,
            pic_dish: '',
            price: 10.0,
            dish_name: "Fried Rice"
          }
        ],
        name: "E2",
        diningComment: [
          {
            dining_com_id: 4,
            merchant_id: 12,
            customer_id: 2,
            content: "So Great",
            rate: 5,
            time_dc: "2023-10-15 16:45:20",
            pic_dc: "D://pic/food6.jpg"
          }
        ],
        avator: '',
        avg_rate: 5.0,
        phone_number: "00000000",
        email: "123",
        category: [
          "HotPot"
        ],
        announcement: [
          {
            announcement_id: 7,
            merchant_id: 12,
            content: "Some Dish Has Sold Out!",
            time_anc: "2023-10-31 14:13:17"
          }
        ]
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
      getMerchantDeatail(userId).then((res: any) => {
        this.merchant = res.list
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
        if (reader.result){
          that.merchant.avator = reader.result.toString()
        }
      }
    },
    submitBasicInfo() {
      // eslint-disable-next-line @typescript-eslint/no-this-alias
      let that = this
      let id=parseInt(this.userId)
      updateUserInfo(id,that.merchant.name,'',that.merchant.phone_number,that.merchant.email,that.merchant.avator).then((res1:any)=>{
        updateAddress(id,that.merchant.address_code,that.merchant.address_detail,that.merchant.address_unitnum).then((res2:any)=>{
          updateCategory(id,that.merchant.category).then((res3:any)=>{
            if (res1.code == 200&&res2.code == 200&&res3.code == 200) {
              getMerchantDeatail(this.userId).then((res: any) => {
                this.merchant = res.list
              })
              ElMessage({
                message: 'Update success!',
                type: 'success',
              })
            } else {
              ElMessage.error('Update Failed')
            }
          })
        })
      })
    },
    addAnnouncement() {
      this.createAnnouncementVisible = true
    },
    submitNewAnnouncement() {
      addAnnouncement(this.userId, this.newAnnouncement.content).then((res: any) => {
        if (res.code == 200) {
          getMerchantDeatail(this.userId).then((res: any) => {
            this.merchant = res.list
          })
          ElMessage({
            message: 'Add success!',
            type: 'success',
          })
        } else {
          ElMessage.error(res.msg)
        }
      })
      this.createAnnouncementVisible = false
    },
    deleteAnnouncement(id: any) {
      deleteAnnouncement(id).then((res: any) => {
        if (res.code == 200) {
          location.reload()
          ElMessage({
            message: 'Delete success!',
            type: 'success',
          })
        } else {
          ElMessage.error(res.msg)
        }
      })
      getMerchantDeatail(this.userId).then((res: any) => {
        this.merchant = res.list
      })
    }
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
                <img v-if="merchant.avator" :src="merchant.avator" class="avatar"/>
                <el-icon v-else class="avatar-uploader-icon">
                  <Plus/>
                </el-icon>
              </el-upload>
            </div>
          </el-form-item>
          <el-form-item label="Name">
            <el-input
                style="margin-bottom: 9px;"
                v-model="merchant.name"
                placeholder="Name"
            />
          </el-form-item>
          <el-form-item label="Phome Number">
          <el-input
              style="margin-bottom: 9px;margin-top:9px"
              v-model="merchant.phone_number"
              placeholder="Phome Number"
          />
          </el-form-item>
          <el-form-item label="Email">
            <el-input
                style="margin-bottom: 9px"
                v-model="merchant.email"
                placeholder="Email"
            />
          </el-form-item>
          <el-form-item label="Post Code">
            <el-input
                style="margin-bottom: 9px;margin-top:9px"
                v-model="merchant.address_code"
                placeholder="Post Code"
            />
          </el-form-item>
          <el-form-item label="Address Detail">
            <el-input
                style="margin-bottom: 9px"
                v-model="merchant.address_detail"
                placeholder="Address Detail"
            />
          </el-form-item>

          <el-form-item label="Unit Number">
            <el-input
                style="margin-bottom: 9px"
                v-model="merchant.address_unitnum"
                placeholder="Unit Number"
            />
          </el-form-item>

          <el-form-item label="Category">
            <el-checkbox-group v-model="merchant.category">
              <el-checkbox label="BBQ"/>
              <el-checkbox label="ChineseFood"/>
              <el-checkbox label="FrenchFood"/>
              <el-checkbox label="HotPot"/>
            </el-checkbox-group>
          </el-form-item>
        </el-form>
        <el-button type="primary" style="width: 100%;margin-top: 12px"
                   @click="submitBasicInfo()">
          Submit
        </el-button>
      </el-card>

      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>Announcement</span>
            <el-button class="button" text @click="addAnnouncement">Add</el-button>
          </div>
        </template>
        <el-table :data="merchant.announcement" style="width: 100%" max-height="250">
          <el-table-column fixed prop="announcement_id" label="Announcement Id" width="150"/>
          <el-table-column prop="time_anc" label="Announce Time" width="120"/>
          <el-table-column prop="content" label="Content" width="600"/>
          <el-table-column fixed="right" label="Operations" width="120">
            <template #default="scope">
              <el-button
                  link
                  type="primary"
                  size="small"
                  @click.prevent="deleteAnnouncement(scope.row.announcement_id)"
              >
                Delete
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>

      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>Card name</span>
            <el-button class="button" text>Operation button</el-button>
          </div>
        </template>
        <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
      </el-card>
    </div>
    <el-dialog
        v-model="createAnnouncementVisible"
        title="Create Post"
        destroy-on-close
        center
        style="    max-width: 1300px;    min-height: 400px;    min-width: 960px;"
    >
      <div style="width: 100%;display: flex;margin-left: 200px">
        <div class="announcement-form-container" style="width: 500px;height: 500px">
          <el-input
              style="margin-bottom: 9px;"
              v-model="newAnnouncement.content"
              placeholder="Content"

          />
          <el-button type="danger" style="width: 100%" @click="submitNewAnnouncement">Submit</el-button>
        </div>
      </div>
    </el-dialog>
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
  width: min-content;
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